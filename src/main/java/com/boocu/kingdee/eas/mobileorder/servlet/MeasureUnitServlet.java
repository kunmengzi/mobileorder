package com.boocu.kingdee.eas.mobileorder.servlet;

import com.boocu.kingdee.eas.mobileorder.Constants;
import com.boocu.kingdee.eas.mobileorder.dao.IDataQueryDao;
import com.boocu.kingdee.eas.mobileorder.vo.JsonListResult;
import com.boocu.kingdee.eas.mobileorder.vo.MeasureUnitVO;
import com.kingdee.bos.Context;
import com.kingdee.util.StringUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jordan on 2015/11/30.
 */
public class MeasureUnitServlet extends AbstractOrderServlet {

    @Override
    protected Object getData(IDataQueryDao dataQueryDao, HttpServletRequest req) throws Exception {
        Context context = (Context) req.getSession().getAttribute(Constants.ATTR_KEY_CONTEXT);

        String materialId = req.getParameter("materialId");
        if (!StringUtils.isEmpty(materialId)) {
            List<MeasureUnitVO> list = new ArrayList<MeasureUnitVO>();
            list.add(new MeasureUnitVO());
            List<MeasureUnitVO> result = dataQueryDao.queryMeausreUnitByMaterialId(materialId);
            if (result != null) {
                list.addAll(result);
            }

            return list;
        } else {
            logger.warn("获取计量单位数据失败。物料ID为空。materialId=" + materialId);
            return null;
        }
    }

    @Override
    protected Object getMockData(IDataQueryDao dataQueryDao, HttpServletRequest req) throws Exception {
        long a = System.currentTimeMillis();
        int count = (int) (a % 10);

        List<MeasureUnitVO> cars = new ArrayList<MeasureUnitVO>();
        cars.add(new MeasureUnitVO());
        for (int i = 0; i < count; i++) {
            MeasureUnitVO item = new MeasureUnitVO();
            item.setId(String.valueOf(i + 10));
            item.setName("名称" + String.valueOf(i + 1));
            item.setNumber("code" + String.valueOf(i + 1));
            cars.add(item);
        }

        return cars;
    }
}
