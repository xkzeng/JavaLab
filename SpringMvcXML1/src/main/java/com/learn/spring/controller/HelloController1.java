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
		mav.addObject("content", "使用SimpleControllerHandlerAdapter类充当适配器时,所编写的控制器类都必须要实现Controller接口");
		mav.setViewName("/WEB-INF/view/message.jsp");
		return mav;
	}
}
