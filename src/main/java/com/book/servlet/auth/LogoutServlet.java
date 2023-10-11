package com.book.servlet.auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    // logout也在过滤范围内，登录之后才能调用该接口
    // 所以能进来的肯定是已经验证过的用户
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 清除session，login页面中已经重定向过
        req.getSession().removeAttribute("user");
        resp.sendRedirect("login");
    }
}
