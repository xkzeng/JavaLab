package com.learn.ssm.school.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.learn.ssm.school.entity.Subject;
import com.learn.ssm.school.mapper.ISubjectMapper;
import com.learn.ssm.school.service.ISubjectService;

@Service(value = "subjectService")
public class SubjectServiceImpl implements ISubjectService
{
	@Resource(name = "subjectMapper")
	private ISubjectMapper subjectMapper;
	
	public SubjectServiceImpl()
	{
	}
	
  //Ôö:
	public int insertSubject(Subject subject)
	{
		return this.subjectMapper.insertSubject(subject);
	}
	
	//É¾:
	public int deleteAllSubjects()
	{
		return this.subjectMapper.deleteAllSubjects();
	}
	public int deleteSubjects(int ids[])
	{
		return this.subjectMapper.deleteSubjects(ids);
	}
	
	public int deleteSubject(Subject subject)
	{
		return this.subjectMapper.deleteSubject(subject);
	}
	
	public int deleteSubjectById(int id)
	{
		return this.subjectMapper.deleteSubjectById(id);
	}
	
	//¸Ä:
	public int updateSubject(Subject subject)
	{
		return this.subjectMapper.updateSubject(subject);
	}
	
	public int updateSubjectLeaveTimeById(int id)
	{
		return this.subjectMapper.updateSubjectLeaveTimeById(id);
	}
	
	//²é:
	public List<Subject> selectAllSubjects()
	{
		return this.subjectMapper.selectAllSubjects();
	}
	
	public List<Subject> selectOpendSubjects()
	{
		return this.subjectMapper.selectOpendSubjects();
	}
	
	public List<Subject> selectCloseSubjects()
	{
		return this.subjectMapper.selectCloseSubjects();
	}
	
	public Subject selectSubjectById(int id)
	{
		return this.subjectMapper.selectSubjectById(id);
	}
}
