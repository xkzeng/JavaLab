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
import com.learn.ssm.school.entity.MasterTeacher;
import com.learn.ssm.school.entity.Student;
import com.learn.ssm.school.service.IClassesService;
import com.learn.ssm.school.service.ITeacherService;
/* �Զ���� */
import com.wrapper.Print;
import com.wrapper.SC;

@Controller
public class ClassesController
{
	@Resource(name = "teacherService")
	private ITeacherService teacherService;
	
	@Resource(name = "classesService")
	private IClassesService classesService;
	
	public ClassesController()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * ��ȡ���а༶����Ϣ�б�
	 */
	@RequestMapping(value = "/classess", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getAllClassess(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();

		//��ȡ���а༶����Ϣ:
		List<Classes> classesList = this.classesService.selectAllClassess();
		int number_of_classess = classesList.size();

		//��ȡ������ְ�����ε���Ϣ:
		List<MasterTeacher> masterTeacherList = this.teacherService.selectInServiceMasterTeachers();
		int number_of_master_teachers = masterTeacherList.size();
		
		//��ȡ�༶�������֮��һ��һ��ϵ�ķ�����:
		List<Classes> classessAnsMasterTeachersList = this.classesService.selectAllClassessAndMasterTeachers();
		int number_of_classess_and_master_teachers = classessAnsMasterTeachersList.size();

		//���ذ༶��Ϣ�б�:
		mav.addObject("number_of_classess", number_of_classess);
		mav.addObject("classesList", classesList);

		//���ذ�������Ϣ�б�:
		mav.addObject("number_of_master_teachers", number_of_master_teachers);
		mav.addObject("masterTeacherList", masterTeacherList);
		
		//���ذ༶�������֮��һ��һ��ϵ�ķ�����:
		mav.addObject("number_of_classess_and_master_teachers", number_of_classess_and_master_teachers);
		mav.addObject("classessAnsMasterTeachersList", classessAnsMasterTeachersList);

		mav.setViewName("classes");
		return mav;
	}
	
	/**
	 * ���һ���༶
	 */
	@RequestMapping(value = "/add_classes", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String addClasses(@RequestParam(value = "classesId") int classesId, @RequestParam(value = "classesName") String classesName, @RequestParam(value = "masterTeacherId") int masterTeacherId, Model model)
	{
		if(classesId < 10 || classesId > 999)
		{
			Print.error("�༶��Ÿ�ʽ����:%d", classesId);
			String title = "������Ϣ";
			String content = String.format("�༶��Ÿ�ʽ����:%d", classesId);
			model.addAttribute("title", title);
			model.addAttribute("content", content);
			return "error"; //���ص��ַ�����ʾһ���߼���ͼҳ������,ֻ��ͨ��Model���������ͼ�ϴ�������;
		}
		
		Classes classes = new Classes();
		classes.setId(classesId);
		classes.setName(classesName);
		
		if(masterTeacherId > 1000000000L && masterTeacherId < 2000000000)
		{
			MasterTeacher masterTeacher = new MasterTeacher();
			masterTeacher.setId(masterTeacherId);
			classes.setMasterTeacher(masterTeacher);
		}
		else
		{
			Print.warn("�����εı�Ź������: %d", masterTeacherId);
		}
		classes.dump();
		int result = this.classesService.insertClasses(classes);
		return "redirect:/classess.do";
	}
	
	/**
	 * ɾ��һ���༶
	 */
	@RequestMapping(value = "/del_classes", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String delClasses(@ModelAttribute(value = "id") int id)
	{
		int result = this.classesService.deleteClassesById(id);
		return "forward:/classess.do";
	}
	
	/**
	 * ��ͣ�༶:�༶��Ӫ���ƻ��г���̭,������;
	 */
	@RequestMapping(value = "/updateClassesLeave/{id}", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String updateClassesLeaveTime(@PathVariable(value = "id") int id)
	{
		int result = this.classesService.updateClassesLeaveTimeById(id);
		return "forward:/classess.do";
	}
	
	/**
	 * ��ȡһ���༶����Ϣ
	 */
	@RequestMapping(value = "/get_classes", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String getClasses(@RequestParam(value = "id") int id, ModelMap model)
	{
		//��ȡ������ְ�����ε���Ϣ:
		List<MasterTeacher> masterTeacherList = this.teacherService.selectInServiceMasterTeachers();
		int number_of_master_teachers = masterTeacherList.size();
		
		//��ȡ�ð������ȫ��ѧ����Ϣ:�����Ƕ��ӳ��;
		Classes classes = this.classesService.selectClassesToStudentsById(id);
		classes.dump();
		
	  //��ȡ�ð������ȫ��ѧ����Ϣ:�������ִ����һ����ӳ���SQL;
	  Classes classes2 = this.classesService.selectClassesToStudentsById2(id);
	  classes2.dump();
		
		List<Student> students = classes.getStudents();
		if(students != null)
		{
			Print.info("�İ๲��%d��ѧ��", students.size());
			Print.info("Students:%s", students.toString());
		  //����ѧ����Ϣ�б�:
			model.addAttribute("number_of_students", students.size());
			model.addAttribute("studentsList", students);
		}
		else
		{
			Print.warn("�İ�û���κ�ѧ��,��������");
		}
		
		List<Student> students2 = classes2.getStudents();
		if(students2 != null)
		{
			Print.info("�İ๲��%d��ѧ��", students2.size());
			Print.info("Students2:%s", students2.toString());
		  //����ѧ����Ϣ�б�:
			model.addAttribute("number_of_students2", students2.size());
			model.addAttribute("studentsList2", students2);
		}
		else
		{
			Print.warn("�İ�û���κ�ѧ��2,��������");
		}
		
		model.addAttribute("number_of_master_teachers", number_of_master_teachers);
		model.addAttribute("masterTeacherList", masterTeacherList);
		
		model.addAttribute("classes", classes);
		model.addAttribute("classes2", classes2);
		return "layout/classes/update_classes";
	}
}
