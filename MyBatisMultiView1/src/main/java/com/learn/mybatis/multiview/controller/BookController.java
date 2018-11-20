package com.learn.mybatis.multiview.controller;

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
import com.learn.mybatis.multiview.entity.Book;
import com.learn.mybatis.multiview.mapper.IBookMapper;

/* �Զ���� */
import com.wrapper.Print;

@Controller
public class BookController
{
	public BookController()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * ʹ��Thymeleaf��ͼ��������ʾҳ��
	 */
	@RequestMapping(value = "/get_books_thy", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView showBookListWithThymeleaf(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
	{
		request.setCharacterEncoding("UTF-8");
		
		String environment = "development";
		try
		{
			environment = request.getParameter("envId");
		}
		catch(Exception ex)
		{
			environment = "development";
		}
		
		ModelAndView mav = new ModelAndView();
		
		//��MyBatis��ȫ�������ļ��ж�ȡ���ݿ�������Ϣ�������ݿ�����:
		SqlSession session = this.getSqlSession("mybatis/mybatis-config.xml", environment);
		if(session == null)
		{
			String title = "������Ϣ";
			String content = "���ܴ����ݿ�����:" + environment;
			mav.addObject("title", title); 
			mav.addObject("content", content);
			mav.setViewName("error");
			return mav;
		}
		
	  //ѡ��ӳ����:��ȡӳ�����ӿڶ���ʵ��;
		IBookMapper bookMapper = session.getMapper(IBookMapper.class);
		
		//���ýӿ�ӳ����ʵ���ķ���,�������ݿ�:
		List<Book> bookList = bookMapper.selectAllBooks();
		
		//�ر����ݿ�����;
		session.close();
		
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
    mav.setViewName("thymeleaf/book_list"); //�ؼ���
		return mav;
	}
	
	/**
	 * ʹ��FreeMarker��ͼ��������ʾҳ��
	 */
	@RequestMapping(value = "/get_books_ftl", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView showBookListWithFreeMarker()
	{
		ModelAndView mav = new ModelAndView();
		
		String environment = "development";
		
		//��MyBatis��ȫ�������ļ��ж�ȡ���ݿ�������Ϣ�������ݿ�����:
		SqlSession session = this.getSqlSession("mybatis/mybatis-config.xml", environment);
		if(session == null)
		{
			String title = "������Ϣ";
			String content = "���ܴ����ݿ�����:" + environment;
			mav.addObject("title", title); 
			mav.addObject("content", content);
			mav.setViewName("book_error");
			return mav;
		}
		
	  //ѡ��ӳ����:��ȡӳ�����ӿڶ���ʵ��;
		IBookMapper bookMapper = session.getMapper(IBookMapper.class);
		
		//���ýӿ�ӳ����ʵ���ķ���,�������ݿ�:
		List<Book> bookList = bookMapper.selectAllBooks();
		
		//�ر����ݿ�����;
		session.close();
		
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
		mav.setViewName("freemarker/book_list"); //�ؼ���
		return mav;
	}
	
	/**
	 * ʹ��JSP/JSTL��Ϊģ����ͼ����:
	 */
	@RequestMapping(value = "/get_books_jsp", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView showBookListWithJspJstl()
	{
		ModelAndView mav = new ModelAndView();
		
		String environment = "development";
		
		//��MyBatis��ȫ�������ļ��ж�ȡ���ݿ�������Ϣ�������ݿ�����:
		SqlSession session = this.getSqlSession("mybatis/mybatis-config.xml", environment);
		if(session == null)
		{
			String title = "������Ϣ";
			String content = "���ܴ����ݿ�����:" + environment;
			mav.addObject("title", title); 
			mav.addObject("content", content);
			mav.setViewName("book_error");
			return mav;
		}
		
	  //ѡ��ӳ����:��ȡӳ�����ӿڶ���ʵ��;
		IBookMapper bookMapper = session.getMapper(IBookMapper.class);
		
		//���ýӿ�ӳ����ʵ���ķ���,�������ݿ�:
		List<Book> bookList = bookMapper.selectAllBooks();
		
		//�ر����ݿ�����;
		session.close();
		
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
		mav.setViewName("jsp/book_list"); //�ؼ���
		return mav;
	}
	
	private SqlSession getSqlSession(String configFilePath, String environment)
	{
		SqlSession sqlSession = null;
		
		try
		{
			//����MyBatis��ȫ��������Ϣ:
			Reader mybatisConfiguration = Resources.getResourceAsReader(configFilePath);
			
			//ʵ����SqlSessionFactoryBuilder;
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory sqlSessionFactory = builder.build(mybatisConfiguration, environment);
			
			//�����ݿ�����:
			sqlSession =  sqlSessionFactory.openSession();
		}
		catch(Exception e)
		{
			sqlSession = null;
			Print.exce("�����ݿ��쳣:%s", e.getMessage());
		}
		finally
		{}
		return sqlSession;
		
	}
}
