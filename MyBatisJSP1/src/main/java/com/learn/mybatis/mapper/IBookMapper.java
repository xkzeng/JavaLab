package com.learn.mybatis.mapper;

import java.util.List;
import com.learn.mybatis.entity.Book;

public interface IBookMapper
{
	public List<Book> selectAllBooks(); //函数名与标签<select>的id属性定义的函数名完全相同;
  public Book selectBookByBookId(int bookId);
}
