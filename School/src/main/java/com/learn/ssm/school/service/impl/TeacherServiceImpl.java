package com.learn.ssm.school.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.learn.ssm.school.entity.Teacher;
import com.learn.ssm.school.entity.MasterTeacher;
import com.learn.ssm.school.mapper.ITeacherMapper;
import com.learn.ssm.school.service.ITeacherService;

@Service(value = "teacherService")
public class TeacherServiceImpl implements ITeacherService
{
	@Resource(name = "teacherMapper")
	private ITeacherMapper teacherMapper;
	
	public TeacherServiceImpl()
	{
	}
	
	/* 班主任信息 */
  //增:
	public int insertMasterTeacher(MasterTeacher masterTeacher)
	{
		return this.teacherMapper.insertMasterTeacher(masterTeacher);
	}
	
	//删:
	public int deleteAllMasterTeachers()
	{
		return this.teacherMapper.deleteAllMasterTeachers();
	}
	
	public int deleteMasterTeachers(int ids[])
	{
		return this.teacherMapper.deleteMasterTeachers(ids);
	}
	
	public int deleteMasterTeacher(MasterTeacher masterTeacher)
	{
		return this.teacherMapper.deleteMasterTeacher(masterTeacher);
	}
	
	public int deleteMasterTeacherById(int masterTeacherId)
	{
		return this.teacherMapper.deleteMasterTeacherById(masterTeacherId);
	}
	
	//改:
	public int updateMasterTeacher(MasterTeacher masterTeacher)
	{
		return this.teacherMapper.updateMasterTeacher(masterTeacher);
	}
	
	public int updateMasterTeacherLeaveTimeById(int masterTeacherId)
	{
		return this.teacherMapper.updateMasterTeacherLeaveTimeById(masterTeacherId);
	}
	
	//查:
	public List<MasterTeacher> selectAllMasterTeachers()
	{
		return this.teacherMapper.selectAllMasterTeachers();
	}
	
	public List<MasterTeacher> selectInServiceMasterTeachers()
	{
		return this.teacherMapper.selectInServiceMasterTeachers();
	}
	
	public MasterTeacher selectMasterTeacherById(int masterTeacherId)
	{
		return this.teacherMapper.selectMasterTeacherById(masterTeacherId);
	}
	
	/* 教师信息 */
	//增:
	public int insertTeacher(Teacher teacher)
	{
		return this.teacherMapper.insertTeacher(teacher);
	}
	
	//删:
	public int deleteAllTeachers()
	{
		return this.teacherMapper.deleteAllTeachers();
	}
	
	public int deleteTeachers(int ids[])
	{
		return this.teacherMapper.deleteTeachers(ids);
	}
	
	public int deleteTeacher(Teacher teacher)
	{
		return this.teacherMapper.deleteTeacher(teacher);
	}
	
	public int deleteTeacherById(int teacherId)
	{
		return this.teacherMapper.deleteTeacherById(teacherId);
	}
	
	//改:
	public int updateTeacher(Teacher teacher)
	{
		return this.teacherMapper.updateTeacher(teacher);
	}
	
	public int updateTeacherLeaveTimeById(int teacherId)
	{
		return this.teacherMapper.updateTeacherLeaveTimeById(teacherId);
	}
	
	//查:
	public List<Teacher> selectAllTeachers()
	{
		return this.teacherMapper.selectAllTeachers();
	}
	
	public List<Teacher> selectInServiceTeachers()
	{
		return this.teacherMapper.selectInServiceTeachers();
	}
	
	public Teacher selectTeacherById(int teacherId)
	{
		return this.teacherMapper.selectTeacherById(teacherId);
	}
}
