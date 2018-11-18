package com.learn.ssm.school.controller;

import java.util.List;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.annotation.Resource;

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

/* Entities for this application */
import com.learn.ssm.school.entity.Subject;
import com.learn.ssm.school.service.ISubjectService;

/* 自定义库 */
import com.wrapper.Print;
import com.wrapper.SC;

@Controller
public class SubjectController
{
	@Resource(name = "subjectService")
	private ISubjectService subjectService;
	
	public SubjectController()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 获取所有课程的信息列表
	 */
	@RequestMapping(value = "/subjects", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getAllSubjects()
	{
		ModelAndView mav = new ModelAndView();
		
		//获取所有课程的信息:
		List<Subject> subjectList = this.subjectService.selectAllSubjects();
		int number_of_subjects = subjectList.size();
		
		//返回课程信息列表:
		mav.addObject("number_of_subjects", number_of_subjects);
		mav.addObject("subjectList", subjectList);
		
    mav.setViewName("subjects");
		return mav;
	}
	
	/**
	 * 添加一门课程
	 */
	@RequestMapping(value = "/add_subject", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	//public String addSubject(@RequestParam(value = "subjectId") int id, @RequestParam(value = "subjectName") String name)
	public String addSubject(Subject subject)
	{
		//Subject subject = new Subject(id, name);
		subject.dump();
		Print.info("表单字段直接封装到POJO里面:%s", subject.toString());
		int result = this.subjectService.insertSubject(subject);
		return "redirect:/subjects.do";
	}
	
	/**
	 * 删除一门课程
	 */
	@RequestMapping(value = "/del_subject/{id}", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String delSubject(@PathVariable(value = "id") int id)
	{
		int result = this.subjectService.deleteSubjectById(id);
		return "forward:/subjects.do";
	}
	
	/**
	 * 课程停课
	 */
	@RequestMapping(value = "/updateSubjectLeave", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String updateSubjectLeaveTime(@RequestParam(value = "id") int id)
	{
		int result = this.subjectService.updateSubjectLeaveTimeById(id);
		return "forward:/subjects.do";
	}
	
	/**
	 * 获取一门课程的信息
	 */
	@RequestMapping(value = "/get_subject", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getSubject(@RequestParam(value = "id") int id)
	{
		Subject subject = this.subjectService.selectSubjectById(id);
		
		ModelAndView mav = new ModelAndView("redirect:/subjects.do");
		mav.addObject("oneSubject", subject);
		//mav.setViewName("subjects");
		return mav;
	}
}
