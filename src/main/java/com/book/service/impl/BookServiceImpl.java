package com.book.service.impl;

import com.book.dao.BookMapper;
import com.book.dao.UserMapper;
import com.book.entity.Book;
import com.book.entity.Borrow;
import com.book.entity.User;
import com.book.service.BookService;
import com.book.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.*;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {
    @Override
    public List<Borrow> getBorrowList() {
        try(SqlSession sqlSession = MybatisUtil.getSession(true)) {
            BookMapper mapper =sqlSession.getMapper(BookMapper.class);
            return mapper.getBorrowList();
        }
    }

    @Override
    public void returnBook(String id) {
        try(SqlSession sqlSession = MybatisUtil.getSession(true)) {
            BookMapper mapper =sqlSession.getMapper(BookMapper.class);
            mapper.deleteBorrow(id);
        }
    }

    @Override
    public List<Book> getActiveBook() {
        Set<Integer> set = new HashSet<>();
        // 获取借阅列表
        this.getBorrowList().forEach(borrow -> set.add(borrow.getBook_id()));
        try(SqlSession sqlSession = MybatisUtil.getSession(true)) {
            BookMapper mapper =sqlSession.getMapper(BookMapper.class);
            // 不包含在借阅列表（没被借过的）中的图书才被查询出
            return mapper.getBookList()
                    .stream()
                    .filter(book -> !set.contains(book.getBid()))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Map<Book, Boolean> getBookList() {
        Set<Integer> set = new HashSet<>();
        // 获取借阅列表
        this.getBorrowList().forEach(borrow -> set.add(borrow.getBook_id()));
        try(SqlSession sqlSession = MybatisUtil.getSession(true)) {
            Map<Book, Boolean> map = new LinkedHashMap<>();
            BookMapper mapper =sqlSession.getMapper(BookMapper.class);
            // 存储书籍和它的借阅状态
            // set.contains() 判断集合是否含有指定的对象
            mapper.getBookList().forEach(book -> map.put(book, set.contains(book.getBid())));
            return map;
        }
    }

    @Override
    public void deleteBook(int bid) {
        try(SqlSession sqlSession = MybatisUtil.getSession(true)) {
            BookMapper mapper =sqlSession.getMapper(BookMapper.class);
            mapper.deleteBook(bid);
        }
    }

    @Override
    public void addBook(String title, String desc, double price) {
        try(SqlSession sqlSession = MybatisUtil.getSession(true)) {
            BookMapper mapper =sqlSession.getMapper(BookMapper.class);
            mapper.addBook(title, desc, price);
        }
    }

    @Override
    public void addBorrow(int sid, int bid) {
        try(SqlSession sqlSession = MybatisUtil.getSession(true)) {
            BookMapper mapper =sqlSession.getMapper(BookMapper.class);
            mapper.addBorrow(sid, bid);
        }
    }
}
