package com.learn.ssm.school.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.learn.ssm.school.entity.Book;
import com.learn.ssm.school.entity.Publisher;
import com.learn.ssm.school.mapper.IBookMapper;
import com.learn.ssm.school.service.IBookService;

@Service(value = "bookService")
public class BookServiceImpl implements IBookService
{
	@Resource(name = "bookMapper")
	private IBookMapper bookMapper;
	
	public BookServiceImpl()
	{
	}
	
	/* 出版社信息 */
  //增:
	public int insertPublisher(Publisher publisher)
	{
		return this.bookMapper.insertPublisher(publisher);
	}
	
	//删:
	public int deleteAllPublishers()
	{
		return this.bookMapper.deleteAllPublishers();
	}
	
	public int deletePublishers(int ids[])
	{
		return this.bookMapper.deletePublishers(ids);
	}
	
	public int deletePublisher(Publisher publisher)
	{
		return this.bookMapper.deletePublisher(publisher);
	}
	
	public int deletePublisherById(int id)
	{
		return this.bookMapper.deletePublisherById(id);
	}
	
	//查:
	public List<Publisher> selectAllPublishers()
	{
		return this.bookMapper.selectAllPublishers();
	}
	
	public Publisher selectPublisherById(int id)
	{
		return this.bookMapper.selectPublisherById(id);
	}
	
	public List<Publisher> selectPublisherByName(String name)
	{
		return this.bookMapper.selectPublisherByName(name);
	}
	
	/* 图书信息 */
	//增:
	public int insertBook(Book book)
	{
		return this.bookMapper.insertBook(book);
	}
	
	//删:
	public int deleteAllBooks()
	{
		return this.bookMapper.deleteAllBooks();
	}
	
	public int deleteBooks(int ids[])
	{
		return this.bookMapper.deleteBooks(ids);
	}
	
	public int deleteBook(Book book)
	{
		return this.bookMapper.deleteBook(book);
	}
	
	public int deleteBookById(int id)
	{
		return this.bookMapper.deleteBookById(id);
	}
	
	//改:
	public int updateBook(Book book)
	{
		return this.bookMapper.updateBook(book);
	}
	
	public int updateBookById(int id)
	{
		return this.bookMapper.updateBookById(id);
	}
	
	//查:
	public List<Book> selectAllBooks()
	{
		return this.bookMapper.selectAllBooks();
	}
	
	public Book selectBookById(int id)
	{
		return this.bookMapper.selectBookById(id);
	}
	
	public List<Book> selectBookByName(String name)
	{
		return this.bookMapper.selectBookByName(name);
	}
}
