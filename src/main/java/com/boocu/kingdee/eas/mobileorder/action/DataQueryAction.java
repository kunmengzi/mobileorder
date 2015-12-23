package com.boocu.kingdee.eas.mobileorder.action;

import com.boocu.kingdee.eas.mobileorder.dao.IDataQueryDao;
import com.boocu.kingdee.eas.mobileorder.vo.MaterialGroupVO;
import com.boocu.kingdee.eas.mobileorder.vo.MaterialVO;
import com.boocu.kingdee.eas.mobileorder.vo.MeasureUnitVO;
import com.kingdee.bos.Context;
import com.kingdee.eas.basedata.org.CtrlUnitInfo;
import com.kingdee.eas.mobilecommon.struts2.action.BaseMobileAction;
import com.kingdee.eas.util.app.ContextUtil;
import com.kingdee.util.StringUtils;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jordan on 15/12/6.
 */
public class DataQueryAction extends BaseMobileAction{

    //日志信息
    private static final Logger logger = Logger.getLogger(DataQueryAction.class);

    private static final String ATTR_KEY_CONTEXT = "XTContext";
    private static final String CONTENT_TYPE_JSON = "application/json;charset=utf-8";
    private static final String HEADER_KEY_CACHE_CONTROL = "cache-control";
    private static final String HEADER_VALUE_NO_CACHE = "no-cache";

    /** T_BD_MaterialGroupStandard -- 网上订货分类标准 **/
    private static final String MATERIAL_GROUP_STANDARD_ID = "Cqw8RgA/S0W0WYWqpA5On5eb4R8=";

    private IDataQueryDao dataQueryDao;

    /**
     * 查询物料分组
     *
     * @return
     * @throws Exception
     */
    public String queryMaterialGroup() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        Context context = (Context) request.getSession().getAttribute(ATTR_KEY_CONTEXT);

        List<MaterialGroupVO> result = dataQueryDao.queryMaterialGroupByStandard(MATERIAL_GROUP_STANDARD_ID);
        write(result);

        return null;
    }

    /**
     * 查询物料分组
     *
     * @return
     * @throws Exception
     */
    public String queryMaterial() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        Context context = (Context) request.getSession().getAttribute(ATTR_KEY_CONTEXT);
        String materialGroupId = request.getParameter("materialGroupId");

        //todo 获取客户ID
        //当前登录用户为客户？？
        String customerId = null;

        //CUID
        CtrlUnitInfo cu = ContextUtil.getCurrentCtrlUnit(context);

        if(!StringUtils.isEmpty(materialGroupId)){
            //获取物料列表
            List<MaterialVO> result = dataQueryDao.queryMaterialByGroupAndSaleOrgUnit(materialGroupId,customerId, cu.getId().toString());
            write(result);
        }else{
            logger.warn("获取物料数据失败。物料组ID为空。materialGroupId=" + materialGroupId);
        }

        return null;
    }

    /**
     * 查询计量单位
     *
     * @return
     * @throws Exception
     */
    public String queryMeasureUnit() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        Context context = (Context) request.getSession().getAttribute(ATTR_KEY_CONTEXT);

        String materialId = request.getParameter("materialId");
        if(!StringUtils.isEmpty(materialId)){
            List<MeasureUnitVO> result = dataQueryDao.queryMeausreUnitByMaterialId(materialId);
            write(result);
        }else{
            logger.warn("获取计量单位数据失败。物料ID为空。materialId=" + materialId);
        }

        return null;
    }

    /**
     * 选择计量单位后，计算最新的价格
     * @return
     * @throws Exception
     */
    public String calPrice() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        Context context = (Context) request.getSession().getAttribute(ATTR_KEY_CONTEXT);

        String materialId = request.getParameter("materialId");
        String measureUnitId = request.getParameter("measureUnitId");

        if(!StringUtils.isEmpty(materialId) && !StringUtils.isEmpty(measureUnitId)){
            //计算价格、金额
            CtrlUnitInfo cu = ContextUtil.getCurrentCtrlUnit(context);
            MaterialVO materialVO = dataQueryDao.getMaterialVO(materialId, cu.getId().toString());

            //获取选择的计量单位
            MeasureUnitVO measureUnitVO = dataQueryDao.getMeasureUnitVO(materialId,measureUnitId);
            if(measureUnitVO!=null){
                //用户选择的即为基本价格中的计量单位，直接返回价格
                if(StringUtils.equals(measureUnitVO.getId().toString(),materialVO.getUnitId())){
                    Map<String,Object> result = new HashMap<String, Object>();
                    result.put("price",materialVO.getPrice());
                    write(result);
                    return null;
                }else{
                    //计量单位换算
                    MeasureUnitVO basePriceMeasureUnitVO = dataQueryDao.getMeasureUnitVO(materialId, materialVO.getUnitId());
                    if(basePriceMeasureUnitVO!=null){
                        //计算
                        BigDecimal rate = measureUnitVO.getBaseConvsRate().divide(basePriceMeasureUnitVO.getBaseConvsRate(), 4, RoundingMode.HALF_UP);
                        BigDecimal finalPrice = materialVO.getPrice().multiply(rate);
                        Map<String,Object> result = new HashMap<String, Object>();
                        result.put("price",finalPrice.setScale(2,RoundingMode.HALF_UP));
                        write(result);
                        return null;
                    }
                }
            }else{
                logger.warn("计算价格失败，获取不到计量单位数据。materialId=" + materialId + ";measureUnitId=" + measureUnitId);
            }

        }else{
            logger.warn("计算价格失败，获取不到计量单位数据。物料ID为空或者计量单位ID为空。materialId=" + materialId + ";measureUnitId=" + measureUnitId);
        }

        return null;
    }

    /**
     * 查询购物车列表
     * @return
     */
    public String queryShoppingCarList(){



        return null;
    }

    /**
     * 输出数据
     *
     * @param obj
     * @throws IOException
     */
    private void write(Object obj) throws IOException {
        if(obj==null){
            return ;
        }

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType(CONTENT_TYPE_JSON);
        response.setHeader(HEADER_KEY_CACHE_CONTROL, HEADER_VALUE_NO_CACHE);

        PrintWriter out = response.getWriter();
        out.print(JSONObject.fromObject(obj).toString());
        out.flush();
        out.close();

        return ;
    }

    public IDataQueryDao getDataQueryDao() {
        return dataQueryDao;
    }

    public void setDataQueryDao(IDataQueryDao dataQueryDao) {
        this.dataQueryDao = dataQueryDao;
    }
}
