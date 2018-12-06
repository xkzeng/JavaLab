package com.learn.springboot.controller;

import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * SpringBoot HelloController
 * @author zxk
 */

@Controller
@RequestMapping(value = "/api")
public class HelloController
{
	public HelloController()
	{
		System.out.println("call HelloController()");
	}

	@RequestMapping(path = "/hello")
	@ResponseBody
	public Map<String, Object> show()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("msg", "Hello World Controller");
		return map;
	}
}
