package com.learn.mybatis.mapper;

import java.util.List;
import com.learn.mybatis.entity.Book;

public interface IBookMapper
{
	public List<Book> selectAllBooks(); //���������ǩ<select>��id���Զ���ĺ�������ȫ��ͬ;
  public Book selectBookByBookId(int bookId);
}
