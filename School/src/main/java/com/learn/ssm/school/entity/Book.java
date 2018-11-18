package com.learn.ssm.school.entity;

import org.apache.ibatis.type.Alias;

@Alias(value = "Book") //可能不需要使用这个注解来修饰
public class Book
{
	private int       id;        //图书编号;
  private String    name;      //图书名称;
  private String    author;    //图书作者;
  private Publisher publisher; //出版社信息;
  
	public Book()
	{
		
	}
  
  public Book(int id, String name, String author, Publisher publisher)
	{
		this.id = id;
    this.name = name;
    this.author = author;
    this.publisher = publisher;
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

	public String getAuthor()
	{
		return this.author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}
	
	public Publisher getPublisher()
	{
		return this.publisher;
	}

	public void setPublisher(Publisher publisher)
	{
		this.publisher = publisher;
	}
	
	public String toString()
	{
		return String.format("{id:%s, name:'%s', author:'%s', publisher:%s}",
				this.id, this.name, this.author,
				(this.publisher != null ? this.publisher.toString() : "NULL"));
	}
	
	public void dump()
	{
		System.out.println(this.getClass().getName() + " -> " + this.toString());
	}
}
