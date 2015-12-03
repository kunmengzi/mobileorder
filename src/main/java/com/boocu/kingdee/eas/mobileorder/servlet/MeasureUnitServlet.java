package com.boocu.kingdee.eas.mobileorder.servlet;

import com.boocu.kingdee.eas.mobileorder.vo.JsonListResult;
import com.boocu.kingdee.eas.mobileorder.vo.MaterialTypeVO;
import com.boocu.kingdee.eas.mobileorder.vo.MeasureUnitVO;

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
public class MeasureUnitServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        this.doPost(req,resp);
    }

    public MeasureUnitServlet() {
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        long a =  System.currentTimeMillis();
        int count =(int)(a%10);

        List<MeasureUnitVO> cars = new ArrayList<MeasureUnitVO>();
        cars.add(new MeasureUnitVO());
        for(int i=0; i<count; i++){
            MeasureUnitVO item = new MeasureUnitVO();
            item.setId(i+1);
            item.setName("名称"+String.valueOf(i+1));
            item.setCode("code" + String.valueOf(i+1));
            cars.add(item);
        }

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {

            JsonListResult<MeasureUnitVO> jsonResult  = new JsonListResult<MeasureUnitVO>();
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
