package com.boocu.kingdee.eas.mobileorder.servlet;

import com.boocu.kingdee.eas.mobileorder.dao.IDataQueryDao;
import com.boocu.kingdee.eas.mobileorder.vo.*;
import com.kingdee.bos.Context;
import com.kingdee.eas.basedata.scm.sd.channel.ChannelBaseInfo;
import com.kingdee.eas.util.app.ContextUtil;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jordan on 2015/11/30.
 */
public class NetOrderServlet extends AbstractOrderServlet {

    @Override
    protected Object getData(IDataQueryDao dataQueryDao, HttpServletRequest req) throws Exception {
        Context ctx = getContext(req);
        String userId = ContextUtil.getCurrentUserInfo(ctx).getId().toString();
        String saleOrgUnitId = ContextUtil.getCurrentSaleUnit(ctx).getId().toString();

        ChannelBaseInfo channelInfo = getChannelBaseInfo(ctx);
        String channelId = channelInfo==null?null:channelInfo.getId().toString();

        return dataQueryDao.queryNetOrderList(userId, saleOrgUnitId, channelId);
    }

    @Override
    protected Object getMockData(IDataQueryDao dataQueryDao, HttpServletRequest req) throws Exception {
        long a =  System.currentTimeMillis();
        int count =(int)(a%10);

        List<NetOrderVO> cars = new ArrayList<NetOrderVO>();
        for(int i=0; i<count; i++){
            NetOrderVO vo = new NetOrderVO();
            vo.setAmount(new BigDecimal(100.00 * i));
            vo.setAskArriveDate(new Date());
            vo.setBillStatus(1);
            if(i%2==0) {
                vo.setBillStatusDesc("审核");
                vo.setIsFightBack(true);
            }else {
                vo.setBillStatusDesc("完成");
                vo.setIsFightBack(false);
            }

            vo.setBizDate(new Date());
            vo.setChannelVO(new ChannelVO(String.valueOf(i + 1), "渠道A"));

//            vo.setIsFightBack(false);

            vo.setNumber("单据编号" + i);
            vo.setQty(new BigDecimal(23 * i));
            vo.setSaleOrgUnitVO(new SaleOrgUnitVO("afbd", "dfasdfasdf"));
            vo.setCustomerVO(new CustomerVO("dfsa", "fadsfasfasd"));
            vo.setId(String.valueOf(i));
            cars.add(vo);
        }

        return cars;
    }
}
