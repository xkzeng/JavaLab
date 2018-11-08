package com.learn.ssm.service.impl;

import java.util.List;
//import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.learn.ssm.entity.Book;
import com.learn.ssm.mapper.IBookMapper;
import com.learn.ssm.service.IBookService;

@Service(value = "bookService") @Transactional
public class BookServiceImpl implements IBookService
{
	//@Resource(name = "bookMapper")
	@Autowired(required = true)
	private IBookMapper bookMapper;
	
	public BookServiceImpl()
	{
	}
	
	public List<Book> getBookList()
	{
		return this.bookMapper.selectAllBooks();
	}
}
