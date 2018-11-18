package com.learn.ssm.school.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.learn.ssm.school.entity.Classes;
import com.learn.ssm.school.mapper.IClassesMapper;
import com.learn.ssm.school.service.IClassesService;

@Service(value = "classesService")
public class ClassesServiceImpl implements IClassesService
{
	@Resource(name = "classesMapper")
	private IClassesMapper classesMapper;
	
	public ClassesServiceImpl()
	{
	}
	
//增:
	public int insertClasses(Classes classes)
	{
		return this.classesMapper.insertClasses(classes);
	}
	
	//删:
	public int deleteAllClasses()
	{
		return this.classesMapper.deleteAllClasses();
	}
	
	public int deleteClassess(int ids[])
	{
		return this.classesMapper.deleteClassess(ids);
	}
	
	public int deleteClasses(Classes classes)
	{
		return this.classesMapper.deleteClasses(classes);
	}
	
	public int deleteClassesById(int classesId)
	{
		return this.classesMapper.deleteClassesById(classesId);
	}
	
	//改:
	public int updateClasses(Classes classes)
	{
		return this.classesMapper.updateClasses(classes);
	}
	
	public int updateClassesLeaveTimeById(int classesId)
	{
		return this.classesMapper.updateClassesLeaveTimeById(classesId);
	}
	
	//查:
	public List<Classes> selectAllClassess()
	{
		return this.classesMapper.selectAllClassess();
	}
	
	public List<Classes> selectOpenedClassess()
	{
		return this.classesMapper.selectOpenedClassess();
	}
	
	public List<Classes> selectClosedClassess()
	{
		return this.classesMapper.selectClosedClassess();
	}
	
	public Classes selectClassesById(int classesId)
	{
		return this.classesMapper.selectClassesById(classesId);
	}
	
  //班级与班主任之间一对一关系查询:
	public List<Classes> selectAllClassessAndMasterTeachers()
	{
		return this.classesMapper.selectAllClassessAndMasterTeachers();
	}
	
	public List<Classes> selectAllClassesAndMasterTeacherById(int masterTeacherId)
	{
		return this.classesMapper.selectAllClassesAndMasterTeacherById(masterTeacherId);
	}
	
  //班级与学生之间一对多关系查询:
	public Classes selectClassesToStudentsById(int id)
	{
		return this.classesMapper.selectClassesToStudentsById(id);
	}
	
	public Classes selectClassesToStudentsById2(int id)
	{
		return this.classesMapper.selectClassesToStudentsById2(id);
	}
}
