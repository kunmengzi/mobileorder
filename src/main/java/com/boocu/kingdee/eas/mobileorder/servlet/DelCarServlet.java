package com.boocu.kingdee.eas.mobileorder.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boocu.kingdee.eas.mobileorder.dao.IDataQueryDao;
import com.boocu.kingdee.eas.mobileorder.vo.MaterialGroupVO;
import com.boocu.kingdee.eas.mobileorder.vo.MaterialVO;
import com.boocu.kingdee.eas.mobileorder.vo.MeasureUnitVO;
import com.boocu.kingdee.eas.mobileorder.vo.ShoppingCarVO;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.ormapping.ObjectStringPK;
import com.kingdee.eas.dynbusiness.util.StringUtil;
import com.kingdee.eas.scm.sd.channel.ShoppingCartRecordFactory;

/**
 * Created by jordan on 2015/11/30.
 */
public class DelCarServlet extends AbstractOrderServlet {

    @Override
    protected Object getData(IDataQueryDao dataQueryDao, HttpServletRequest req) throws Exception {
        String id = req.getParameter("id");
        System.out.println("id=" + id);

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            IObjectPK pk = new ObjectStringPK(id);
            ShoppingCartRecordFactory.getLocalInstance(getContext(req)).delete(pk);

            result.put("success", true);
            result.put("message", "移除商品成功");
        } catch (Exception e) {
            logger.error("删除购物车发生异常", e);
            result.put("success", false);
            result.put("message", "请稍后重试或联系管理员");
        }

        return result;
    }

    @Override
    protected Object getMockData(IDataQueryDao dataQueryDao, HttpServletRequest req) throws Exception {
        System.out.print(req.getParameter("id"));

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);
        result.put("message", "添加商品成功");

        return result;
    }
}
