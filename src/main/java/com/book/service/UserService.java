package com.book.service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface UserService {

    // 用户是否登录成功
    boolean auth(String username, String password, HttpSession session);
}
