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
/* 自定义库 */
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
	 * 获取所有班级的信息列表
	 */
	@RequestMapping(value = "/classess", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getAllClassess(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();

		//获取所有班级的信息:
		List<Classes> classesList = this.classesService.selectAllClassess();
		int number_of_classess = classesList.size();

		//获取所有在职班主任的信息:
		List<MasterTeacher> masterTeacherList = this.teacherService.selectInServiceMasterTeachers();
		int number_of_master_teachers = masterTeacherList.size();
		
		//获取班级与班主任之间一对一关系的分配结果:
		List<Classes> classessAnsMasterTeachersList = this.classesService.selectAllClassessAndMasterTeachers();
		int number_of_classess_and_master_teachers = classessAnsMasterTeachersList.size();

		//返回班级信息列表:
		mav.addObject("number_of_classess", number_of_classess);
		mav.addObject("classesList", classesList);

		//返回班主任信息列表:
		mav.addObject("number_of_master_teachers", number_of_master_teachers);
		mav.addObject("masterTeacherList", masterTeacherList);
		
		//返回班级与班主任之间一对一关系的分配结果:
		mav.addObject("number_of_classess_and_master_teachers", number_of_classess_and_master_teachers);
		mav.addObject("classessAnsMasterTeachersList", classessAnsMasterTeachersList);

		mav.setViewName("classes");
		return mav;
	}
	
	/**
	 * 添加一个班级
	 */
	@RequestMapping(value = "/add_classes", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String addClasses(@RequestParam(value = "classesId") int classesId, @RequestParam(value = "classesName") String classesName, @RequestParam(value = "masterTeacherId") int masterTeacherId, Model model)
	{
		if(classesId < 10 || classesId > 999)
		{
			Print.error("班级编号格式错误:%d", classesId);
			String title = "错误信息";
			String content = String.format("班级编号格式错误:%d", classesId);
			model.addAttribute("title", title);
			model.addAttribute("content", content);
			return "error"; //返回的字符串表示一个逻辑视图页面名称,只能通过Model参数向该视图上传递数据;
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
			Print.warn("班主任的编号规则错误: %d", masterTeacherId);
		}
		classes.dump();
		int result = this.classesService.insertClasses(classes);
		return "redirect:/classess.do";
	}
	
	/**
	 * 删除一个班级
	 */
	@RequestMapping(value = "/del_classes", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String delClasses(@ModelAttribute(value = "id") int id)
	{
		int result = this.classesService.deleteClassesById(id);
		return "forward:/classess.do";
	}
	
	/**
	 * 关停班级:班级经营不善或被市场淘汰,倒闭了;
	 */
	@RequestMapping(value = "/updateClassesLeave/{id}", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String updateClassesLeaveTime(@PathVariable(value = "id") int id)
	{
		int result = this.classesService.updateClassesLeaveTimeById(id);
		return "forward:/classess.do";
	}
	
	/**
	 * 获取一个班级的信息
	 */
	@RequestMapping(value = "/get_classes", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String getClasses(@RequestParam(value = "id") int id, ModelMap model)
	{
		//获取所有在职班主任的信息:
		List<MasterTeacher> masterTeacherList = this.teacherService.selectInServiceMasterTeachers();
		int number_of_master_teachers = masterTeacherList.size();
		
		//获取该班里面的全部学生信息:结果集嵌套映射;
		Classes classes = this.classesService.selectClassesToStudentsById(id);
		classes.dump();
		
	  //获取该班里面的全部学生信息:结果集中执行另一个被映射的SQL;
	  Classes classes2 = this.classesService.selectClassesToStudentsById2(id);
	  classes2.dump();
		
		List<Student> students = classes.getStudents();
		if(students != null)
		{
			Print.info("改班共有%d个学生", students.size());
			Print.info("Students:%s", students.toString());
		  //返回学生信息列表:
			model.addAttribute("number_of_students", students.size());
			model.addAttribute("studentsList", students);
		}
		else
		{
			Print.warn("改班没有任何学生,好凄凉啊");
		}
		
		List<Student> students2 = classes2.getStudents();
		if(students2 != null)
		{
			Print.info("改班共有%d个学生", students2.size());
			Print.info("Students2:%s", students2.toString());
		  //返回学生信息列表:
			model.addAttribute("number_of_students2", students2.size());
			model.addAttribute("studentsList2", students2);
		}
		else
		{
			Print.warn("改班没有任何学生2,好凄凉啊");
		}
		
		model.addAttribute("number_of_master_teachers", number_of_master_teachers);
		model.addAttribute("masterTeacherList", masterTeacherList);
		
		model.addAttribute("classes", classes);
		model.addAttribute("classes2", classes2);
		return "layout/classes/update_classes";
	}
}
