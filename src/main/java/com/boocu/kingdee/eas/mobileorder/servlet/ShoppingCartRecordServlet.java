package com.boocu.kingdee.eas.mobileorder.servlet;

import com.boocu.kingdee.eas.mobileorder.dao.IDataQueryDao;
import com.boocu.kingdee.eas.mobileorder.vo.MaterialVO;
import com.boocu.kingdee.eas.mobileorder.vo.MeasureUnitVO;
import com.boocu.kingdee.eas.mobileorder.vo.ShoppingCarVO;
import com.boocu.kingdee.eas.mobileorder.vo.JsonListResult;
import com.kingdee.bos.Context;
import com.kingdee.eas.basedata.scm.sd.channel.ChannelBaseInfo;
import com.kingdee.eas.util.app.ContextUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jordan on 2015/11/30.
 */
public class ShoppingCartRecordServlet extends AbstractOrderServlet {

    @Override
    protected Object getData(IDataQueryDao dataQueryDao, HttpServletRequest req) throws Exception {
        Context ctx = getContext(req);
        String userId = ContextUtil.getCurrentUserInfo(ctx).getId().toString();
        String saleOrgUnitId = ContextUtil.getCurrentSaleUnit(ctx).getId().toString();

        ChannelBaseInfo channelInfo = getChannelBaseInfo(ctx);
        String channelId = channelInfo==null?null:channelInfo.getId().toString();

        return dataQueryDao.queryShoppingCarList(userId,saleOrgUnitId,channelId);
    }

    @Override
    protected Object getMockData(IDataQueryDao dataQueryDao, HttpServletRequest req) throws Exception {
        long a =  System.currentTimeMillis();
        int count =(int)(a%10);

        List<ShoppingCarVO> cars = new ArrayList<ShoppingCarVO>();
        for(int i=0; i<count; i++){
            ShoppingCarVO vo = new ShoppingCarVO();
            vo.setId(String.valueOf(i + 1));
            vo.setQty(new BigDecimal(234 * i));
            vo.setAmount(new BigDecimal(230 * i));
            vo.setChannel("渠道名称 ");
            vo.setPrice(new BigDecimal(133));
            vo.setShopDate(new Date());
            vo.setSaleOrgUnitId("dsafsdfasdfas");
            MaterialVO material = new MaterialVO();
            material.setId(String.valueOf(i));
            material.setName(String.valueOf(i));
            vo.setMaterialVO(material);
            MeasureUnitVO unit = new MeasureUnitVO();
            unit.setId(String.valueOf(i));
            unit.setName(String.valueOf(i));
            vo.setMeasureUnitVO(unit);

            cars.add(vo);
        }

        return cars;
    }
}
