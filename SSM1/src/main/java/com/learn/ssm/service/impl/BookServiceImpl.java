package com.learn.ssm.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.learn.ssm.entity.Book;
import com.learn.ssm.mapper.IBookMapper;
import com.learn.ssm.service.IBookService;

@Service(value = "bookService")
public class BookServiceImpl implements IBookService
{
	@Resource(name = "bookMapper")
	private IBookMapper bookMapper;
	
	public BookServiceImpl()
	{
	}
	
	public List<Book> getBookList()
	{
		return this.bookMapper.selectAllBooks();
	}
}
