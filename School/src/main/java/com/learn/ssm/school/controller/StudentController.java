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

/* �Զ���� */
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
	 * ��ȡ����ѧ������Ϣ�б�
	 */
	@RequestMapping(value = "/students", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getAllStudents(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();

		//��ȡ���а༶����Ϣ:
		List<Classes> classesList = this.classesService.selectAllClassess();
		int number_of_classess = classesList.size();

		//��ȡ����ѧ������Ϣ:(һ��ѧ��ֻ����һ���༶,һ���༶ֻӵ��һ��������)
		List<Student> studentsList = this.studentService.selectAllStudentsClassess();
		int number_of_students = studentsList.size();

		//���ذ༶��Ϣ�б�:
		mav.addObject("number_of_classess", number_of_classess);
		mav.addObject("classessList", classesList);

		//����ѧ����Ϣ�б�:
		mav.addObject("number_of_students", number_of_students);
		mav.addObject("studentsList", studentsList);

		mav.setViewName("students");
		return mav;
	}
	
	/**
	 * ���һ��ѧ��
	 */
	@RequestMapping(value = "/add_student", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String addStudent(@RequestParam(value = "studentId") int id, @RequestParam(value = "studentName") String name, @RequestParam(value = "studentSex") int sex, @RequestParam(name = "studentAge") int age, @RequestParam(name = "classesId") int classesId, ModelMap model)
	{
		if(id < 18010000 || id > 18029999)
		{
			Print.error("ѧ�Ÿ�ʽ����:%d", id);
			String title = "������Ϣ";
			String content = String.format("ѧ�Ÿ�ʽ����:%d", id);
			model.addAttribute("title", title);
			model.addAttribute("content", content);
			return "error"; //���ص��ַ�����ʾһ���߼���ͼҳ������,ֻ��ͨ��Model���������ͼ�ϴ�������;
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
			Print.warn("��ѧ���޲�����༶: %d", classesId);
		}
		student.dump();
		int result = this.studentService.insertStudent(student);
		return "redirect:/students.do"; //�ض���ҳ��;
	}
	
	/**
	 * ɾ��һ��ѧ��
	 */
	@RequestMapping(value = "/del_student", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String delStudent(@ModelAttribute(value = "id") int id)
	{
		int result = this.studentService.deleteStudentById(id);
		return "forward:/students.do";
	}
	
	/**
	 * ѧ����ҵ;
	 */
	@RequestMapping(value = "/updateStudentLeave/{id}", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String updateStudentLeaveTime(@PathVariable(value = "id") int id)
	{
		int result = this.studentService.updateStudentLeaveTimeById(id);
		return "forward:/students.do";
	}
	
	/**
	 * ��ȡһ��ѧ������Ϣ
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
