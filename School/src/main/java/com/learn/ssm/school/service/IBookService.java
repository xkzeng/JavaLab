package com.learn.ssm.school.service;

import java.util.List;
import com.learn.ssm.school.entity.Book;
import com.learn.ssm.school.entity.Publisher;

public interface IBookService
{
	/* ��������Ϣ */
  //��:
	public int insertPublisher(Publisher publisher);
	
	//ɾ:
	public int deleteAllPublishers();
	public int deletePublishers(int ids[]);
	public int deletePublisher(Publisher publisher);
	public int deletePublisherById(int id);
	
	//��:
	public List<Publisher> selectAllPublishers();
	public Publisher selectPublisherById(int id);
	public List<Publisher> selectPublisherByName(String name);
	
	/* ͼ����Ϣ */
	//��:
	public int insertBook(Book book);
	
	//ɾ:
	public int deleteAllBooks();
	public int deleteBooks(int ids[]);
	public int deleteBook(Book book);
	public int deleteBookById(int id);
	
	//��:
	public int updateBook(Book book);
	public int updateBookById(int id);
	
	//��:
	public List<Book> selectAllBooks();
	public Book selectBookById(int id);
	public List<Book> selectBookByName(String name);
}
