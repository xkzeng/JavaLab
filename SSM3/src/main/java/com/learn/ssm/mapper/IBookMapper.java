package com.learn.ssm.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.learn.ssm.entity.Book;

@Repository(value = "bookMapper")
public interface IBookMapper
{
	public List<Book> selectAllBooks(); //���������ǩ<select>��id���Զ���ĺ�������ȫ��ͬ;
  public Book selectBookByBookId(int bookId);
}
