package com.learn.ssm.school.mapper;

import java.util.List;
import java.sql.Timestamp;
import org.springframework.stereotype.Repository;
import com.learn.ssm.school.entity.Classes;

@Repository(value = "classesMapper")
public interface IClassesMapper
{
  //��:
	public int insertClasses(Classes classes);
	
	//ɾ:
	public int deleteAllClasses();
	public int deleteClassess(int ids[]);
	public int deleteClasses(Classes classes);
	public int deleteClassesById(int classesId);
	
	//��:
	public int updateClasses(Classes classes);
	public int updateClassesLeaveTimeById(int classesId);
	
	//��:
	public List<Classes> selectAllClassess();
	public List<Classes> selectOpenedClassess();
	public List<Classes> selectClosedClassess();
	public Classes selectClassesById(int classesId);
	
	//�༶�������֮��һ��һ��ϵ��ѯ:
	public List<Classes> selectAllClassessAndMasterTeachers();
	public List<Classes> selectAllClassesAndMasterTeacherById(int masterTeacherId);
	
	//�༶��ѧ��֮��һ�Զ��ϵ��ѯ:
	public Classes selectClassesToStudentsById(int id);
	public Classes selectClassesToStudentsById2(int id);
}
