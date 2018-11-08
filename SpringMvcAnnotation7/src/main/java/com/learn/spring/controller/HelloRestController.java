package com.learn.spring.controller;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/* Servlet API */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* Spring */
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

@RestController
@RequestMapping(value = "/api/*")
public class HelloRestController
{
	private static final String HTML_TEMPLATE = "<CENTER><TABLE>"
			+ "<TR><TH><FONT face=新宋体 size=4>%s</FONT></TH></TR>"
			+ "<TR><TD><FONT face=新宋体 size=3>%s</FONT></TD></TR>"
			+ "<TR><TD><FONT face=新宋体 size=3>%s</FONT></TD></TR>"
			+ "</TABLE></CENTER>";
	
	public HelloRestController()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 不需要传递任何参数
	 */
	@RequestMapping(value = "/getInfo", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String getInfo()
	{
		String title = "参数信息"; 
		String content = "无@ResponseBody注解";
		String msg = String.format(HTML_TEMPLATE, title, content, this.doAddBalance(10.00D));
		return msg;
	}
	
	/**
	 * 不需要传递任何参数
	 */
	@RequestMapping(value = "/getInfo0", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String getInfo0()
	{
		String title = "参数信息"; 
		String content = "这个Rest API没有参数";
		String msg = String.format(HTML_TEMPLATE, title, content, this.doAddBalance(100.00D));
		return msg;
	}
	
	/**
	 * 使用HttpServletRequest传递参数
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getInfo1", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String getInfo1(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
	{
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title"); 
		String content = request.getParameter("content");
		String msg = String.format(HTML_TEMPLATE, title, content, this.doAddBalance(1000.00D));
		return msg;
	}
	
	/**
	 * 使用注解@PathVariable传递参数
	 */
	@RequestMapping(value = "/getInfo2/{title}/{content}", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String getInfo2(@PathVariable(value = "title") String title, @PathVariable(name = "content") String content)
	{
		String msg = String.format(HTML_TEMPLATE, title, content, this.doAddBalance(5000.00D));
		return msg;
	}
	
	/**
	 * 使用注解@RequestParam传递参数
	 */
	@RequestMapping(path = "/getInfo3", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String getInfo3(@RequestParam(value = "title") String title, @RequestParam(name = "content") String content)
	{
		String msg = String.format(HTML_TEMPLATE, title, content, this.doAddBalance(100000000.00D));
		return msg;
	}
	
	/**
	 * 使用注解@ModelAttribute传递参数
	 */
	@RequestMapping(path = "/getInfo4", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String getInfo4(@ModelAttribute(name = "title") String title, @ModelAttribute(value = "content") String content)
	{
		String msg = String.format(HTML_TEMPLATE, title, content, this.doAddBalance(200000000.60D));
		return msg;
	}
	
	/**
	 * 业务函数
	 */
	private String doAddBalance(double balanceValue)
	{
		String desc = "";
		if(balanceValue >= 100000000.00D && balanceValue < 200000000.00D)
			desc = "你发财了";
		else if(balanceValue >= 200000000.00D)
			desc = "你发大财了";
		else
			desc = "你发了点小财";
		
		NumberFormat df = new DecimalFormat("￥#,###.00");
		String strBalance = df.format(balanceValue);
		return String.format("您的银行账户余额已增加人民币<FONT face=微软雅黑 size=5 color=Red>%s</FONT>元,%s", strBalance, desc);
	}
}
