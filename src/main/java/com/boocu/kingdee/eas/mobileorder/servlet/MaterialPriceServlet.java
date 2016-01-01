package com.boocu.kingdee.eas.mobileorder.servlet;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.boocu.kingdee.eas.mobileorder.Constants;
import com.boocu.kingdee.eas.mobileorder.dao.IDataQueryDao;
import com.boocu.kingdee.eas.mobileorder.vo.MaterialVO;
import com.boocu.kingdee.eas.mobileorder.vo.MeasureUnitVO;
import com.kingdee.bos.Context;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.basedata.org.CtrlUnitInfo;
import com.kingdee.eas.util.app.ContextUtil;
import com.kingdee.util.StringUtils;

/**
 * Created by jordan on 2015/11/30.
 */
public class MaterialPriceServlet extends AbstractOrderServlet {

    @Override
    protected Object getData(IDataQueryDao dataQueryDao, HttpServletRequest request) throws Exception {
        Context context = (Context) request.getSession().getAttribute(Constants.ATTR_KEY_CONTEXT);
        String materialId = request.getParameter("materialId");

        // 计量单位列表
        String cuId = ContextUtil.getCurrentCtrlUnit(context).getId().toString();
        List<MeasureUnitVO> unitList = dataQueryDao.queryMeausreUnitByMaterialId(materialId);
        Map<String, MeasureUnitVO> map = list2Map(unitList);

        MaterialVO materialVO = dataQueryDao.getMaterialVO(materialId, cuId);

        // 和基本单价的换算关系
        BigDecimal baseRate = map.get(materialVO.getUnitId()).getBaseConvsRate();
        BigDecimal targetRate = map.get(request.getParameter("unitId")).getBaseConvsRate();

        //价格计算
        BigDecimal targetPrice = materialVO.getPrice().multiply(targetRate).divide(baseRate, 2, RoundingMode.HALF_UP);

        //返回值
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("price", targetPrice);
        return result;
    }

    private Map<String, MeasureUnitVO> list2Map(List<MeasureUnitVO> unitList) {
        Map<String, MeasureUnitVO> result = new HashMap<String, MeasureUnitVO>();
        if (null == unitList) {
            return result;
        }

        for (MeasureUnitVO vo : unitList) {
            result.put(vo.getId(), vo);
        }

        return result;
    }

    @Override
    protected Object getMockData(IDataQueryDao dataQueryDao, HttpServletRequest req) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("price", new BigDecimal("123.423"));
        return result;
    }
}
