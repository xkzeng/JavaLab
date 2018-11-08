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
		
	  //动态指定需要返回的视图名;视图名的规则在SpringMVC的全局配置文件spring-mvc.xml中的视图解析器中已经配置好;
		String flag = "1"; //默认选择视图1
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
			mav.addObject("title", "错误"); 
			mav.addObject("content", "该视图不存在");
			mav.setViewName("error");
		}
		return mav;
	}
}
