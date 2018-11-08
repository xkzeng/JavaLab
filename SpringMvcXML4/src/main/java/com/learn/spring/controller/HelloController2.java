package com.learn.spring.controller;

/* Common Package */
import java.io.IOException;

/* Servlet API */
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* Spring */
import org.springframework.web.HttpRequestHandler;;

public class HelloController2 implements HttpRequestHandler
{
	public HelloController2()
	{
		// TODO Auto-generated constructor stub
	}
	
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setAttribute("title", "HttpRequestHandlerAdapter + SimpleUrlHandlerMapping + HttpRequestHandler");
		request.setAttribute("content", "使用HttpRequestHandlerAdapter类充当适配器时,所编写的控制器类都必须要实现HttpRequestHandler接口;");
	  request.getRequestDispatcher("/WEB-INF/view/message.jsp").forward(request, response);
	}
}
