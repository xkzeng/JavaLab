package com.learn.ssm.school.service;

import java.util.List;

import com.learn.ssm.school.entity.Subject;

public interface ISubjectService
{
  //��:
	public int insertSubject(Subject subject);
	
	//ɾ:
	public int deleteAllSubjects();
	public int deleteSubjects(int ids[]);
	public int deleteSubject(Subject subject);
	public int deleteSubjectById(int id);
	
	//��:
	public int updateSubject(Subject subject);
	public int updateSubjectLeaveTimeById(int id);
	
	//��:
	public List<Subject> selectAllSubjects();
	public List<Subject> selectOpendSubjects();
	public List<Subject> selectCloseSubjects();
	public Subject selectSubjectById(int id);
}
