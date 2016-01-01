package com.boocu.kingdee.eas.mobileorder.servlet;

import com.boocu.kingdee.eas.mobileorder.Constants;
import com.boocu.kingdee.eas.mobileorder.dao.IDataQueryDao;
import com.boocu.kingdee.eas.mobileorder.dao.impl.DataQueryDaoServiceImpl;
import com.boocu.kingdee.eas.mobileorder.vo.JsonListResult;
import com.boocu.kingdee.eas.mobileorder.vo.MaterialGroupVO;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.ormapping.ObjectStringPK;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.base.permission.UserFactory;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.basedata.master.cssp.CustomerFactory;
import com.kingdee.eas.basedata.master.cssp.CustomerInfo;
import com.kingdee.eas.basedata.scm.sd.channel.ChannelBaseCollection;
import com.kingdee.eas.basedata.scm.sd.channel.ChannelBaseFactory;
import com.kingdee.eas.basedata.scm.sd.channel.ChannelBaseInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.util.app.ContextUtil;
import com.kingdee.util.StringUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.BooleanUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 类AbstractOrderServlet.java的实现描述：#TODO 类实现描述
 *
 * @author jordan 15/12/27 23:34
 */
public abstract class AbstractOrderServlet extends HttpServlet {

    // 日志信息
    protected static final Logger logger = Logger.getLogger("mobileorder");

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IDataQueryDao dataQueryDao = new DataQueryDaoServiceImpl(getContext(req));

        try {
            if (isMockCall(req)) {
                write(getMockData(dataQueryDao, req),resp);
            } else {
                write(getData(dataQueryDao, req),resp);
            }
        } catch (Exception e) {
            logger.error("查询数据出现异常", e);
        }
    }

    /**
     * 获取eas上下文
     *
     * @param req
     * @return
     */
    protected Context getContext(HttpServletRequest req){
        return (Context) req.getSession().getAttribute(Constants.ATTR_KEY_CONTEXT);
    }

    /**
     * 获取客户ID
     *
     * @param context
     * @return
     * @throws BOSException
     * @throws EASBizException
     */
    protected String getCustomerId(Context context) throws BOSException, EASBizException {
        if(null==context){
            return null;
        }

        UserInfo userInfo = ContextUtil.getCurrentUserInfo(context);
        if(null==userInfo){
            return null;
        }

        String customerId = userInfo.getCustomerID();
        if(!StringUtils.isEmpty(customerId)){
            return customerId;
        }

        //todo 通过用户获取客户ID
        SelectorItemCollection selectors = new SelectorItemCollection();
        selectors.add("id");
        selectors.add("customer.id");
        UserInfo tmpUserInfo = UserFactory.getLocalInstance(context).getUserInfo(new ObjectUuidPK(userInfo.getId()),selectors);
        if(null!=tmpUserInfo){
            customerId = tmpUserInfo.getCustomerID();
            userInfo.setCustomerID(customerId);
        }

        return customerId;
    }

    /**
     * 获取渠道信息
     *
     * @param context
     * @return
     * @throws EASBizException
     * @throws BOSException
     */
    protected ChannelBaseInfo getChannelBaseInfo(Context context) throws EASBizException, BOSException {
        String customerId = getCustomerId(context);

        EntityViewInfo entityViewInfo = new EntityViewInfo();
        FilterInfo filterInfo = new FilterInfo();
        filterInfo.getFilterItems().add(new FilterItemInfo("customer.id", customerId));
        entityViewInfo.setFilter(filterInfo);

        ChannelBaseCollection channelCol = ChannelBaseFactory.getLocalInstance(
                context).getChannelBaseCollection(entityViewInfo);

        if(null!=channelCol&&channelCol.size()>0){
            return channelCol.get(0);
        }else{
            return null;
        }
    }

    /**
     *  是否mock调用
     *
     * @param req
     * @return
     */
    private boolean isMockCall(HttpServletRequest req){
        String mock = req.getParameter("mock");
        if(Boolean.valueOf(mock)){
            return true;
        }else{
            return Constants.IS_MOCK;
        }
    }

    protected abstract Object getData(IDataQueryDao dataQueryDao, HttpServletRequest req) throws Exception;

    protected abstract Object getMockData(IDataQueryDao dataQueryDao, HttpServletRequest req) throws Exception;

    /**
     * 输出数据
     *
     * @param obj
     * @param response
     * @throws IOException
     */
    protected void write(Object obj,HttpServletResponse response) throws IOException {
        if (obj == null) {
            return;
        }

        response.setContentType(Constants.CONTENT_TYPE_JSON);
        response.setHeader(Constants.HEADER_KEY_CACHE_CONTROL, Constants.HEADER_VALUE_NO_CACHE);

        PrintWriter out = response.getWriter();
        if(obj instanceof Array || obj instanceof Collection){
            out.print(JSONArray.fromObject(obj).toString());
        }else {
            out.print(JSONObject.fromObject(obj).toString());
        }
        out.flush();
        out.close();

        return;
    }
}
