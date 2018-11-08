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
			+ "<TR><TH><FONT face=������ size=4>%s</FONT></TH></TR>"
			+ "<TR><TD><FONT face=������ size=3>%s</FONT></TD></TR>"
			+ "<TR><TD><FONT face=������ size=3>%s</FONT></TD></TR>"
			+ "</TABLE></CENTER>";
	
	public HelloRestController()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * ����Ҫ�����κβ���
	 */
	@RequestMapping(value = "/getInfo", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String getInfo()
	{
		String title = "������Ϣ"; 
		String content = "��@ResponseBodyע��";
		String msg = String.format(HTML_TEMPLATE, title, content, this.doAddBalance(10.00D));
		return msg;
	}
	
	/**
	 * ����Ҫ�����κβ���
	 */
	@RequestMapping(value = "/getInfo0", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String getInfo0()
	{
		String title = "������Ϣ"; 
		String content = "���Rest APIû�в���";
		String msg = String.format(HTML_TEMPLATE, title, content, this.doAddBalance(100.00D));
		return msg;
	}
	
	/**
	 * ʹ��HttpServletRequest���ݲ���
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
	 * ʹ��ע��@PathVariable���ݲ���
	 */
	@RequestMapping(value = "/getInfo2/{title}/{content}", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String getInfo2(@PathVariable(value = "title") String title, @PathVariable(name = "content") String content)
	{
		String msg = String.format(HTML_TEMPLATE, title, content, this.doAddBalance(5000.00D));
		return msg;
	}
	
	/**
	 * ʹ��ע��@RequestParam���ݲ���
	 */
	@RequestMapping(path = "/getInfo3", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String getInfo3(@RequestParam(value = "title") String title, @RequestParam(name = "content") String content)
	{
		String msg = String.format(HTML_TEMPLATE, title, content, this.doAddBalance(100000000.00D));
		return msg;
	}
	
	/**
	 * ʹ��ע��@ModelAttribute���ݲ���
	 */
	@RequestMapping(path = "/getInfo4", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String getInfo4(@ModelAttribute(name = "title") String title, @ModelAttribute(value = "content") String content)
	{
		String msg = String.format(HTML_TEMPLATE, title, content, this.doAddBalance(200000000.60D));
		return msg;
	}
	
	/**
	 * ҵ����
	 */
	private String doAddBalance(double balanceValue)
	{
		String desc = "";
		if(balanceValue >= 100000000.00D && balanceValue < 200000000.00D)
			desc = "�㷢����";
		else if(balanceValue >= 200000000.00D)
			desc = "�㷢�����";
		else
			desc = "�㷢�˵�С��";
		
		NumberFormat df = new DecimalFormat("��#,###.00");
		String strBalance = df.format(balanceValue);
		return String.format("���������˻���������������<FONT face=΢���ź� size=5 color=Red>%s</FONT>Ԫ,%s", strBalance, desc);
	}
}
