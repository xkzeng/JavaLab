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
		request.setAttribute("content", "ʹ��HttpRequestHandlerAdapter��䵱������ʱ,����д�Ŀ������඼����Ҫʵ��HttpRequestHandler�ӿ�;");
	  request.getRequestDispatcher("/WEB-INF/view/message.jsp").forward(request, response);
	}
}
