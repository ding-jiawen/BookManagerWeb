package com.book.service;

import com.book.entity.Book;
import com.book.entity.Borrow;

import java.util.List;
import java.util.Map;

public interface BookService {
    List<Borrow> getBorrowList(); // 获取借阅信息
    void returnBook(String id); // 归还书籍

    List<Book> getActiveBook(); // 获取未被借阅的书籍
    void addBorrow(int sid, int bid); // 添加借阅信息

    Map<Book, Boolean> getBookList(); // 获取全部书籍

    void deleteBook(int bid); // 删除书籍

    void addBook(String title, String desc, double price); // 录入书籍
}
