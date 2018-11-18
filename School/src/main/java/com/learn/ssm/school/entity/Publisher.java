package com.learn.ssm.school.entity;

import org.apache.ibatis.type.Alias;

@Alias(value = "Publisher")
public class Publisher
{
	private int    id;   //出版社编号;
  private String name; //出版社名称;
  
	public Publisher()
	{
		
	}
  
  public Publisher(int id, String name)
	{
		this.id = id;
    this.name = name;
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
	
	public String toString()
	{
		return String.format("{id:%s, name:'%s'}",this.id, this.name);
	}
	
	public void dump()
	{
		System.out.println(this.getClass().getName() + " -> " + this.toString());
	}
}
