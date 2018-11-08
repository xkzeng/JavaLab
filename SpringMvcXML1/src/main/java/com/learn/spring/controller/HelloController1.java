package com.learn.spring.controller;

/* Servlet API */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* Spring */
import org.springframework.web.servlet.ModelAndView;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.mvc.Controller;

public class HelloController1 implements Controller
{
	public HelloController1()
	{
		// TODO Auto-generated constructor stub
	}
	
	@Nullable
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "SimpleControllerHandlerAdapter + BeanNameUrlHandlerMapping + Controller"); 
		mav.addObject("content", "ʹ��SimpleControllerHandlerAdapter��䵱������ʱ,����д�Ŀ������඼����Ҫʵ��Controller�ӿ�");
		mav.setViewName("/WEB-INF/view/message.jsp");
		return mav;
	}
}
