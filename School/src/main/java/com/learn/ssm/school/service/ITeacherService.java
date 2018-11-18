package com.learn.ssm.school.service;

import java.util.List;

import com.learn.ssm.school.entity.Teacher;
import com.learn.ssm.school.entity.MasterTeacher;

public interface ITeacherService
{
	/* ��������Ϣ */
  //��:
	public int insertMasterTeacher(MasterTeacher masterTeacher);
	
	//ɾ:
	public int deleteAllMasterTeachers();
	public int deleteMasterTeachers(int ids[]);
	public int deleteMasterTeacher(MasterTeacher masterTeacher);
	public int deleteMasterTeacherById(int masterTeacherId);
	
	//��:
	public int updateMasterTeacher(MasterTeacher masterTeacher);
	public int updateMasterTeacherLeaveTimeById(int masterTeacherId);
	
	//��:
	public List<MasterTeacher> selectAllMasterTeachers();
	public List<MasterTeacher> selectInServiceMasterTeachers();
	public MasterTeacher selectMasterTeacherById(int masterTeacherId);
	
	/* ��ʦ��Ϣ */
	//��:
	public int insertTeacher(Teacher teacher);
	
	//ɾ:
	public int deleteAllTeachers();
	public int deleteTeachers(int ids[]);
	public int deleteTeacher(Teacher teacher);
	public int deleteTeacherById(int teacherId);
	
	//��:
	public int updateTeacher(Teacher teacher);
	public int updateTeacherLeaveTimeById(int teacherId);
	
	//��:
	public List<Teacher> selectAllTeachers();
	public List<Teacher> selectInServiceTeachers();
	public Teacher selectTeacherById(int teacherId);
}
