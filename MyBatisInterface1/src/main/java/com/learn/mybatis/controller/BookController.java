package com.learn.mybatis.controller;

import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;

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

/* MyBatis */
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/* Entities for this application */
import com.learn.mybatis.entity.Book;
import com.learn.mybatis.mapper.IBookMapper;

/* 自定义库 */
import com.wrapper.Print;

@Controller
public class BookController
{
	public BookController()
	{
		// TODO Auto-generated constructor stub
		Print.info("调用控制器BookController的构造函数BookController()");
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
		
		//从MyBatis的全局配置文件中读取数据库链接信息并打开数据库链接:
		SqlSession session = this.getSqlSession("mybatis/mybatis-config.xml");
		if(session == null)
		{
			String title = "错误信息";
			String content = "不能打开数据库链接!";
			mav.addObject("title", title); 
			mav.addObject("content", content);
			mav.setViewName("error");
			return mav;
		}
		
	  //选择映射器:获取映射器接口对象实例;
		IBookMapper bookMapper = session.getMapper(IBookMapper.class);
		
		//调用接口映射器实例的方法,访问数据库:
		List<Book> bookList = bookMapper.selectAllBooks();
		
		//关闭数据库链接;
		session.close();
		
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
	public ModelAndView getInfo2(@PathVariable(value = "title") String title, @PathVariable(name = "content") String content)
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
	@RequestMapping(path = "/getInfo3", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getInfo3(@RequestParam(value = "title") String title, @RequestParam(name = "content") String content)
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
	@RequestMapping(path = "/getInfo4", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getInfo4(@ModelAttribute(name = "title") String title, @ModelAttribute(value = "content") String content)
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", title); 
		mav.addObject("content", content);
		mav.setViewName("message");
		return mav;
	}
	
	private SqlSession getSqlSession(String configFilePath)
	{
		SqlSession sqlSession = null;
		
		try
		{
			//加载MyBatis的全局配置信息:
			Reader mybatisConfiguration = Resources.getResourceAsReader(configFilePath);
			
			//实例化SqlSessionFactoryBuilder;
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory sqlSessionFactory = builder.build(mybatisConfiguration);
			
			//打开数据库链接:
			sqlSession =  sqlSessionFactory.openSession();
		}
		catch(Exception e)
		{
			sqlSession = null;
			Print.exce("打开数据库异常:%s", e.getMessage());
		}
		finally
		{}
		return sqlSession;
		
	}
}
