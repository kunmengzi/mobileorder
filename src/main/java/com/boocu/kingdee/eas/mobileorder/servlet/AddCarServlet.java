package com.boocu.kingdee.eas.mobileorder.servlet;

import com.boocu.kingdee.eas.mobileorder.vo.JsonListResult;
import com.boocu.kingdee.eas.mobileorder.vo.MaterialVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jordan on 2015/11/30.
 */
public class AddCarServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        this.doPost(req,resp);
    }

    public AddCarServlet() {
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        req.setCharacterEncoding("UTF8");
       System.out.println(req.getParameter("amount"));
        System.out.println(req.getParameter("material"));
        System.out.println(req.getParameter("materialType"));
        System.out.println(req.getParameter("measureUnit"));
        System.out.println(req.getParameter("num"));
        System.out.println(req.getParameter("price"));
        System.out.println(new String(req.getParameter("test").getBytes("utf8")));
        System.out.println(new String(req.getParameter("test").getBytes(),"utf8"));
        System.out.println(new String(req.getParameter("test").getBytes("ISO8859-1"),"utf8"));
        System.out.println(new String(req.getParameter("test").getBytes("utf8"),"ISO8859-1"));
        System.out.println(URLDecoder.decode(req.getParameter("test"),"utf8") );
        System.out.println(URLDecoder.decode(req.getParameter("test"),"ISO8859-1") );
        System.out.println(URLDecoder.decode(req.getParameter("test"),"utf8") );
        System.out.println(URLDecoder.decode(req.getParameter("test"),"ISO8859-1") );

        System.out.print("中文ssss");

        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println("保存成功啦");
    }
}
