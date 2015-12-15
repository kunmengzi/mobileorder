package com.boocu.kingdee.eas.mobileorder.servlet;

import com.boocu.kingdee.eas.mobileorder.vo.MaterialVO;
import com.boocu.kingdee.eas.mobileorder.vo.MeasureUnitVO;
import com.boocu.kingdee.eas.mobileorder.vo.ShoppingCarVO;
import com.boocu.kingdee.eas.mobileorder.vo.JsonListResult;

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
public class CarItemListServlet  extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        this.doPost(req,resp);
    }

    public CarItemListServlet() {
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
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

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {

            JsonListResult<ShoppingCarVO> jsonResult  = new JsonListResult<ShoppingCarVO>();
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
