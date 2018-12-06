package com.learn.springboot.entity;

public class User
{
	private int id;
	private String name;
	private String sex;
	private int age;
	private String address;
	
	public User()
	{
	}
	
	public User(int id, String name, String sex, int age, String address)
	{
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.address = address;
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
	
	public String getSex()
	{
		return this.sex;
	}
	
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	
	public int getAge()
	{
		return this.age;
	}
	
	public void setAge(int age)
	{
		this.age = age;
	}
	
	public String getAddress()
	{
		return this.address;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
}
