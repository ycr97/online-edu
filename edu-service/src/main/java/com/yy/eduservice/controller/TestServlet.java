package com.yy.eduservice.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ycr
 * @date 2020/5/31
 */
@WebServlet(name = "test", urlPatterns = "/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.write("hello");

    }

    @Override
    public void destroy() {
//        super.destroy();
        System.out.println("销毁");
    }

    @Override
    public void init() throws ServletException {
//        super.init();
        System.out.println("初始化");
    }

    public TestServlet() {
        System.out.println("构造方法");
    }

}
