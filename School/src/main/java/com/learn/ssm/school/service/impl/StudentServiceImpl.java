package com.learn.ssm.school.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.learn.ssm.school.entity.Student;
import com.learn.ssm.school.mapper.IStudentMapper;
import com.learn.ssm.school.service.IStudentService;

@Service(value = "studentService")
public class StudentServiceImpl implements IStudentService
{
	@Resource(name = "studentMapper")
	private IStudentMapper studentMapper;
	
	public StudentServiceImpl()
	{
	}
	
  //��:
	public int insertStudent(Student student)
	{
		return this.studentMapper.insertStudent(student);
	}
	
	//ɾ:
	public int deleteAllStudents()
	{
		return this.studentMapper.deleteAllStudents();
	}
	
	public int deleteStudents(int ids[])
	{
		return this.studentMapper.deleteStudents(ids);
	}
	
	public int deleteStudent(Student student)
	{
		return this.studentMapper.deleteStudent(student);
	}
	
	public int deleteStudentById(int id)
	{
		return this.studentMapper.deleteStudentById(id);
	}
	
	//��:
	public int updateStudent(Student student)
	{
		return this.studentMapper.updateStudent(student);
	}
	
	public int updateStudentLeaveTimeById(int id)
	{
		return this.studentMapper.updateStudentLeaveTimeById(id);
	}
	
	//��:
	public List<Student> selectAllStudents()
	{
		return this.studentMapper.selectAllStudents();
	}
	
	public List<Student> selectJoinedStudents()
	{
		return this.studentMapper.selectJoinedStudents();
	}
	
	public List<Student> selectQuitedStudents()
	{
		return this.studentMapper.selectQuitedStudents();
	}
	
	public Student selectStudentById(int id)
	{
		return this.studentMapper.selectStudentById(id);
	}
	
  //ѧ��:�༶:������,������֮���һ��һ��ϵ��ѯ:
	public List<Student> selectAllStudentsClassess()
	{
		return this.studentMapper.selectAllStudentsClassess();
	}
	
	public Student selectStudentAndClassesByStudentId(int id)
	{
		return this.studentMapper.selectStudentAndClassesByStudentId(id);
	}
}
