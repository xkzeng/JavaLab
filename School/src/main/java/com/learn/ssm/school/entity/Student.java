package com.learn.ssm.school.entity;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import org.apache.ibatis.type.Alias;

import com.learn.ssm.school.entity.Subject;

@Alias(value = "Student")
public class Student
{
	private int           id;        //学号;
  private String        name;      //姓名;
  private int           sex;       //性别;1-男;0-女;
  private int           age;       //年龄;
  private Date          enterTime; //入校时间;(可以由数据库自动填充NOW或CURRENT_TIMESTAMP)
  private Timestamp     leaveTime; //毕业时间;
  private Classes       classes;   //所属班级;
  private List<Subject> subjects;  //所选课程;
  
	public Student()
	{
	}
	
	public Student(int id, String name, int sex)
	{
		this.id = id;
    this.name = name;
    this.sex = sex;
	}
	
	public Student(int id, String name, int sex, int age)
	{
		this.id = id;
    this.name = name;
    this.sex = sex;
    this.age = age;
	}
	
	public Student(int id, String name, int sex, Date enterTime)
	{
		this.id = id;
    this.name = name;
    this.sex = sex;
    this.enterTime = enterTime;
	}
	
	public Student(int id, String name, int sex, int age, Date enterTime)
	{
		this.id = id;
    this.name = name;
    this.sex = sex;
    this.age = age;
    this.enterTime = enterTime;
	}
	
	public Student(int id, String name, int sex, int age, Date enterTime, Classes classes)
	{
		this.id = id;
    this.name = name;
    this.sex = sex;
    this.age = age;
    this.enterTime = enterTime;
    this.classes = classes;
	}
	
	public Student(int id, String name, int sex, int age, Date enterTime, Classes classes, List<Subject> subjects)
	{
		this.id = id;
    this.name = name;
    this.sex = sex;
    this.age = age;
    this.enterTime = enterTime;
    this.classes = classes;
    this.subjects = subjects;
	}
	
	public Student(int id, String name, int sex, int age, Date enterTime, Timestamp leaveTime)
	{
		this.id = id;
    this.name = name;
    this.sex = sex;
    this.age = age;
    this.enterTime = enterTime;
    this.leaveTime = leaveTime;
	}
  
  public Student(int id, String name, int sex, int age, Date enterTime, Timestamp leaveTime, Classes classes)
	{
		this.id = id;
    this.name = name;
    this.sex = sex;
    this.age = age;
    this.enterTime = enterTime;
    this.leaveTime = leaveTime;
    this.classes = classes;
	}
  
  public Student(int id, String name, int sex, int age, Date enterTime, Timestamp leaveTime, Classes classes, List<Subject> subjects)
	{
		this.id = id;
    this.name = name;
    this.sex = sex;
    this.age = age;
    this.enterTime = enterTime;
    this.leaveTime = leaveTime;
    this.classes = classes;
    this.subjects = subjects;
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
	
	public int getSex()
	{
		return this.sex;
	}

	public void setSex(int sex)
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
	
	public Classes getClasses()
	{
		return this.classes;
	}

	public void setClasses(Classes classes)
	{
		this.classes = classes;
	}
	
	public List<Subject> getSubjects()
	{
		return this.subjects;
	}
	
	public void setSubjects(List<Subject> subjects)
	{
		this.subjects = subjects;
	}
	
	public String toString()
	{
		return String.format("{id:%s, name:'%s', sex:'%s', age:%d, enterTime:'%s', leaveTime:'%s', classes:'%s', subjects:%s}",
				this.id, this.name, (this.sex == 0 ? "女" : "男"), this.age,
				String.format("%1$tF %1$tT", this.enterTime), String.format("%1$tF %1$tT", this.leaveTime),
				(this.classes != null ? this.classes.toString() : "NULL"),
				(this.subjects != null ? this.subjects.toString() : "NULL"));
	}
	
	public void dump()
	{
		System.out.println(this.getClass().getName() + " -> " + this.toString());
	}
}
