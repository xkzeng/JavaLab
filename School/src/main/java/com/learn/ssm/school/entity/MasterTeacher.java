package com.learn.ssm.school.entity;

import java.util.Date;
import java.sql.Timestamp;
import org.apache.ibatis.type.Alias;

@Alias(value = "MasterTeacher")
public class MasterTeacher
{
	private int       id;        //班主任编号;
  private String    name;      //班主任名称;
  private Date      enterTime; //任职时间;
  private Timestamp leaveTime; //离职时间;
  
	public MasterTeacher()
	{
		
	}
  
  public MasterTeacher(int id, String name, Date enterTime, Timestamp leaveTime)
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
