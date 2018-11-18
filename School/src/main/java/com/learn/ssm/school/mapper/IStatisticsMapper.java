package com.learn.ssm.school.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.learn.ssm.school.entity.SubjectStatistics;

@Repository(value = "statisticsMapper")
public interface IStatisticsMapper
{
	public List<SubjectStatistics> selectSubjectSelectCount();
}
