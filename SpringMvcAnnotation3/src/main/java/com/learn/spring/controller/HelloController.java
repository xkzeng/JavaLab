package com.learn.spring.controller;

import java.io.UnsupportedEncodingException;

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

@Controller
public class HelloController
{
	public HelloController()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 使用HttpServletRequest传递参数
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getInfo1", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getInfo1(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
	{
		request.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", request.getParameter("title")); 
		mav.addObject("content", request.getParameter("content"));
		mav.setViewName("message");
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
}
