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
import com.learn.ssm.school.service.ISubjectService;
import com.learn.ssm.school.service.ISubjectStudentService;

/* �Զ���� */
import com.wrapper.Print;
import com.wrapper.SC;

@Controller
public class SubjectStudentController
{
	@Resource(name = "subjectService")
	private ISubjectService subjectService;
	
	@Resource(name = "subjectStudentService")
	private ISubjectStudentService subjectStudentService;
	
	public SubjectStudentController()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * ��ȡ����ѡ�ε���Ϣ
	 */
	@RequestMapping(value = "/subjects_students", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getAllSubjectsStudents(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		
	  //��ȡ���пγ̵�ѡ����Ϣ:ʹ��ʵ����StudentsSubjects;
		List<StudentsSubjects> subjectsStudentsList = this.subjectStudentService.selectAllSubjectsStudents();
		int number_of_subjectsstudents = subjectsStudentsList.size();
		
		//����ѡ����Ϣ�б�:
		mav.addObject("number_of_subjectsstudents", number_of_subjectsstudents);
		mav.addObject("subjectsStudentsList", subjectsStudentsList);
		
	  //��ȡ���пγ̵�ѡ����Ϣ:ʹ��ʵ����Student��ʵ����Subject;
		List<Subject> subjects_studentsList = this.subjectStudentService.selectAllSubjectsToStudents();
		int number_of_subjects_students = subjects_studentsList.size();
		
		//����ѡ����Ϣ�б�:
		mav.addObject("number_of_subjects_students", number_of_subjects_students);
		mav.addObject("subjects_studentsList", subjects_studentsList);
		
		mav.setViewName("subjects_students");
		return mav;
	}
	
	/**
	 * ɾ��һ��ѡ����Ϣ
	 */
	@RequestMapping(value = "/del_subjectstudent/{id}", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String delSubjectStudent1(@PathVariable(value = "id") int id)
	{
		int result = this.subjectStudentService.deleteSubjectStudentByRowId(id);
		return "forward:/subjects_students.do";
	}
	
	/**
	 * ɾ������ѡ����Ϣ
	 */
	@RequestMapping(value = "/del_subjectstudents", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String delStudentSubjects(@ModelAttribute(value = "id") int ids[])
	{
		int result = this.subjectStudentService.deleteSubjectStudentByRowIds(ids);
		return "forward:/subjects_students.do";
	}
	
	/**
	 * ɾ��һ��ѡ����Ϣ
	 */
	@RequestMapping(value = "/del_subject_student", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String delSubjectStudent2(@ModelAttribute(value = "subjectId") int subId, @ModelAttribute(value = "studentId") int stuId)
	{
		int result = this.subjectStudentService.deleteSubjectStudentBySubjectIdAndStudentId(subId, stuId);
		return "forward:/subjects_students.do";
	}
  
	/**
	 * ��ȡһ�ſγ̵�ѡ�����
	 */
	@RequestMapping(value = "/get_subject_students", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getSubjectStudents(@RequestParam(value = "subjectId") int subjectId)
	{
		ModelAndView mav = new ModelAndView();
		
		//��ȡ��ǰ�γ̵���Ϣ:
		Subject currentSubject = this.subjectService.selectSubjectById(subjectId);
		
		//���ص�ǰ�γ̵���Ϣ:
		mav.addObject("subject", currentSubject);
		
		//��ȡ��ǰ�γ̵�ѡ�����:ʹ��ʵ����StudentsSubjects;
		List<StudentsSubjects> subjectStudentsList = this.subjectStudentService.selectSubjectStudentsBySubjectId(subjectId);
		int number_of_subjectstudents = subjectStudentsList.size();
		
		//���ص�ǰѧ����ѡ����Ϣ:ʹ��ʵ����StudentsSubjects;
		mav.addObject("number_of_subjectstudents", number_of_subjectstudents);
		mav.addObject("subjectStudentsList", subjectStudentsList);
		
	  //��ȡ��ǰ�γ̵�ѡ�����:ʹ��ʵ����Student��ʵ����Subject;
		int number_of_subject_students = 0;
		Subject subject_studentsSubject = this.subjectStudentService.selectSubjectToStudentsBySubjectId(subjectId);
		List<Student> subject_studentsList = null;
		if(subject_studentsSubject != null)
		{
			subject_studentsList = subject_studentsSubject.getStudents();
			if(subject_studentsList != null)
			{
				number_of_subject_students = subject_studentsList.size();
			}
		}
		
	  //��ȡ��ǰ�γ̵�ѡ�����:ʹ��ʵ����Student��ʵ����Subject;
		mav.addObject("number_of_subject_students", number_of_subject_students);
		mav.addObject("subject_studentsList", subject_studentsList);
		
		mav.setViewName("subject_students");
		return mav;
	}
}
