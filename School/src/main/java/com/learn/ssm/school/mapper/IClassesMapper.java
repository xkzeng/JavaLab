package com.learn.ssm.school.mapper;

import java.util.List;
import java.sql.Timestamp;
import org.springframework.stereotype.Repository;
import com.learn.ssm.school.entity.Classes;

@Repository(value = "classesMapper")
public interface IClassesMapper
{
  //增:
	public int insertClasses(Classes classes);
	
	//删:
	public int deleteAllClasses();
	public int deleteClassess(int ids[]);
	public int deleteClasses(Classes classes);
	public int deleteClassesById(int classesId);
	
	//改:
	public int updateClasses(Classes classes);
	public int updateClassesLeaveTimeById(int classesId);
	
	//查:
	public List<Classes> selectAllClassess();
	public List<Classes> selectOpenedClassess();
	public List<Classes> selectClosedClassess();
	public Classes selectClassesById(int classesId);
	
	//班级与班主任之间一对一关系查询:
	public List<Classes> selectAllClassessAndMasterTeachers();
	public List<Classes> selectAllClassesAndMasterTeacherById(int masterTeacherId);
	
	//班级与学生之间一对多关系查询:
	public Classes selectClassesToStudentsById(int id);
	public Classes selectClassesToStudentsById2(int id);
}
