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

/* �Զ���� */
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
	 * ��ȡ����ѡ�ε���Ϣ
	 */
	@RequestMapping(value = "/students_subjects", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getAllStudentsSubjects(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		
		//��ȡ�γ���Ϣ:
		List<Subject> subjectList = this.subjectService.selectAllSubjects();
		int number_of_subjects = subjectList.size();
		
		//���ؿγ���Ϣ:
		mav.addObject("number_of_subjects", number_of_subjects);
		mav.addObject("subjectList", subjectList);

		//��ȡ����ѧ������Ϣ:(һ��ѧ��ֻ����һ���༶,һ���༶ֻӵ��һ��������)
		List<Student> studentsList = this.studentService.selectAllStudentsClassess();
		int number_of_students = studentsList.size();
		
		//����ѧ����Ϣ�б�:
		mav.addObject("number_of_students", number_of_students);
		mav.addObject("studentsList", studentsList);
		
		//��ȡѡ����Ϣ:ʹ��ʵ����StudentsSubjects;
		List<StudentsSubjects> studentsSubjectsList = this.studentSubjectService.selectAllStudentsSubjects();
		int number_of_studentssubjects = studentsSubjectsList.size();
		
		//����ѡ����Ϣ�б�:
		mav.addObject("number_of_studentssubjects", number_of_studentssubjects);
		mav.addObject("studentsSubjectsList", studentsSubjectsList);
		
	  //��ȡѡ����Ϣ:ʹ��ʵ����Student��ʵ����Subject;
		List<Student> students_subjectsList = this.studentSubjectService.selectAllStudentsToSubjects();
		int number_of_students_subjects = students_subjectsList.size();
		
		//����ѡ����Ϣ�б�:
		mav.addObject("number_of_students_subjects", number_of_students_subjects);
		mav.addObject("students_subjectsList", students_subjectsList);
		
		mav.setViewName("students_subjects");
		return mav;
	}
	
	/**
	 * ���ѡ����Ϣ
	 */
	@RequestMapping(value = "/add_students_subjects", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
  public String addStudentsSubjects(@RequestParam(value = "studentId") int studentId, @RequestParam(name = "studentIds") int studentIds[],
                                     @RequestParam(value = "subjectId") int subjectId, @RequestParam(name = "subjectIds") int subjectIds[],
                                     ModelMap model)
	{
		if(subjectIds.length <= 0)
		{
			String message = "��ѡ��γ�";
			Print.error(message);
			String title = "��ʾ��Ϣ";
			String content = message;
			model.addAttribute("title", title);
			model.addAttribute("content", content);
			return "error"; //���ص��ַ�����ʾһ���߼���ͼҳ������,ֻ��ͨ��Model���������ͼ�ϴ�������;
		}
		
		if(studentIds.length <= 0)
		{
			String message = "��ָ��ѧ��";
			Print.error(message);
			String title = "��ʾ��Ϣ";
			String content = message;
			model.addAttribute("title", title);
			model.addAttribute("content", content);
			return "error"; //���ص��ַ�����ʾһ���߼���ͼҳ������,ֻ��ͨ��Model���������ͼ�ϴ�������;
		}
		
		int result = 0;
		if(studentIds.length == 1) //һ��ѧ��;
		{
			if(subjectIds.length == 1) //ѡ��һ�ſ�;
			{
				Print.info(">>> ��������һ��ѡ����Ϣ:studentId = %d, subjectId = %d", studentIds[0], subjectIds[0]);
				result = this.studentSubjectService.insertStudentSubject(studentIds[0], subjectIds[0]);
			}
			else //ѡ�޶��ſ�;
			{
				Print.info(">>> ��������%d��ѡ����Ϣ:studentId = %d", subjectIds.length, studentIds[0]);
				result = this.studentSubjectService.insertStudentSubjects(studentIds[0], subjectIds);
			}
		}
		else                       //����ѧ��
		{
			if(subjectIds.length == 1) //ѡ��һ�ſ�;
			{
				result = this.studentSubjectService.insertSubjectStudents(subjectIds[0], studentIds);
			}
			else //ѡ�޶��ſ�;
			{
				//result = this.studentSubjectService.insertStudentSubjects(studentId, subjectIds);
				String message = "����ѧ��ѡ����ſγ̵Ķ�Զ����̫������,�ݲ�ʵ��";
				Print.error(message);
				String title = "��ʾ��Ϣ";
				String content = message;
				model.addAttribute("title", title);
				model.addAttribute("content", content);
				return "error"; //���ص��ַ�����ʾһ���߼���ͼҳ������,ֻ��ͨ��Model���������ͼ�ϴ�������;
			}
		}
		
		Print.info("���� %d �м�¼", result);
		return "redirect:/students_subjects.do"; //�ض���ҳ��;
	}
	
	/**
	 * ��ָ��ѧ�����ѡ����Ϣ
	 */
	@RequestMapping(value = "/add_student_subjects", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
  public String addStudentSubjects(@RequestParam(value = "studentId") int studentId, @RequestParam(name = "subjectIds") int subjectIds[], ModelMap model)
	{
		if(subjectIds.length <= 0)
		{
			String message = "��ѡ��γ�";
			Print.error(message);
			String title = "��ʾ��Ϣ";
			String content = message;
			model.addAttribute("title", title);
			model.addAttribute("content", content);
			return "error"; //���ص��ַ�����ʾһ���߼���ͼҳ������,ֻ��ͨ��Model���������ͼ�ϴ�������;
		}
		
		int result = 0;
		if(subjectIds.length == 1) //ѡ��һ�ſ�;
		{
			Print.info(">>> ��������һ��ѡ����Ϣ:studentId = %d, subjectId = %d", studentId, subjectIds[0]);
			result = this.studentSubjectService.insertStudentSubject(studentId, subjectIds[0]);
		}
		else //ѡ�޶��ſ�;
		{
			Print.info(">>> ��������%d��ѡ����Ϣ:studentId = %d", subjectIds.length, studentId);
			result = this.studentSubjectService.insertStudentSubjects(studentId, subjectIds);
		}
		
		Print.info("���� %d �м�¼", result);
		
		//model.addAttribute("studentId", studentId);
		return "redirect:/get_student_subjects.do?studentId=" + studentId; //�ض���ҳ��;
	}
	
	/**
	 * ɾ��һ��ѡ����Ϣ
	 */
	@RequestMapping(value = "/del_studentsubject/{id}", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String delStudentSubject1(@PathVariable(value = "id") int id)
	{
		int result = this.studentSubjectService.deleteStudentSubjectByRowId(id);
		return "forward:/students_subjects.do";
	}
	
	/**
	 * ɾ������ѡ����Ϣ
	 */
	@RequestMapping(value = "/del_studentsubjects", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String delStudentSubjects(@ModelAttribute(value = "id") int ids[])
	{
		int result = this.studentSubjectService.deleteStudentSubjectByRowIds(ids);
		return "forward:/students_subjects.do";
	}
	
	/**
	 * ɾ��һ��ѡ����Ϣ
	 */
	@RequestMapping(value = "/del_student_subject", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String delStudentSubject2(@ModelAttribute(value = "studentId") int stuId, @ModelAttribute(value = "subjectId") int subId)
	{
		int result = this.studentSubjectService.deleteStudentSubjectByStudentIdAndSubjectId(stuId, subId);
		return "forward:/students_subjects.do";
	}
	
	/**
	 * ��ȡһ��ѧ����ѡ����Ϣ
	 */
	@RequestMapping(value = "/get_student_subjects", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getStudentSubjects(@RequestParam(value = "studentId") int studentId)
	{
		ModelAndView mav = new ModelAndView();

		//��ȡ�γ���Ϣ:
		List<Subject> subjectsList = this.subjectService.selectAllSubjects();
		int number_of_subjects = subjectsList.size();
		
		//���ؿγ���Ϣ:
		mav.addObject("number_of_subjects", number_of_subjects);
		mav.addObject("subjectsList", subjectsList);
		
		//��ȡ��ǰѧ������Ϣ:
		Student currentStudent = this.studentService.selectStudentAndClassesByStudentId(studentId);
		
		//���ص�ǰѧ������Ϣ:
		mav.addObject("student", currentStudent);
		
		//��ȡ��ǰѧ����ѡ����Ϣ:ʹ��ʵ����StudentsSubjects;
		List<StudentsSubjects> studentSubjectsList = this.studentSubjectService.selectStudentSubjectsByStudentId(studentId);
		int number_of_studentsubjects = studentSubjectsList.size();

		//���ص�ǰѧ����ѡ����Ϣ:ʹ��ʵ����StudentsSubjects;
		mav.addObject("number_of_studentsubjects", number_of_studentsubjects);
		mav.addObject("studentSubjectsList", studentSubjectsList);
		
		//��ȡ��ǰѧ����ѡ����Ϣ:ʹ��ʵ����Student��ʵ����Subject;
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
		
		//���ص�ǰѧ����ѡ����Ϣ:ʹ��ʵ����Student��ʵ����Subject;
		mav.addObject("number_of_student_subjects", number_of_student_subjects);
		mav.addObject("student_subjectsList", student_subjectsList);
		
		mav.setViewName("student_subjects");
		return mav;
	}
}
