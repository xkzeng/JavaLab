package com.learn.springboot.entity;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import org.apache.ibatis.type.Alias;

@Alias(value = "Subject")
public class Subject
{
	private int           id;
  private String        name;
  private Date          enterTime;
  private Timestamp     leaveTime;
  
	public Subject()
	{
		
	}
	
	public Subject(int id, String name)
	{
		this.id = id;
    this.name = name;
	}
  
  public Subject(int id, String name, Date enterTime, Timestamp leaveTime)
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
