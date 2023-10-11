package com.book.service.impl;

import com.book.dao.UserMapper;
import com.book.entity.User;
import com.book.service.UserService;
import com.book.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpSession;

public class UserServiceImpl implements UserService {

    // 验证登录
    @Override
    public boolean auth(String username, String password, HttpSession session) {
        try(SqlSession sqlSession = MybatisUtil.getSession(true)) {
            UserMapper mapper =sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUser(username, password);
            if(user == null) return false;
            // 绑定一个key为"user", value = user 对象到一个session会话
            session.setAttribute("user", user);
            return true;
        }
    }
}
