package com.learn.ssm.school.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.learn.ssm.school.entity.Student;
import com.learn.ssm.school.entity.StudentsSubjects;

public interface IStudentSubjectService
{
  //增:
	public int insertStudentSubject(int stuId, int subId);
	public int insertStudentSubjects(int stuId, int subIds[]);
	public int insertSubjectStudents(int subId, int stuIds[]);
	
	//删:
	public int deleteAllStudentSubjects();
	public int deleteStudentSubjectsByStudentId(int studentId);
	public int deleteStudentSubjectsByStudentIds(int studentIds[]);
	public int deleteSubjectStudentsBySubjectId(int subjectId);
	public int deleteSubjectStudentsBySubjectIds(int subjectIds[]);
	public int deleteStudentSubjectByRowId(int id);
	public int deleteStudentSubjectByRowIds(int ids[]);
	public int deleteStudentSubjectByStudentIdAndSubjectId(int stuId, int subId);
	
	//改:
	//无业务需求
	
	//查: 一位学生选修多门课程
	public List<StudentsSubjects> selectAllStudentsSubjects();
	public List<StudentsSubjects> selectStudentSubjectsByStudentId(int studentId);
	public List<Student> selectAllStudentsToSubjects();
	public Student selectStudentToSubjectsByStudentId(int studentId);
	
	public StudentsSubjects selectStudentSubjectByStudentIdAndSubjectId(int studentId, int subjectId);
	public Student selectStudentToSubjectByStudentIdAndSubjectId(int studentId, int subjectId);
}
