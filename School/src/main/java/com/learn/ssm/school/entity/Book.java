package com.learn.ssm.school.entity;

import org.apache.ibatis.type.Alias;

@Alias(value = "Book") //���ܲ���Ҫʹ�����ע��������
public class Book
{
	private int       id;        //ͼ����;
  private String    name;      //ͼ������;
  private String    author;    //ͼ������;
  private Publisher publisher; //��������Ϣ;
  
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
