package com.book.service.impl;

import com.book.dao.BookMapper;
import com.book.dao.CountMapper;
import com.book.service.CountService;
import com.book.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class CountServiceImpl implements CountService {
    @Override
    public int getStudentTotal() {
        try(SqlSession sqlSession = MybatisUtil.getSession(true)) {
            CountMapper mapper =sqlSession.getMapper(CountMapper.class);
            return mapper.getStudentTotal();
        }
    }

    @Override
    public int getBookTotal() {
        try(SqlSession sqlSession = MybatisUtil.getSession(true)) {
            CountMapper mapper =sqlSession.getMapper(CountMapper.class);
            return mapper.getBookTotal();
        }
    }
}
