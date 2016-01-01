package com.boocu.kingdee.eas.mobileorder.servlet;

import com.boocu.kingdee.eas.mobileorder.dao.IDataQueryDao;
import com.boocu.kingdee.eas.mobileorder.vo.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.basedata.assistant.MeasureUnitInfo;
import com.kingdee.eas.basedata.master.cssp.CustomerInfo;
import com.kingdee.eas.basedata.master.material.MaterialInfo;
import com.kingdee.eas.basedata.scm.sd.channel.*;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.dynbusiness.util.StringUtil;
import com.kingdee.eas.scm.sd.channel.ShoppingCartRecordFactory;
import com.kingdee.eas.scm.sd.channel.ShoppingCartRecordInfo;
import com.kingdee.eas.util.app.ContextUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jordan on 2015/11/30.
 */
public class AddCarServlet extends AbstractOrderServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 不支持GET方法
        throw new RuntimeException("not supported");
    }

    @Override
    protected Object getData(IDataQueryDao dataQueryDao, HttpServletRequest req) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Context context = getContext(req);

        try{
            ShoppingCarVO vo = getVO(req);
            validateVO(vo);
            submitVO(context,vo);

            result.put("success", true);
            result.put("msg","添加商品成功");
        }catch (Exception e){
            logger.error("添加商品到购物车发生异常",e);

            result.put("success", false);
            result.put("msg",e.getMessage());
        }

        return result;
    }

    @Override
    protected Object getMockData(IDataQueryDao dataQueryDao, HttpServletRequest req) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success",true);
        result.put("message","添加商品成功");

        return result;
    }

    /**
     * 提交数据
     *
     * @param context
     * @param vo
     */
    private void submitVO(Context context,ShoppingCarVO vo) throws BOSException, EASBizException {
        ShoppingCartRecordInfo recordInfo = new ShoppingCartRecordInfo();

        //创建人信息
        recordInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        recordInfo.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
        recordInfo.setCreator(ContextUtil.getCurrentUserInfo(context));
        recordInfo.setLastUpdateUser(ContextUtil.getCurrentUserInfo(context));

        //组织信息
        recordInfo.setCU(ContextUtil.getCurrentCtrlUnit(context));
        recordInfo.setSaleOrgUnit(ContextUtil.getCurrentSaleUnit(context));

        //渠道信息
        ChannelBaseInfo channelBaseInfo = getChannelBaseInfo(context);
        recordInfo.setChannel(channelBaseInfo);

        //物料
        MaterialInfo materialInfo = new MaterialInfo();
        materialInfo.setId(BOSUuid.read(vo.getMaterialVO().getId()));
        recordInfo.setMaterial(materialInfo);

        //计量单位
        MeasureUnitInfo unitInfo = new MeasureUnitInfo();
        unitInfo.setId(BOSUuid.read(vo.getMeasureUnitVO().getId()));
        recordInfo.setUnit(unitInfo);

        //数据信息
        recordInfo.setQty(vo.getQty());
        recordInfo.setAmount(vo.getAmount());
        recordInfo.setPrice(vo.getPrice());
        recordInfo.setAvailable(true);
        recordInfo.setShopDate(new Date());

        //写到数据库中
        try {
            ShoppingCartRecordFactory.getLocalInstance(context).submit(recordInfo);
        }catch(Exception e){
            logger.error("添加商品到购物车发生异常",e);
            throw new RuntimeException("请稍后重试或联系管理员");
        }
    }

    /**
     * 数据校验
     *
     * @param vo
     */
    private void validateVO(ShoppingCarVO vo) {
        // 非空校验
        if (StringUtil.isEmpty(vo.getMaterialVO().getId())){
            throw new RuntimeException("请选择物料");
        }

        if (StringUtil.isEmpty(vo.getMaterialVO().getMaterialGroupVO().getId())){
            throw new RuntimeException("请选择类目");
        }

        if (StringUtil.isEmpty(vo.getMeasureUnitVO().getId())){
            throw new RuntimeException("请选择单位");
        }

        if (vo.getQty()==null || vo.getQty().compareTo(BigDecimal.ZERO)<=0){
            throw new RuntimeException("请输入购买数量");
        }
    }

    /**
     * 获取数据对象
     *
     * @param req
     * @return
     */
    private ShoppingCarVO getVO(HttpServletRequest req) {
        ShoppingCarVO vo = new ShoppingCarVO();

        // 计量单位
        MeasureUnitVO unitVO = new MeasureUnitVO();
        unitVO.setId(req.getParameter("measureUnit"));
        vo.setMeasureUnitVO(unitVO);

        // 物料
        MaterialVO materialVO = new MaterialVO();
        vo.setMaterialVO(materialVO);
        materialVO.setId(req.getParameter("material"));

        // 物料组
        MaterialGroupVO materialGroupVO = new MaterialGroupVO();
        materialVO.setMaterialGroupVO(materialGroupVO);
        materialGroupVO.setId(req.getParameter("materialGroup"));

        // 数量
        String qtyStr = req.getParameter("qty");
        vo.setQty(new BigDecimal(qtyStr));

        // 单价
        String priceStr = req.getParameter("price");
        vo.setQty(new BigDecimal(priceStr));

        // 金额
        String amountStr = req.getParameter("amount");
        vo.setQty(new BigDecimal(amountStr));

        return vo;
    }
}
