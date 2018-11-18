package com.learn.ssm.school.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.learn.ssm.school.entity.Classes;
import com.learn.ssm.school.entity.Student;

@Repository(value = "studentMapper")
public interface IStudentMapper
{
  //��:
	public int insertStudent(Student student);
	
	//ɾ:
	public int deleteAllStudents();
	public int deleteStudents(int ids[]);
	public int deleteStudent(Student student);
	public int deleteStudentById(int id);
	
	//��:
	public int updateStudent(Student student);
	public int updateStudentLeaveTimeById(int id);
	
	//��:
	public List<Student> selectAllStudents();
	public List<Student> selectJoinedStudents();
	public List<Student> selectQuitedStudents();
	public Student selectStudentById(int id);
	
  //ѧ��:�༶:������,������֮���һ��һ��ϵ��ѯ:
	public List<Student> selectAllStudentsClassess();
	public Student selectStudentAndClassesByStudentId(int id);
}
