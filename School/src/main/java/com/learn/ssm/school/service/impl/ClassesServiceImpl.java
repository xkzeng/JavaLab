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
	
//��:
	public int insertClasses(Classes classes)
	{
		return this.classesMapper.insertClasses(classes);
	}
	
	//ɾ:
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
	
	//��:
	public int updateClasses(Classes classes)
	{
		return this.classesMapper.updateClasses(classes);
	}
	
	public int updateClassesLeaveTimeById(int classesId)
	{
		return this.classesMapper.updateClassesLeaveTimeById(classesId);
	}
	
	//��:
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
	
  //�༶�������֮��һ��һ��ϵ��ѯ:
	public List<Classes> selectAllClassessAndMasterTeachers()
	{
		return this.classesMapper.selectAllClassessAndMasterTeachers();
	}
	
	public List<Classes> selectAllClassesAndMasterTeacherById(int masterTeacherId)
	{
		return this.classesMapper.selectAllClassesAndMasterTeacherById(masterTeacherId);
	}
	
  //�༶��ѧ��֮��һ�Զ��ϵ��ѯ:
	public Classes selectClassesToStudentsById(int id)
	{
		return this.classesMapper.selectClassesToStudentsById(id);
	}
	
	public Classes selectClassesToStudentsById2(int id)
	{
		return this.classesMapper.selectClassesToStudentsById2(id);
	}
}
