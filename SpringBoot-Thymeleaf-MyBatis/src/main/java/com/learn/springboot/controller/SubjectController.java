package com.learn.springboot.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;

/* Spring */
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ModelAttribute;

/* Entities for this application */
import com.learn.springboot.entity.Subject;
import com.learn.springboot.service.ISubjectService;

@Controller
public class SubjectController
{
	//@Autowired
	@Resource(name = "subjectService")
	private ISubjectService subjectService;
	
	public SubjectController()
	{
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/subjects", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public Map<String, Object> getAllSubjects()
	{
		List<Subject> subjectList = this.subjectService.selectAllSubjects();
		int number_of_subjects = subjectList.size();
		
		Map<String, Object> map = new HashMap<>();
		map.put("subjectList", subjectList);
		map.put("number_of_subjects", number_of_subjects);
		return map;
	}
}
