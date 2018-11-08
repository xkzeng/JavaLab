package com.learn.ssm.controller;

import java.util.List;
import java.io.UnsupportedEncodingException;
import java.util.List;
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

/* Entities for this application */
import com.learn.ssm.entity.Book;
import com.learn.ssm.service.IBookService;

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
		Print.info("���ÿ�����BookController�Ĺ��캯��BookController() <- %s", SC.extract());
	}
	
	/**
	 * ʹ��HttpServletRequest���ݲ���
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getAllBooks", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getBookList(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
	{
		request.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		
		//���ýӿ�ӳ����ʵ���ķ���,�������ݿ�:
		List<Book> bookList = bookService.getBookList();
		
		int number_of_books = bookList.size();
		if(number_of_books <= 0)
		{
			Print.warn("û�в�ѯ��ͼ��,�㻹û������κ�ͼ����Ϣ!");
			String title = "��ʾ��Ϣ";
			String content = "�㻹û������κ�ͼ����Ϣ!";
			mav.addObject("title", title); 
			mav.addObject("content", content);
			mav.setViewName("message");
			return mav;
		}
		
		//��ӡͼ���б�:
		Print.info("���� %d ͼ��", number_of_books);
		for(Book book: bookList)
		{
			Print.info("ͼ���� = %d, ͼ������ = %s, ͼ������ = %s", book.getBookId(), book.getBookName(), book.getAuthor());
		}
		
		//�����鼮�б�:
		mav.addObject("number_of_books", number_of_books);
		mav.addObject("bookList", bookList);
    mav.setViewName("book_list");
		return mav;
	}
	
	/**
	 * ʹ��ע��@PathVariable���ݲ���
	 */
	@RequestMapping(value = "/getInfo2/{title}/{content}", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getInfo2(@PathVariable(value = "title") String title, @PathVariable(value = "content") String content)
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", title); 
		mav.addObject("content", content);
		mav.setViewName("message");
		return mav;
	}
	
	/**
	 * ʹ��ע��@RequestParam���ݲ���
	 */
	@RequestMapping(value = "/getInfo3", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getInfo3(@RequestParam(value = "title") String title, @RequestParam(value = "content") String content)
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", title); 
		mav.addObject("content", content);
		mav.setViewName("message");
		return mav;
	}
	
	/**
	 * ʹ��ע��@ModelAttribute���ݲ���
	 */
	@RequestMapping(value = "/getInfo4", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getInfo4(@ModelAttribute(value = "title") String title, @ModelAttribute(value = "content") String content)
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", title); 
		mav.addObject("content", content);
		mav.setViewName("message");
		return mav;
	}
}
