package com.learn.ssm.school.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.learn.ssm.school.entity.SubjectStatistics;
import com.learn.ssm.school.mapper.IStatisticsMapper;
import com.learn.ssm.school.service.IStatisticsService;

@Service(value = "statisticsService")
public class StatisticsServiceImpl implements IStatisticsService
{
	@Resource(name = "statisticsMapper")
	private IStatisticsMapper statisticsMapper;
	
	public StatisticsServiceImpl()
	{
	}
	
	public List<SubjectStatistics> selectSubjectSelectCount()
	{
		return this.statisticsMapper.selectSubjectSelectCount();
	}
}
