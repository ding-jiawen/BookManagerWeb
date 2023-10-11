package com.book.servlet.auth;

import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;
import com.book.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserService service;

    @Override
    public void init() throws ServletException {
        service = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        resp.setContentType("text/html;charset=UTF-8");
        if(req.getSession().getAttribute("login-failure") != null) {
            // 添加登录失败标记：您的用户名或密码输入不正确
            context.setVariable("failure", true);
            // 移除登录失败标记（重新进入页面消失）
            req.getSession().removeAttribute("login-failure");
        }
        if(req.getSession().getAttribute("user") != null) {
            resp.sendRedirect("login");
            return;
        }
        ThymeleafUtil.process("login.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember-me");
        // 判断是否登陆成功
        if(service.auth(username, password, req.getSession())) {
            resp.sendRedirect("index");
        }else {
            req.getSession().setAttribute("login-failure", new Object());
            this.doGet(req,resp);
        }
    }
}
