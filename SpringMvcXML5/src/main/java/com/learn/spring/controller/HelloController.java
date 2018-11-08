package com.learn.spring.controller;

/* Servlet API */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* Spring */
import org.springframework.web.servlet.ModelAndView;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.mvc.Controller;

public class HelloController implements Controller
{
	public HelloController()
	{
		// TODO Auto-generated constructor stub
	}
	
	@Nullable
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		
	  //��ָ̬����Ҫ���ص���ͼ��;��ͼ���Ĺ�����SpringMVC��ȫ�������ļ�spring-mvc.xml�е���ͼ���������Ѿ����ú�;
		String flag = "1"; //Ĭ��ѡ����ͼ1
		try
		{
			flag = request.getParameter("flag");
		}
		catch(Exception e)
		{
			
		}
		
		if(flag.equals("1"))
		{
			mav.setViewName("message1"); //mav.setViewName("/WEB-INF/view/message1.jsp");
		}
		else if(flag.equals("2"))
		{
			mav.setViewName("message2"); //mav.setViewName("/WEB-INF/view/message2.jsp");
		}
		else
		{
			mav.addObject("title", "����"); 
			mav.addObject("content", "����ͼ������");
			mav.setViewName("error");
		}
		return mav;
	}
}
