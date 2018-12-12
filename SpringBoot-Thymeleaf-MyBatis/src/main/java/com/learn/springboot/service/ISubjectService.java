package com.learn.springboot.service;

import java.util.List;

import com.learn.springboot.entity.Subject;

public interface ISubjectService
{
	public List<Subject> selectAllSubjects();
}
