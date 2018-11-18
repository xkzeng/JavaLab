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

/* �Զ���� */
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
	 * ��ȡ���пγ̵���Ϣ�б�
	 */
	@RequestMapping(value = "/subjects", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getAllSubjects()
	{
		ModelAndView mav = new ModelAndView();
		
		//��ȡ���пγ̵���Ϣ:
		List<Subject> subjectList = this.subjectService.selectAllSubjects();
		int number_of_subjects = subjectList.size();
		
		//���ؿγ���Ϣ�б�:
		mav.addObject("number_of_subjects", number_of_subjects);
		mav.addObject("subjectList", subjectList);
		
    mav.setViewName("subjects");
		return mav;
	}
	
	/**
	 * ���һ�ſγ�
	 */
	@RequestMapping(value = "/add_subject", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	//public String addSubject(@RequestParam(value = "subjectId") int id, @RequestParam(value = "subjectName") String name)
	public String addSubject(Subject subject)
	{
		//Subject subject = new Subject(id, name);
		subject.dump();
		Print.info("���ֶ�ֱ�ӷ�װ��POJO����:%s", subject.toString());
		int result = this.subjectService.insertSubject(subject);
		return "redirect:/subjects.do";
	}
	
	/**
	 * ɾ��һ�ſγ�
	 */
	@RequestMapping(value = "/del_subject/{id}", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String delSubject(@PathVariable(value = "id") int id)
	{
		int result = this.subjectService.deleteSubjectById(id);
		return "forward:/subjects.do";
	}
	
	/**
	 * �γ�ͣ��
	 */
	@RequestMapping(value = "/updateSubjectLeave", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public String updateSubjectLeaveTime(@RequestParam(value = "id") int id)
	{
		int result = this.subjectService.updateSubjectLeaveTimeById(id);
		return "forward:/subjects.do";
	}
	
	/**
	 * ��ȡһ�ſγ̵���Ϣ
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
