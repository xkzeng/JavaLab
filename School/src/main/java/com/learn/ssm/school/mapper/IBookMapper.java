package com.learn.ssm.school.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.learn.ssm.school.entity.Book;
import com.learn.ssm.school.entity.Publisher;

@Repository(value = "bookMapper")
public interface IBookMapper
{
	/* 出版社信息 */
  //增:
	public int insertPublisher(Publisher publisher);
	
	//删:
	public int deleteAllPublishers();
	public int deletePublishers(int ids[]);
	public int deletePublisher(Publisher publisher);
	public int deletePublisherById(int id);
	
	//查:
	public List<Publisher> selectAllPublishers();
	public Publisher selectPublisherById(int id);
	public List<Publisher> selectPublisherByName(String name);
	
	/* 图书信息 */
	//增:
	public int insertBook(Book book);
	
	//删:
	public int deleteAllBooks();
	public int deleteBooks(int ids[]);
	public int deleteBook(Book book);
	public int deleteBookById(int id);
	
	//改:
	public int updateBook(Book book);
	public int updateBookById(int id);
	
	//查:
	public List<Book> selectAllBooks();
	public Book selectBookById(int id);
	public List<Book> selectBookByName(String name);
}
