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
import com.learn.ssm.school.entity.Teacher;
import com.learn.ssm.school.entity.MasterTeacher;
import com.learn.ssm.school.service.ITeacherService;

/* �Զ���� */
import com.wrapper.Print;
import com.wrapper.SC;

@Controller
public class TeacherController
{
	@Resource(name = "teacherService")
	private ITeacherService teacherService;
	
	public TeacherController()
	{
		// TODO Auto-generated constructor stub
	}
	
	/* ####################### ��ʦ��Ϣ���� ####################### */
	
	/**
	 * ��ȡ���н�ʦ����Ϣ�б�
	 */
	@RequestMapping(value = "/teachers", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getAllTeachers()
	{
		ModelAndView mav = new ModelAndView();
		
		//��ȡ���н�ʦ����Ϣ:
		List<Teacher> teacherList = this.teacherService.selectAllTeachers();
		int number_of_teachers = teacherList.size();
		
		//��ȡ���а����ε���Ϣ;
    List<MasterTeacher> masterTeacherList = this.teacherService.selectAllMasterTeachers();
		int number_of_master_teachers = masterTeacherList.size();
		
		//���ؽ�ʦ��Ϣ�б�:
		mav.addObject("number_of_teachers", number_of_teachers);
		mav.addObject("teacherList", teacherList);
		
	  //���ذ�������Ϣ�б�:
		mav.addObject("number_of_master_teachers", number_of_master_teachers);
		mav.addObject("masterTeacherList", masterTeacherList);
		
    mav.setViewName("teacher");
		return mav;
	}
	
	/**
	 * ���һ����ʦ
	 */
	@RequestMapping(value = "/add_teacher", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String addTeacher(@RequestParam(value = "teacherId") int teacherId, @RequestParam(value = "teacherName") String teacherName)
	{
		Teacher teacher = new Teacher();
		teacher.setId(teacherId);
		teacher.setName(teacherName);
		teacher.dump();
		int result = this.teacherService.insertTeacher(teacher);
		return "redirect:/teachers.do";
	}
	
	/**
	 * ɾ��һ����ʦ
	 */
	@RequestMapping(value = "/del_teacher", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String delTeacher(@RequestParam(value = "teacherId") int teacherId)
	{
		int result = this.teacherService.deleteTeacherById(teacherId);
		return "forward:/teachers.do";
	}
	
	/**
	 * ��ʦ��ְ
	 */
	@RequestMapping(value = "/updateTeacherLeave", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String updateTeacherLeaveTime(@RequestParam(value = "teacherId") int teacherId)
	{
		int result = this.teacherService.updateTeacherLeaveTimeById(teacherId);
		return "forward:/teachers.do";
	}
	
	/**
	 * ��ȡһ����ʦ����Ϣ
	 */
	@RequestMapping(value = "/get_teacher", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getTeacher(@RequestParam(value = "teacherId") int teacherId)
	{
		Teacher teacher = this.teacherService.selectTeacherById(teacherId);
		
		ModelAndView mav = new ModelAndView("redirect:/get_teachers.do");
		mav.addObject("oneTeacher", teacher);
		//mav.setViewName("teacher");
		return mav;
	}
	
	/* ####################### ��������Ϣ���� ####################### */
	/**
	 * ���һ��������
	 */
	@RequestMapping(value = "/add_master_teacher", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String addMasterTeacher(@RequestParam(value = "masterTeacherId") int masterTeacherId, @RequestParam(value = "masterTeacherName") String masterTeacherName)
	{
		MasterTeacher masterTeacher = new MasterTeacher();
		masterTeacher.setId(masterTeacherId);
		masterTeacher.setName(masterTeacherName);
		masterTeacher.dump();
		int result = this.teacherService.insertMasterTeacher(masterTeacher);
		return "forward:/teachers.do";
	}
	
	/**
	 * ɾ��һ��������
	 */
	@RequestMapping(value = "/del_master_teacher", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String delMasterTeacher(@RequestParam(value = "masterTeacherId") int masterTeacherId)
	{
		int result = this.teacherService.deleteMasterTeacherById(masterTeacherId);
		return "redirect:/teachers.do";
	}
	
	/**
	 * ��������ְ
	 */
	@RequestMapping(value = "/updateMasterTeacherLeave", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String updateMasterTeacherLeaveTime(@RequestParam(value = "masterTeacherId") int masterTeacherId)
	{
		int result = this.teacherService.updateMasterTeacherLeaveTimeById(masterTeacherId);
		return "redirect:/teachers.do";
	}
	
	/**
	 * ��ȡһ�������ε���Ϣ
	 */
	@RequestMapping(value = "/get_master_teacher", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getMasterTeacher(@RequestParam(value = "masterTeacherId") int masterTeacherId)
	{
		MasterTeacher masterTeacher = this.teacherService.selectMasterTeacherById(masterTeacherId);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("oneMasterTeacher", masterTeacher);
		mav.setViewName("teacher");
		return mav;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * ʹ��ע��@PathVariable���ݲ���
	 */
	@RequestMapping(value = "/deleteTeacher/{id}", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getInfo2(@PathVariable(value = "title") String title, @PathVariable(value = "content") String content)
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", title); 
		mav.addObject("content", content);
		mav.setViewName("message");
		return mav;
	}
	
	/**
	 * ʹ��ע��@ModelAttribute���ݲ���
	 */
	@RequestMapping(value = "/delete_teachers", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getInfo4(@ModelAttribute(value = "title") String title, @ModelAttribute(value = "content") String content)
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", title); 
		mav.addObject("content", content);
		mav.setViewName("message");
		return mav;
	}
}
