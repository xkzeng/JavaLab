package com.learn.ssm.school.service;

import java.util.List;

import com.learn.ssm.school.entity.Teacher;
import com.learn.ssm.school.entity.MasterTeacher;

public interface ITeacherService
{
	/* 班主任信息 */
  //增:
	public int insertMasterTeacher(MasterTeacher masterTeacher);
	
	//删:
	public int deleteAllMasterTeachers();
	public int deleteMasterTeachers(int ids[]);
	public int deleteMasterTeacher(MasterTeacher masterTeacher);
	public int deleteMasterTeacherById(int masterTeacherId);
	
	//改:
	public int updateMasterTeacher(MasterTeacher masterTeacher);
	public int updateMasterTeacherLeaveTimeById(int masterTeacherId);
	
	//查:
	public List<MasterTeacher> selectAllMasterTeachers();
	public List<MasterTeacher> selectInServiceMasterTeachers();
	public MasterTeacher selectMasterTeacherById(int masterTeacherId);
	
	/* 教师信息 */
	//增:
	public int insertTeacher(Teacher teacher);
	
	//删:
	public int deleteAllTeachers();
	public int deleteTeachers(int ids[]);
	public int deleteTeacher(Teacher teacher);
	public int deleteTeacherById(int teacherId);
	
	//改:
	public int updateTeacher(Teacher teacher);
	public int updateTeacherLeaveTimeById(int teacherId);
	
	//查:
	public List<Teacher> selectAllTeachers();
	public List<Teacher> selectInServiceTeachers();
	public Teacher selectTeacherById(int teacherId);
}
