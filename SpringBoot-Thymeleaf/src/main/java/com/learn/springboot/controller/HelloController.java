package com.learn.springboot.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.learn.springboot.entity.User;

@Controller
@RequestMapping(value = "/api")
public class HelloController
{
	public HelloController()
	{
	}

	@RequestMapping(path = "/showUserList")
	public String showUserList(Model model)
	{
		//构造数据:
		List<User> userList = new ArrayList<User>();
		userList.add(new User(1001, "张三丰", "男", 98, "河北省石家庄市"));
		userList.add(new User(1002, "无崖子", "男", 76, "河北省邯郸市"));
		userList.add(new User(1003, "阿朱", "女", 23, "逍遥镇"));
		userList.add(new User(1004, "李秋水", "女", 20, "河北省石家庄市"));
		userList.add(new User(1005, "丁春秋", "男", 67, "黑龙江辽宁市"));
		userList.add(new User(1006, "天山童姥", "女", 72, "天门山"));
		userList.add(new User(1007, "阿紫", "女", 24, "云南大理"));
		
		//渲染模板:
		model.addAttribute("userList", userList);
		
		//返回模板:
		return "user_list";
	}
}
