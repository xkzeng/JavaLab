package com.learn.ssm.school.entity;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import org.apache.ibatis.type.Alias;
import com.learn.ssm.school.entity.Student;

@Alias(value = "SubjectStatistics")
public class SubjectStatistics
{
	private int    id;          //主键;
  private int    subjectId;   //课程编号;
  private String subjectName; //课程名称;
  private int    selectCount; //选修这门课程的人数;
  
	public SubjectStatistics()
	{
		
	}
	
	public int getId()
	{
		return this.id;
	}

	public void setId(int id)
	{
		this.id = id;
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
  
  public int getSelectCount()
	{
		return this.selectCount;
	}

	public void setSelectCount(int selectCount)
	{
		this.selectCount = selectCount;
	}
  
	public String toString()
	{
		return String.format("{id:%s, subjectId:%d, subjectName:%s, selectCount:%d}",
				this.id, this.subjectId, this.subjectName, this.selectCount);
	}
	
	public void dump()
	{
		System.out.println(this.getClass().getName() + " -> " + this.toString());
	}
}
