package com.learn.mybatis.multiview.mapper;

import java.util.List;
import com.learn.mybatis.multiview.entity.Book;

public interface IBookMapper
{
	public List<Book> selectAllBooks(); //���������ǩ<select>��id���Զ���ĺ�������ȫ��ͬ;
  public Book selectBookByBookId(int bookId);
}
