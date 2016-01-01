package com.boocu.kingdee.eas.mobileorder.servlet;

import com.boocu.kingdee.eas.mobileorder.Constants;
import com.boocu.kingdee.eas.mobileorder.dao.IDataQueryDao;
import com.boocu.kingdee.eas.mobileorder.vo.JsonListResult;
import com.boocu.kingdee.eas.mobileorder.vo.MaterialVO;
import com.kingdee.bos.Context;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.basedata.org.CtrlUnitInfo;
import com.kingdee.eas.util.app.ContextUtil;
import com.kingdee.util.StringUtils;

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
public class MaterialServlet extends AbstractOrderServlet {

    @Override
    protected Object getData(IDataQueryDao dataQueryDao, HttpServletRequest request) throws Exception {
        Context context = (Context) request.getSession().getAttribute(Constants.ATTR_KEY_CONTEXT);
        String materialGroupId = request.getParameter("materialGroupId");

        // 当前登录用户获取对应的客户ID
        UserInfo userInfo = ContextUtil.getCurrentUserInfo(context);
        String customerId = userInfo.getCustomerID();

        // CUID
        CtrlUnitInfo cu = ContextUtil.getCurrentCtrlUnit(context);

        if (!StringUtils.isEmpty(materialGroupId)) {
            List<MaterialVO> list = new ArrayList<MaterialVO>();
            list.add(new MaterialVO());
            // 获取物料列表
            List<MaterialVO> result = dataQueryDao.queryMaterialByGroupAndSaleOrgUnit(materialGroupId, customerId,
                                                                                      cu.getId().toString());
            if (result != null) {
                list.addAll(result);
            }

            return list;
        } else {
            logger.warn("获取物料数据失败。物料组ID为空。materialGroupId=" + materialGroupId);
            return null;
        }
    }

    @Override
    protected Object getMockData(IDataQueryDao dataQueryDao, HttpServletRequest req) throws Exception {
        long a = System.currentTimeMillis();
        int count = (int) (a % 10);

        List<MaterialVO> cars = new ArrayList<MaterialVO>();
        cars.add(new MaterialVO());
        for (int i = 0; i < count; i++) {
            MaterialVO item = new MaterialVO();
            item.setId(String.valueOf(i + 10));
            item.setName("名称" + String.valueOf(i + 1));
            item.setNumber("code" + String.valueOf(i + 1));
            cars.add(item);
        }

        return cars;
    }
}
