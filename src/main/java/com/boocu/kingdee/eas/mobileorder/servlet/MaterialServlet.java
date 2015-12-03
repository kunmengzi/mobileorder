package com.boocu.kingdee.eas.mobileorder.servlet;

import com.boocu.kingdee.eas.mobileorder.vo.JsonListResult;
import com.boocu.kingdee.eas.mobileorder.vo.MaterialTypeVO;
import com.boocu.kingdee.eas.mobileorder.vo.MaterialVO;

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
public class MaterialServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        this.doPost(req,resp);
    }

    public MaterialServlet() {
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        System.out.print("req.getParameter(\"materialType\") = " + req.getParameter("materialType"));

        long a =  System.currentTimeMillis();
        int count =(int)(a%10);

        List<MaterialVO> cars = new ArrayList<MaterialVO>();
        cars.add(new MaterialVO());
        for(int i=0; i<count; i++){
            MaterialVO item = new MaterialVO();
            item.setId(i+1);
            item.setName("名称"+String.valueOf(i+1));
            item.setCode("code" + String.valueOf(i+1));
            cars.add(item);
        }

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {

            JsonListResult<MaterialVO> jsonResult  = new JsonListResult<MaterialVO>();
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
