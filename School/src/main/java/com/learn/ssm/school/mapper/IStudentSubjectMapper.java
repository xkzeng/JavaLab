package com.learn.ssm.school.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import com.learn.ssm.school.entity.Student;
import com.learn.ssm.school.entity.Subject;
import com.learn.ssm.school.entity.StudentsSubjects;

@Repository(value = "studentSubjectMapper")
public interface IStudentSubjectMapper
{
  //增:
	public int insertStudentSubject(@Param(value = "studentId") int stuId, @Param(value = "subjectId") int subId);
	public int insertStudentSubjects(@Param(value = "studentId") int stuId, @Param(value = "subjectIds") int subIds[]);
	public int insertSubjectStudents(@Param(value = "subjectId") int subId, @Param(value = "studentIds") int stuIds[]);
	
	//删:
	public int deleteAllStudentSubjects();
	public int deleteStudentSubjectsByStudentId(int studentId);
	public int deleteStudentSubjectsByStudentIds(int studentIds[]);
	public int deleteSubjectStudentsBySubjectId(int subjectId);
	public int deleteSubjectStudentsBySubjectIds(int subjectIds[]);
	public int deleteStudentSubjectByRowId(@Param(value = "rowId") int id);
	public int deleteStudentSubjectByRowIds(int ids[]);
	public int deleteStudentSubjectByStudentIdAndSubjectId(@Param(value = "studentId") int stuId, @Param(value = "subjectId") int subId);
	
	//改:
	//无业务需求
	
	//查: 一位学生选修多门课程
	public List<StudentsSubjects> selectAllStudentsSubjects();
	public List<StudentsSubjects> selectStudentSubjectsByStudentId(int studentId);
	public List<Student> selectAllStudentsToSubjects();
	public Student selectStudentToSubjectsByStudentId(int studentId);
	
	public StudentsSubjects selectStudentSubjectByStudentIdAndSubjectId(int studentId, int subjectId);
	public Student selectStudentToSubjectByStudentIdAndSubjectId(@Param(value = "studentId") int stuId, @Param(value = "subjectId") int subId);
}
