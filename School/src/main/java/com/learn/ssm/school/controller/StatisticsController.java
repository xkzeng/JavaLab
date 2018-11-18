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
import com.learn.ssm.school.entity.SubjectStatistics;
import com.learn.ssm.school.service.IStatisticsService;

/* �Զ���� */
import com.wrapper.Print;
import com.wrapper.SC;

@Controller
public class StatisticsController
{
	@Resource(name = "statisticsService")
	private IStatisticsService statisticsService;
	
	public StatisticsController()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * ��ȡ���пγ̵���Ϣ�б�
	 */
	@RequestMapping(value = "/statistics", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8", "text/html;charset=utf-8", "text/plain;charset=utf-8"})
	public ModelAndView getSubjectStatistics()
	{
		ModelAndView mav = new ModelAndView();
		
		//��ȡ���пγ̵�ѡ���ȶ�:
		List<SubjectStatistics> subjectSelectCountList = this.statisticsService.selectSubjectSelectCount();
		int number_of_subject_selectcount = subjectSelectCountList.size();
		
		//���ؿγ���Ϣ�б�:
		mav.addObject("number_of_subject_selectcount", number_of_subject_selectcount);
		mav.addObject("subjectSelectCountList", subjectSelectCountList);
		
    mav.setViewName("statistics");
		return mav;
	}
}
