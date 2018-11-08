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

/* 自定义库 */
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
		Print.info("调用控制器BookController的构造函数BookController() <- %s", SC.extract());
	}
	
	/**
	 * 使用HttpServletRequest传递参数
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getAllBooks", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getBookList(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
	{
		request.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		
		//调用接口映射器实例的方法,访问数据库:
		List<Book> bookList = bookService.getBookList();
		
		int number_of_books = bookList.size();
		if(number_of_books <= 0)
		{
			Print.warn("没有查询到图书,你还没有添加任何图书信息!");
			String title = "提示信息";
			String content = "你还没有添加任何图书信息!";
			mav.addObject("title", title); 
			mav.addObject("content", content);
			mav.setViewName("message");
			return mav;
		}
		
		//打印图书列表:
		Print.info("共有 %d 图书", number_of_books);
		for(Book book: bookList)
		{
			Print.info("图书编号 = %d, 图书名称 = %s, 图书作者 = %s", book.getBookId(), book.getBookName(), book.getAuthor());
		}
		
		//返回书籍列表:
		mav.addObject("number_of_books", number_of_books);
		mav.addObject("bookList", bookList);
    mav.setViewName("book_list");
		return mav;
	}
	
	/**
	 * 使用注解@PathVariable传递参数
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
	 * 使用注解@RequestParam传递参数
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
	 * 使用注解@ModelAttribute传递参数
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
