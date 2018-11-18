package com.learn.ssm.school.entity;

import java.util.Date;
import java.sql.Timestamp;
import org.apache.ibatis.type.Alias;

@Alias(value = "Teacher")
public class Teacher
{
	private int       id;        //教师编号;
  private String    name;      //教师名称;
  private Date      enterTime; //入职时间;
  private Timestamp leaveTime; //离职时间;
  
	public Teacher()
	{
		
	}
  
  public Teacher(int id, String name, Date enterTime, Timestamp leaveTime)
	{
		this.id = id;
    this.name = name;
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
	
	public String toString()
	{
		return String.format("{id:%s, name:'%s', enterTime:'%s', leaveTime:'%s'}", this.id, this.name,
				String.format("%1$tF %1$tT", this.enterTime), String.format("%1$tF %1$tT", this.leaveTime));
	}
	
	public void dump()
	{
		System.out.println(this.getClass().getName() + " -> " + this.toString());
	}
}
