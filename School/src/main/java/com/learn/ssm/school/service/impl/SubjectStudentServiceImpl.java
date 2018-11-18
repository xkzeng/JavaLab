package com.learn.ssm.school.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.learn.ssm.school.entity.StudentsSubjects;
import com.learn.ssm.school.entity.Subject;
import com.learn.ssm.school.mapper.ISubjectStudentMapper;
import com.learn.ssm.school.service.ISubjectStudentService;

@Service(value = "subjectStudentService")
public class SubjectStudentServiceImpl implements ISubjectStudentService
{
	@Resource(name = "subjectStudentMapper")
	private ISubjectStudentMapper subjectStudentMapper;
	
	public SubjectStudentServiceImpl()
	{
	}
	
  //增:
	public int insertSubjectStudent(int subjectId, int studentId)
	{
		return this.subjectStudentMapper.insertSubjectStudent(subjectId, studentId);
	}
	
	public int insertSubjectStudents(int subjectId, int studentIds[])
	{
		return this.subjectStudentMapper.insertSubjectStudents(subjectId, studentIds);
	}
	
	public int insertStudentSubjects(int studentId, int subjectIds[])
	{
		return this.subjectStudentMapper.insertStudentSubjects(studentId, subjectIds);
	}
	
	//删:
	public int deleteAllSubjectStudents()
	{
		return this.subjectStudentMapper.deleteAllSubjectStudents();
	}
	
	public int deleteSubjectStudentsBySubjectId(int subjectId)
	{
		return this.subjectStudentMapper.deleteSubjectStudentsBySubjectId(subjectId);
	}
	
	public int deleteSubjectStudentsBySubjectIds(int subjectIds[])
	{
		return this.subjectStudentMapper.deleteSubjectStudentsBySubjectIds(subjectIds);
	}
	
	public int deleteStudentSubjectsByStudentId(int studentId)
	{
		return this.subjectStudentMapper.deleteStudentSubjectsByStudentId(studentId);
	}
	
	public int deleteStudentSubjectsByStudentIds(int studentIds[])
	{
		return this.subjectStudentMapper.deleteStudentSubjectsByStudentIds(studentIds);
	}
	
	public int deleteSubjectStudentByRowId(int id)
	{
		return this.subjectStudentMapper.deleteSubjectStudentByRowId(id);
	}
	
	public int deleteSubjectStudentByRowIds(int ids[])
	{
		return this.subjectStudentMapper.deleteSubjectStudentByRowIds(ids);
	}
	
	public int deleteSubjectStudentBySubjectIdAndStudentId(int subId, int stuId)
	{
		return this.subjectStudentMapper.deleteSubjectStudentBySubjectIdAndStudentId(subId, stuId);
	}
	
	//改:
	//无业务需求;
	
	//查: 一门课程被多位学生选修;
	public List<StudentsSubjects> selectAllSubjectsStudents()
	{
		return this.subjectStudentMapper.selectAllSubjectsStudents();
	}
	
	public List<StudentsSubjects> selectSubjectStudentsBySubjectId(int subjectId)
	{
		return this.subjectStudentMapper.selectSubjectStudentsBySubjectId(subjectId);
	}
	
	public List<Subject> selectAllSubjectsToStudents()
	{
		return this.subjectStudentMapper.selectAllSubjectsToStudents();
	}
	
	public Subject selectSubjectToStudentsBySubjectId(int subjectId)
	{
		return this.subjectStudentMapper.selectSubjectToStudentsBySubjectId(subjectId);
	}
}
