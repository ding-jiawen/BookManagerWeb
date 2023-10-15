package com.book.servlet.pages;

import com.book.entity.User;
import com.book.service.BookService;
import com.book.service.CountService;
import com.book.service.impl.BookServiceImpl;
import com.book.service.impl.CountServiceImpl;
import com.book.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    BookService service;
    CountService countService;

    @Override
    public void init() throws ServletException {
        service = new BookServiceImpl();
        countService = new CountServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        Context context = new Context();
        User user = (User) req.getSession().getAttribute("user");
        context.setVariable("student_count", countService.getStudentTotal());
        context.setVariable("book_count", countService.getBookTotal());
        context.setVariable("book_list", service.getActiveBook());
        context.setVariable("borrow_list", service.getBorrowList());
        ThymeleafUtil.process("index.html",context, resp.getWriter());
    }
}
