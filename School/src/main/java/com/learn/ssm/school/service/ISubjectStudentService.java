package com.learn.ssm.school.service;

import java.util.List;

import com.learn.ssm.school.entity.StudentsSubjects;
import com.learn.ssm.school.entity.Subject;

public interface ISubjectStudentService
{
  //增:
	public int insertSubjectStudent(int subjectId, int studentId);
	public int insertSubjectStudents(int subjectId, int studentIds[]);
	public int insertStudentSubjects(int studentId, int subjectIds[]);
	
	//删:
	public int deleteAllSubjectStudents();
	public int deleteSubjectStudentsBySubjectId(int subjectId);
	public int deleteSubjectStudentsBySubjectIds(int subjectIds[]);
	public int deleteStudentSubjectsByStudentId(int studentId);
	public int deleteStudentSubjectsByStudentIds(int studentIds[]);
	public int deleteSubjectStudentByRowId(int id);
	public int deleteSubjectStudentByRowIds(int ids[]);
	public int deleteSubjectStudentBySubjectIdAndStudentId(int subId, int stuId);
	
	//改:
	//无业务需求;
	
	//查: 一门课程被多位学生选修;
	public List<StudentsSubjects> selectAllSubjectsStudents();
	public List<StudentsSubjects> selectSubjectStudentsBySubjectId(int subjectId);
	public List<Subject> selectAllSubjectsToStudents();
	public Subject selectSubjectToStudentsBySubjectId(int subjectId);
}
