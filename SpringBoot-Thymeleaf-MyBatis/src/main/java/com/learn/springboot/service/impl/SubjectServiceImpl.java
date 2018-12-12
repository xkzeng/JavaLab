package com.learn.springboot.service.impl;

import java.util.List;
import javax.annotation.Resource;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learn.springboot.entity.Subject;
import com.learn.springboot.mapper.ISubjectMapper;
import com.learn.springboot.service.ISubjectService;

@Service(value = "subjectService")
@Transactional //由事务托管当前实例
public class SubjectServiceImpl implements ISubjectService
{
	//@Autowired
	@Resource(name = "subjectMapper")
	private ISubjectMapper subjectMapper;
	
	public SubjectServiceImpl()
	{
	}
	
	public List<Subject> selectAllSubjects()
	{
		return this.subjectMapper.selectAllSubjects();
	}
}
