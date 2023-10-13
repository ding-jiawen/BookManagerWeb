package com.book.dao;

import com.book.entity.Book;
import com.book.entity.Borrow;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookMapper {

    // 将查询结果映射到Borrow类
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "bid", property = "book_id"),
            @Result(column = "title", property = "book_name"),
            @Result(column = "time", property = "time"),
            @Result(column = "name", property = "student_name"),
            @Result(column = "sid", property = "student_id")
    })
    @Select("select * from borrow, student, book where borrow.bid = book.bid and student.sid = borrow.sid")
    List<Borrow> getBorrowList();

    // 归还书籍
    @Delete("delete from borrow where id = #{id}")
    void deleteBorrow(String id);

    // 添加借阅信息
    @Insert("insert into borrow(sid, bid, time) values(#{sid}, #{bid}, NOW())")
    void addBorrow(@Param("sid")int sid, @Param("bid")int bid);
    @Select("select * from book")
    List<Book> getBookList();
}
