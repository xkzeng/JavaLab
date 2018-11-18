package com.learn.ssm.school.entity;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import org.apache.ibatis.type.Alias;

@Alias(value = "Classes")
public class Classes
{
	private int           id;            //�༶���;
  private String        name;          //�༶����;
  private MasterTeacher masterTeacher; //�ð�İ�������Ϣ;
  private Date          enterTime;     //����ʱ��;
  private Timestamp     leaveTime;     //��ͣʱ��;
  private List<Student> students;      //����༶�����ѧ��;
  
	public Classes()
	{
		
	}
  
  public Classes(int id, String name, MasterTeacher masterTeacher, Date enterTime, Timestamp leaveTime)
	{
		this.id = id;
    this.name = name;
    this.masterTeacher = masterTeacher;
    this.enterTime = enterTime;
    this.leaveTime = leaveTime;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public MasterTeacher getMasterTeacher()
	{
		return this.masterTeacher;
	}
	
	public void setMasterTeacher(MasterTeacher masterTeacher)
	{
		this.masterTeacher = masterTeacher;
	}
	
	public Date getEnterTime()
	{
		return this.enterTime;
	}
	
	public void setEnterTime(Date enterTime)
	{
		this.enterTime = enterTime;
	}
	
	public Date getLeaveTime()
	{
		return this.leaveTime;
	}
	
	public void setLeaveTime(Timestamp leaveTime)
	{
		this.leaveTime = leaveTime;
	}
	
	public List<Student> getStudents()
	{
		return this.students;
	}
	
	public void setStudents(List<Student> students)
	{
		this.students = students;
	}
	
	public String toString()
	{
		return String.format("{id:%s, name:%s, enterTime:%s, leaveTime:%s, masterTeacher:%s, students:%s}", this.id, this.name,
				String.format("%1$tF %1$tT", this.enterTime), String.format("%1$tF %1$tT", this.leaveTime),
				(this.masterTeacher != null ? this.masterTeacher.toString() : "NULL"),
				(this.students != null ? this.students.toString() : "NULL"));
	}
	
	public void dump()
	{
		System.out.println(this.getClass().getName() + " -> " + this.toString());
	}
}
