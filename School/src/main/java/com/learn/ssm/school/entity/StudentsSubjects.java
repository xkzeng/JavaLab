package com.learn.ssm.school.entity;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import org.apache.ibatis.type.Alias;

@Alias(value = "StudentsSubjects")
public class StudentsSubjects
{
	private int    id;          //记录序号rowId;
	private int    studentId;   //学生学号;
	private String studentName; //学生姓名;
	private int    studentSex;  //学生性别;
	private int    studentAge;  //学生年龄;
  private int    subjectId;   //课程编号;
  private String subjectName; //课程名称;
  
	public StudentsSubjects()
	{
	}
	
	public StudentsSubjects(int id)
	{
		this.id = id;
	}
	
	public StudentsSubjects(int studentId, int subjectId)
	{
		this.studentId = studentId;
    this.subjectId = subjectId;
	}
	
	public StudentsSubjects(int studentId, String studentName, int subjectId, String subjectName)
	{
		this.studentId = studentId;
		this.studentName = studentName;
    this.subjectId = subjectId;
    this.subjectName = subjectName;
	}
	
	public StudentsSubjects(int studentId, String studentName, int studentSex, int studentAge, int subjectId, String subjectName)
	{
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentSex  = studentSex;
		this.studentAge = studentAge;
    this.subjectId = subjectId;
    this.subjectName = subjectName;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getStudentId()
	{
		return this.studentId;
	}

	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}
	
	public String getStudentName()
	{
		return this.studentName;
	}

	public void setStudentName(String studentName)
	{
		this.studentName = studentName;
	}
	
	public int getStudentSex()
	{
		return this.studentSex;
	}

	public void setStudentSex(int studentSex)
	{
		this.studentSex = studentSex;
	}
	
	public int getStudentAge()
	{
		return this.studentAge;
	}

	public void setStudentAge(int studentAge)
	{
		this.studentAge = studentAge;
	}
	
  public int getSubjectId()
	{
		return this.subjectId;
	}

	public void setSubjectId(int subjectId)
	{
		this.subjectId = subjectId;
	}
	
	public String getSubjectName()
	{
		return this.subjectName;
	}

	public void setSubjectName(String subjectName)
	{
		this.subjectName = subjectName;
	}
	
	public String toString()
	{
		return String.format("{studentId:%d, studentName:%s, studentSex:%d, studentAge:%d, subjectId:%d, subjectName:%s}",
				this.studentId, this.studentName, this.studentSex, this.studentAge, this.subjectId, this.subjectName);
	}
	
	public void dump()
	{
		System.out.println(this.getClass().getName() + " -> " + this.toString());
	}
}
