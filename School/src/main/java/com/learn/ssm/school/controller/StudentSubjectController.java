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
import com.learn.ssm.school.entity.Subject;
import com.learn.ssm.school.entity.Student;
import com.learn.ssm.school.entity.StudentsSubjects;
import com.learn.ssm.school.service.IStudentService;
import com.learn.ssm.school.service.ISubjectService;
import com.learn.ssm.school.service.IStudentSubjectService;

/* 自定义库 */
import com.wrapper.Print;
import com.wrapper.SC;

@Controller
public class StudentSubjectController
{
	@Resource(name = "subjectService")
	private ISubjectService subjectService;
	
	@Resource(name = "studentService")
	private IStudentService studentService;
	
	@Resource(name = "studentSubjectService")
	private IStudentSubjectService studentSubjectService;
	
	public StudentSubjectController()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 获取所有选课的信息
	 */
	@RequestMapping(value = "/students_subjects", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getAllStudentsSubjects(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		
		//获取课程信息:
		List<Subject> subjectList = this.subjectService.selectAllSubjects();
		int number_of_subjects = subjectList.size();
		
		//返回课程信息:
		mav.addObject("number_of_subjects", number_of_subjects);
		mav.addObject("subjectList", subjectList);

		//获取所有学生的信息:(一个学生只属于一个班级,一个班级只拥有一个班主任)
		List<Student> studentsList = this.studentService.selectAllStudentsClassess();
		int number_of_students = studentsList.size();
		
		//返回学生信息列表:
		mav.addObject("number_of_students", number_of_students);
		mav.addObject("studentsList", studentsList);
		
		//获取选课信息:使用实体类StudentsSubjects;
		List<StudentsSubjects> studentsSubjectsList = this.studentSubjectService.selectAllStudentsSubjects();
		int number_of_studentssubjects = studentsSubjectsList.size();
		
		//返回选课信息列表:
		mav.addObject("number_of_studentssubjects", number_of_studentssubjects);
		mav.addObject("studentsSubjectsList", studentsSubjectsList);
		
	  //获取选课信息:使用实体类Student和实体类Subject;
		List<Student> students_subjectsList = this.studentSubjectService.selectAllStudentsToSubjects();
		int number_of_students_subjects = students_subjectsList.size();
		
		//返回选课信息列表:
		mav.addObject("number_of_students_subjects", number_of_students_subjects);
		mav.addObject("students_subjectsList", students_subjectsList);
		
		mav.setViewName("students_subjects");
		return mav;
	}
	
	/**
	 * 添加选课信息
	 */
	@RequestMapping(value = "/add_students_subjects", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
  public String addStudentsSubjects(@RequestParam(value = "studentId") int studentId, @RequestParam(name = "studentIds") int studentIds[],
                                     @RequestParam(value = "subjectId") int subjectId, @RequestParam(name = "subjectIds") int subjectIds[],
                                     ModelMap model)
	{
		if(subjectIds.length <= 0)
		{
			String message = "请选择课程";
			Print.error(message);
			String title = "提示信息";
			String content = message;
			model.addAttribute("title", title);
			model.addAttribute("content", content);
			return "error"; //返回的字符串表示一个逻辑视图页面名称,只能通过Model参数向该视图上传递数据;
		}
		
		if(studentIds.length <= 0)
		{
			String message = "请指定学生";
			Print.error(message);
			String title = "提示信息";
			String content = message;
			model.addAttribute("title", title);
			model.addAttribute("content", content);
			return "error"; //返回的字符串表示一个逻辑视图页面名称,只能通过Model参数向该视图上传递数据;
		}
		
		int result = 0;
		if(studentIds.length == 1) //一名学生;
		{
			if(subjectIds.length == 1) //选修一门课;
			{
				Print.info(">>> 即将生成一条选课信息:studentId = %d, subjectId = %d", studentIds[0], subjectIds[0]);
				result = this.studentSubjectService.insertStudentSubject(studentIds[0], subjectIds[0]);
			}
			else //选修多门课;
			{
				Print.info(">>> 即将生成%d条选课信息:studentId = %d", subjectIds.length, studentIds[0]);
				result = this.studentSubjectService.insertStudentSubjects(studentIds[0], subjectIds);
			}
		}
		else                       //多名学生
		{
			if(subjectIds.length == 1) //选修一门课;
			{
				result = this.studentSubjectService.insertSubjectStudents(subjectIds[0], studentIds);
			}
			else //选修多门课;
			{
				//result = this.studentSubjectService.insertStudentSubjects(studentId, subjectIds);
				String message = "多名学生选择多门课程的多对多操作太复杂了,暂不实现";
				Print.error(message);
				String title = "提示信息";
				String content = message;
				model.addAttribute("title", title);
				model.addAttribute("content", content);
				return "error"; //返回的字符串表示一个逻辑视图页面名称,只能通过Model参数向该视图上传递数据;
			}
		}
		
		Print.info("插入 %d 行记录", result);
		return "redirect:/students_subjects.do"; //重定向页面;
	}
	
	/**
	 * 给指定学生添加选课信息
	 */
	@RequestMapping(value = "/add_student_subjects", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
  public String addStudentSubjects(@RequestParam(value = "studentId") int studentId, @RequestParam(name = "subjectIds") int subjectIds[], ModelMap model)
	{
		if(subjectIds.length <= 0)
		{
			String message = "请选择课程";
			Print.error(message);
			String title = "提示信息";
			String content = message;
			model.addAttribute("title", title);
			model.addAttribute("content", content);
			return "error"; //返回的字符串表示一个逻辑视图页面名称,只能通过Model参数向该视图上传递数据;
		}
		
		int result = 0;
		if(subjectIds.length == 1) //选修一门课;
		{
			Print.info(">>> 即将生成一条选课信息:studentId = %d, subjectId = %d", studentId, subjectIds[0]);
			result = this.studentSubjectService.insertStudentSubject(studentId, subjectIds[0]);
		}
		else //选修多门课;
		{
			Print.info(">>> 即将生成%d条选课信息:studentId = %d", subjectIds.length, studentId);
			result = this.studentSubjectService.insertStudentSubjects(studentId, subjectIds);
		}
		
		Print.info("插入 %d 行记录", result);
		
		//model.addAttribute("studentId", studentId);
		return "redirect:/get_student_subjects.do?studentId=" + studentId; //重定向页面;
	}
	
	/**
	 * 删除一条选课信息
	 */
	@RequestMapping(value = "/del_studentsubject/{id}", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String delStudentSubject1(@PathVariable(value = "id") int id)
	{
		int result = this.studentSubjectService.deleteStudentSubjectByRowId(id);
		return "forward:/students_subjects.do";
	}
	
	/**
	 * 删除多条选课信息
	 */
	@RequestMapping(value = "/del_studentsubjects", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String delStudentSubjects(@ModelAttribute(value = "id") int ids[])
	{
		int result = this.studentSubjectService.deleteStudentSubjectByRowIds(ids);
		return "forward:/students_subjects.do";
	}
	
	/**
	 * 删除一条选课信息
	 */
	@RequestMapping(value = "/del_student_subject", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String delStudentSubject2(@ModelAttribute(value = "studentId") int stuId, @ModelAttribute(value = "subjectId") int subId)
	{
		int result = this.studentSubjectService.deleteStudentSubjectByStudentIdAndSubjectId(stuId, subId);
		return "forward:/students_subjects.do";
	}
	
	/**
	 * 获取一名学生的选课信息
	 */
	@RequestMapping(value = "/get_student_subjects", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getStudentSubjects(@RequestParam(value = "studentId") int studentId)
	{
		ModelAndView mav = new ModelAndView();

		//获取课程信息:
		List<Subject> subjectsList = this.subjectService.selectAllSubjects();
		int number_of_subjects = subjectsList.size();
		
		//返回课程信息:
		mav.addObject("number_of_subjects", number_of_subjects);
		mav.addObject("subjectsList", subjectsList);
		
		//获取当前学生的信息:
		Student currentStudent = this.studentService.selectStudentAndClassesByStudentId(studentId);
		
		//返回当前学生的信息:
		mav.addObject("student", currentStudent);
		
		//获取当前学生的选课信息:使用实体类StudentsSubjects;
		List<StudentsSubjects> studentSubjectsList = this.studentSubjectService.selectStudentSubjectsByStudentId(studentId);
		int number_of_studentsubjects = studentSubjectsList.size();

		//返回当前学生的选课信息:使用实体类StudentsSubjects;
		mav.addObject("number_of_studentsubjects", number_of_studentsubjects);
		mav.addObject("studentSubjectsList", studentSubjectsList);
		
		//获取当前学生的选课信息:使用实体类Student和实体类Subject;
		int number_of_student_subjects = 0;
		Student student_subjectsStudent = this.studentSubjectService.selectStudentToSubjectsByStudentId(studentId);
		List<Subject> student_subjectsList = null;
		if(student_subjectsStudent != null)
		{
			student_subjectsList = student_subjectsStudent.getSubjects();
			if(student_subjectsList != null)
			{
				number_of_student_subjects = student_subjectsList.size();
			}
		}
		
		//返回当前学生的选课信息:使用实体类Student和实体类Subject;
		mav.addObject("number_of_student_subjects", number_of_student_subjects);
		mav.addObject("student_subjectsList", student_subjectsList);
		
		mav.setViewName("student_subjects");
		return mav;
	}
}
