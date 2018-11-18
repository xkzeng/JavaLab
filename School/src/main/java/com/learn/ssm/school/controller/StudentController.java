package com.learn.ssm.school.controller;

import java.util.List;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.annotation.Resource;

/* Servlet API */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* Spring */
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

/* Entities for this application */
import com.learn.ssm.school.entity.Classes;
import com.learn.ssm.school.service.IClassesService;
import com.learn.ssm.school.entity.Student;
import com.learn.ssm.school.service.IStudentService;

/* 自定义库 */
import com.wrapper.Print;
import com.wrapper.SC;

@Controller
public class StudentController
{
	@Resource(name = "classesService")
	private IClassesService classesService;
	
	@Resource(name = "studentService")
	private IStudentService studentService;
	
	public StudentController()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 获取所有学生的信息列表
	 */
	@RequestMapping(value = "/students", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getAllStudents(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();

		//获取所有班级的信息:
		List<Classes> classesList = this.classesService.selectAllClassess();
		int number_of_classess = classesList.size();

		//获取所有学生的信息:(一个学生只属于一个班级,一个班级只拥有一个班主任)
		List<Student> studentsList = this.studentService.selectAllStudentsClassess();
		int number_of_students = studentsList.size();

		//返回班级信息列表:
		mav.addObject("number_of_classess", number_of_classess);
		mav.addObject("classessList", classesList);

		//返回学生信息列表:
		mav.addObject("number_of_students", number_of_students);
		mav.addObject("studentsList", studentsList);

		mav.setViewName("students");
		return mav;
	}
	
	/**
	 * 添加一个学生
	 */
	@RequestMapping(value = "/add_student", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String addStudent(@RequestParam(value = "studentId") int id, @RequestParam(value = "studentName") String name, @RequestParam(value = "studentSex") int sex, @RequestParam(name = "studentAge") int age, @RequestParam(name = "classesId") int classesId, ModelMap model)
	{
		if(id < 18010000 || id > 18029999)
		{
			Print.error("学号格式错误:%d", id);
			String title = "错误信息";
			String content = String.format("学号格式错误:%d", id);
			model.addAttribute("title", title);
			model.addAttribute("content", content);
			return "error"; //返回的字符串表示一个逻辑视图页面名称,只能通过Model参数向该视图上传递数据;
		}
		
		Student student = new Student(id, name, sex, age);
		
		if(classesId > 0)
		{
			Classes classes = new Classes();
			classes.setId(classesId);
			student.setClasses(classes);
		}
		else
		{
			Print.warn("该学生赞不分配班级: %d", classesId);
		}
		student.dump();
		int result = this.studentService.insertStudent(student);
		return "redirect:/students.do"; //重定向页面;
	}
	
	/**
	 * 删除一个学生
	 */
	@RequestMapping(value = "/del_student", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String delStudent(@ModelAttribute(value = "id") int id)
	{
		int result = this.studentService.deleteStudentById(id);
		return "forward:/students.do";
	}
	
	/**
	 * 学生毕业;
	 */
	@RequestMapping(value = "/updateStudentLeave/{id}", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String updateStudentLeaveTime(@PathVariable(value = "id") int id)
	{
		int result = this.studentService.updateStudentLeaveTimeById(id);
		return "forward:/students.do";
	}
	
	/**
	 * 获取一个学生的信息
	 */
	@RequestMapping(value = "/get_student", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getClasses(@RequestParam(value = "id") int id)
	{
//		Classes classes = this.classesService.selectClassesById(classesId);
//		
//		ModelAndView mav = new ModelAndView("redirect:/classess.do");
//		mav.addObject("oneStudent", classes);
//		mav.setViewName("classes");
//		return mav;
		return null;
	}
}
