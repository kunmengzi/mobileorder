package com.boocu.kingdee.eas.mobileorder.servlet;

import com.boocu.kingdee.eas.mobileorder.vo.*;

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
public class NetOrderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        this.doPost(req,resp);
    }

    public NetOrderServlet() {
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        long a =  System.currentTimeMillis();
        int count =(int)(a%10);

        List<NetOrderVO> cars = new ArrayList<NetOrderVO>();
        for(int i=0; i<count; i++){
            NetOrderVO vo = new NetOrderVO();
            vo.setAmount(new BigDecimal(100.00 * i));
            vo.setAskArriveDate(new Date());
            vo.setBillStatus(1);
            if(1%2==0)
            vo.setBillStatusDesc("审核");
            else
            vo.setBillStatusDesc("完成");

            vo.setBizDate(new Date());
            vo.setChannelVO(new ChannelVO(String.valueOf(i + 1), "渠道A"));

            vo.setIsFightBack(false);

            vo.setNumber("单据编号" + i);
            vo.setQty(new BigDecimal(23 * i));
            vo.setSaleOrgUnitVO(new SaleOrgUnitVO("afbd", "dfasdfasdf"));
            vo.setCustomerVO(new CustomerVO("dfsa","fadsfasfasd"));

            cars.add(vo);
        }

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {

            JsonListResult<NetOrderVO> jsonResult  = new JsonListResult<NetOrderVO>();
            jsonResult.setData(cars);

            out = resp.getWriter();
            out.append(jsonResult.toJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
