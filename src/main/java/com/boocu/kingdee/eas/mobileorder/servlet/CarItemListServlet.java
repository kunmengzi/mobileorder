package com.boocu.kingdee.eas.mobileorder.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.boocu.kingdee.eas.mobileorder.vo.CarItem;
import com.boocu.kingdee.eas.mobileorder.vo.JsonListResult;
import com.boocu.kingdee.eas.mobileorder.vo.JsonResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

        List<CarItem> cars = new ArrayList<CarItem>();
        for(int i=0; i<count; i++){
            CarItem item = new CarItem();
            item.setAuthor("author"+i);
            item.setDate(new Date());
            item.setId(i+1);
            item.setNum(String.valueOf(i+1));
            cars.add(item);
        }

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {

            JsonListResult<CarItem> jsonResult  = new JsonListResult<CarItem>();
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
