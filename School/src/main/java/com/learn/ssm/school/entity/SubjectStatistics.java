package com.learn.ssm.school.entity;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import org.apache.ibatis.type.Alias;
import com.learn.ssm.school.entity.Student;

@Alias(value = "SubjectStatistics")
public class SubjectStatistics
{
	private int    id;          //����;
  private int    subjectId;   //�γ̱��;
  private String subjectName; //�γ�����;
  private int    selectCount; //ѡ�����ſγ̵�����;
  
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
