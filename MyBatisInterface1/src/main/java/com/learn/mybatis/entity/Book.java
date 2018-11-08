package com.learn.mybatis.entity;

public class Book
{
	public Book()
	{
		
	}
	
  private int    bookId;
  private String bookName;
  private String author;
  
	public int getBookId()
	{
		return this.bookId;
	}

	public void setBookId(int bookId)
	{
		this.bookId = bookId;
	}

	public String getBookName()
	{
		return this.bookName;
	}

	public void setBookName(String bookName)
	{
		this.bookName = bookName;
	}

	public String getAuthor()
	{
		return this.author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}
}
