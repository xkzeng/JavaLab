package com.learn.ssm.school.controller;

import java.util.List;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.annotation.Resource;

/* Servlet API */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* Spring */
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

/* Redis */
import redis.clients.jedis.Jedis;

/* JSON */
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/* Entities for this application */
import com.learn.ssm.school.entity.Publisher;
import com.learn.ssm.school.entity.Teacher;
import com.learn.ssm.school.entity.Book;
import com.learn.ssm.school.entity.Classes;
import com.learn.ssm.school.entity.MasterTeacher;
import com.learn.ssm.school.service.IBookService;

/* �Զ���� */
import com.wrapper.Print;
import com.wrapper.SC;

@Controller
public class BookController
{
	@Resource(name = "bookService")
	private IBookService bookService;
	
	public BookController()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * ʹ��HttpServletRequest���ݲ���
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/books", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getBooksList(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
	{
		request.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();

		//��ȡ���г�������Ϣ:
		List<Publisher> publisherList = this.bookService.selectAllPublishers();
		int number_of_publishers = publisherList.size();

		//��ȡ����ͼ����Ϣ:
		List<Book> bookList = this.bookService.selectAllBooks();
		int number_of_books = bookList.size();

		//���س������б�:
		mav.addObject("number_of_publishers", number_of_publishers);
		mav.addObject("publisherList", publisherList);

		//�����鼮�б�:
		mav.addObject("number_of_books", number_of_books);
		mav.addObject("bookList", bookList);

		mav.setViewName("books");
		return mav;
	}
	
	/**
	 * ���һ��������
	 */
	@RequestMapping(value = "/add_publisher", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String addPublisher(@RequestParam(value = "publisherId") int publisherId, @RequestParam(value = "publisherName") String publisherName)
	{
		Publisher publisher = new Publisher(publisherId, publisherName);
		publisher.dump();
		int result = this.bookService.insertPublisher(publisher);
		return "redirect:/books.do";
	}
	
	/**
	 * ɾ��һ��������
	 */
	@RequestMapping(value = "/del_publisher", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String delTeacher(@ModelAttribute(value = "id") int id)
	{
		int result = this.bookService.deletePublisherById(id);
		return "forward:/books.do";
	}
	
	/**
	 * ��ȡһ�����������Ϣ
	 */
	@RequestMapping(value = "/get_publisher/{id}", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getTeacher(@PathVariable(value = "id") int id)
	{
		Publisher publisher = this.bookService.selectPublisherById(id);
		
		ModelAndView mav = new ModelAndView("redirect:/books.do");
		mav.addObject("onePublisher", publisher);
		//mav.setViewName("books");
		return mav;
	}
	
	/**
	 * ���һ����
	 */
	@RequestMapping(value = "/add_book", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String addBook(@RequestParam(value = "bookId") int bookId, @RequestParam(value = "bookName") String bookName, @RequestParam(value = "bookAuthor") String bookAuthor, @RequestParam(value = "publisherId") int publisherId)
	{
		Publisher publisher = null;
		
		if(publisherId > 1000L && publisherId < 2000L)
		{
			publisher = new Publisher();
			publisher.setId(publisherId);
		}
		else
		{
			Print.warn("������ı�Ź������: %d", publisherId);
		}
		
		Book book = new Book();
		book.setId(bookId);
		book.setName(bookName);
		book.setAuthor(bookAuthor);
		book.setPublisher(publisher);
		book.dump();
		
		int result = this.bookService.insertBook(book);
		return "redirect:/books.do";
	}
	
	/**
	 * ɾ��һ����
	 */
	@RequestMapping(value = "/del_book/{id}", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String delBook(@PathVariable(value = "id") int id)
	{
		int result = this.bookService.deleteBookById(id);
		return "forward:/books.do";
	}
	
	/**
	 * ����һ����,���ܲ���ĳһ�ҳ����������;
	 */
	@RequestMapping(value = "/update_book", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String updateBook(@ModelAttribute(value = "bookId") int id, @ModelAttribute(value = "bookName") String name, @ModelAttribute(value = "bookAuthor") String author, @ModelAttribute(value = "publisherId") int publisherId)
	{
		Publisher publisher = null;
		
		if(publisherId > 1000L && publisherId < 2000L)
		{
			publisher = new Publisher();
			publisher.setId(publisherId);
		}
		else
		{
			Print.warn("������ı�Ź������: %d", publisherId);
		}
		
		Book book = new Book(id, name, author, publisher);
		int result = this.bookService.updateBook(book);
		return "forward:/books.do";
	}
	
	/**
	 * ��ȡһ��ͼ�����Ϣ
	 */
	@RequestMapping(value = "/get_book", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getBook(@RequestParam(value = "id") int id)
	{
		Book book = this.bookService.selectBookById(id);
		
		ModelAndView mav = new ModelAndView("redirect:/books.do");
		mav.addObject("oneBook", book);
		//mav.setViewName("books");
		return mav;
	}
}
