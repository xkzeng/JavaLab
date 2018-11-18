package com.learn.ssm.school.service;

import java.util.List;

import com.learn.ssm.school.entity.Student;

public interface IStudentService
{
  //增:
	public int insertStudent(Student student);
	
	//删:
	public int deleteAllStudents();
	public int deleteStudents(int ids[]);
	public int deleteStudent(Student student);
	public int deleteStudentById(int id);
	
	//改:
	public int updateStudent(Student student);
	public int updateStudentLeaveTimeById(int id);
	
	//查:
	public List<Student> selectAllStudents();
	public List<Student> selectJoinedStudents();
	public List<Student> selectQuitedStudents();
	public Student selectStudentById(int id);
	
  //学生:班级:班主任,这三者之间的一对一关系查询:
	public List<Student> selectAllStudentsClassess();
	public Student selectStudentAndClassesByStudentId(int id);
}
