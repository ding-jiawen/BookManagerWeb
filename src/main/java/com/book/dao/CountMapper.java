package com.book.dao;

import org.apache.ibatis.annotations.Select;

public interface CountMapper {
    @Select("select count(*) from student")
    int getStudentTotal();

    @Select("select count(*) from book")
    int getBookTotal();
}
