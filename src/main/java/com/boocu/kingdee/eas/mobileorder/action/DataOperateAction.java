package com.boocu.kingdee.eas.mobileorder.action;

import com.boocu.kingdee.eas.mobileorder.dao.IDataQueryDao;
import com.kingdee.bos.Context;
import com.kingdee.eas.mobilecommon.struts2.action.BaseMobileAction;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by jordan on 15/12/6.
 */
public class DataOperateAction extends BaseMobileAction{

    //日志信息
    private static final Logger logger = Logger.getLogger(DataOperateAction.class);

    private static final String ATTR_KEY_CONTEXT = "XTContext";
    private static final String CONTENT_TYPE_JSON = "application/json;charset=utf-8";
    private static final String HEADER_KEY_CACHE_CONTROL = "cache-control";
    private static final String HEADER_VALUE_NO_CACHE = "no-cache";

    private IDataQueryDao dataQueryDao;

    /**
     * 添加购物车
     *
     * @return
     * @throws Exception
     */
    public String addShoppingCar() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        Context context = (Context) request.getSession().getAttribute(ATTR_KEY_CONTEXT);

//        List<MaterialGroupVO> result = dataQueryDao.queryMaterialGroupByStandard(MATERIAL_GROUP_STANDARD_ID);
//        write(result);

//        ContextUtil.getcurrentch
//
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
