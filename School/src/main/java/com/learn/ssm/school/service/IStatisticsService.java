package com.learn.ssm.school.service;

import java.util.List;

import com.learn.ssm.school.entity.SubjectStatistics;

public interface IStatisticsService
{
  public List<SubjectStatistics> selectSubjectSelectCount();
}
