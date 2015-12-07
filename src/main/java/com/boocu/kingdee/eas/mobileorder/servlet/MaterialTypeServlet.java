package com.boocu.kingdee.eas.mobileorder.servlet;

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
public class MaterialTypeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        this.doPost(req,resp);
    }

    public MaterialTypeServlet() {
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        long a =  System.currentTimeMillis();
        int count =(int)(a%10);

        List<MaterialGroupVO> cars = new ArrayList<MaterialGroupVO>();
        cars.add(new MaterialGroupVO());
        for(int i=0; i<count; i++){
            MaterialGroupVO item = new MaterialGroupVO();
            item.setId(String.valueOf(i+1));
            item.setName("名称"+String.valueOf(i+1));
            item.setNumber("code" + String.valueOf(i+1));
            cars.add(item);
        }

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {

            JsonListResult<MaterialGroupVO> jsonResult  = new JsonListResult<MaterialGroupVO>();
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
