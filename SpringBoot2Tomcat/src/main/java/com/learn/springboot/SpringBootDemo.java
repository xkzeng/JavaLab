package com.learn.springboot;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.DecimalFormat;
import java.text.NumberFormat;

@RestController
@SpringBootApplication
public class SpringBootDemo extends SpringBootServletInitializer //关键点2
{
	private static final String HTML_TEMPLATE = "<CENTER><TABLE>"
			+ "<TR><TH><FONT face=新宋体 size=6>%s</FONT></TH></TR>"
			+ "<TR><TD><FONT face=新宋体 size=5 color=Red>%s</FONT></TD></TR>"
			+ "<TR><TD><FONT face=新宋体 size=5 color=Red>%s</FONT></TD></TR>"
			+ "<TR><TD><FONT face=新宋体 size=4>%s</FONT></TD></TR>"
			+ "</TABLE></CENTER>";
	
  //关键点3
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
	{
		return builder.sources(SpringBootDemo.class);
	}
	
	@GetMapping("/")
	String home()
	{
		String title = "测试信息"; 
		String content = "Hello, this a simple SpringBoot Demo";
		String msg = String.format(HTML_TEMPLATE, title, "&nbsp;", content, this.doAddBalance(10.00D));
		return msg;
	}
	
	@GetMapping("/now")
	String now()
	{
		String datetime = String.format("%1$tF %1$tT", System.currentTimeMillis());
		String title = "当前时间"; 
		String content = "现在时间是: " + datetime;
		String msg = String.format(HTML_TEMPLATE, title, "&nbsp;", content, this.doAddBalance(100.00D));
		return msg;
	}
	
	@GetMapping(value = "/get", produces = {"application/json;charset=utf-8", "text/html;charset=utf-8"})
	String getInfo(HttpServletRequest request, HttpServletResponse response)
	{
		String title = "通过Request传递参数"; 
		String content = "姓名: " + request.getParameter("name");
		String content2 = "性别: " + request.getParameter("sex");
		String msg = String.format(HTML_TEMPLATE, title, content, content2, this.doAddBalance(1000.00D));
		return msg;
	}
	
	@GetMapping(value = "/get/{name}/{sex}", produces = {"application/json;charset=utf-8", "text/html;charset=utf-8"})
	String getInfo(@PathVariable(value = "name") String name, @PathVariable(name = "sex") String sex)
	{
		String title = "通过注解@PathVariable传递参数";
		String content = "姓名: " + name;
		String content2 = "性别: " + sex;
		String msg = String.format(HTML_TEMPLATE, title, content, content2, this.doAddBalance(10000.00D));
		return msg;
	}
	
	@GetMapping(value = "/get2", produces = {"application/json;charset=utf-8", "text/html;charset=utf-8"})
	String getInfoParam(@RequestParam(name = "name") String name, @RequestParam(value = "sex") String sex)
	{
		String title = "通过注解@RequestParam传递参数";
		String content = "姓名: " + name;
		String content2 = "性别: " + sex;
		String msg = String.format(HTML_TEMPLATE, title, content, content2, this.doAddBalance(100000000.00D));
		return msg;
	}
	
	@GetMapping(value = "/get3", produces = {"application/json;charset=utf-8", "text/html;charset=utf-8"})
	String getInfoMode(@ModelAttribute(name = "name") String name, @ModelAttribute(value = "sex") String sex)
	{
		String title = "通过注解@ModelAttribute传递参数";
		String content = "姓名: " + name;
		String content2 = "性别: " + sex;
		String msg = String.format(HTML_TEMPLATE, title, content, content2, this.doAddBalance(200000000.00D));
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
