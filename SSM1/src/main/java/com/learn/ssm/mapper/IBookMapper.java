package com.learn.ssm.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.learn.ssm.entity.Book;

@Repository(value = "bookMapper")
public interface IBookMapper
{
	public List<Book> selectAllBooks(); //函数名与标签<select>的id属性定义的函数名完全相同;
  public Book selectBookByBookId(int bookId);
}
