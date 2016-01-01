package com.boocu.kingdee.eas.mobileorder.servlet;

import com.boocu.kingdee.eas.mobileorder.Constants;
import com.boocu.kingdee.eas.mobileorder.dao.IDataQueryDao;
import com.boocu.kingdee.eas.mobileorder.vo.JsonListResult;
import com.boocu.kingdee.eas.mobileorder.vo.MaterialGroupVO;

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
public class MaterialGroupServlet extends AbstractOrderServlet {

    @Override
    protected Object getData(IDataQueryDao dataQueryDao, HttpServletRequest req) throws Exception {
        List<MaterialGroupVO> list = new ArrayList<MaterialGroupVO>();
        list.add(new MaterialGroupVO());

        List<MaterialGroupVO> result = dataQueryDao.queryMaterialGroupByStandard(Constants.MATERIAL_GROUP_STANDARD_ID);
        if (null != result) {
            list.addAll(result);
        }

        return list;
    }

    @Override
    protected Object getMockData(IDataQueryDao dataQueryDao, HttpServletRequest req) throws Exception {
        long a = System.currentTimeMillis();
        int count = (int) (a % 10);

        List<MaterialGroupVO> list = new ArrayList<MaterialGroupVO>();
        list.add(new MaterialGroupVO());
        for (int i = 0; i < count; i++) {
            MaterialGroupVO item = new MaterialGroupVO();
            item.setId(String.valueOf(i + 10));
            item.setName("名称" + String.valueOf(i + 1));
            item.setNumber("code" + String.valueOf(i + 1));
            list.add(item);
        }

        return list;
    }
}
