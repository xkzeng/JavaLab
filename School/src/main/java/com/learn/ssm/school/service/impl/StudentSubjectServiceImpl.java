package com.learn.ssm.school.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.learn.ssm.school.entity.Student;
import com.learn.ssm.school.entity.StudentsSubjects;
import com.learn.ssm.school.mapper.IStudentSubjectMapper;
import com.learn.ssm.school.service.IStudentSubjectService;

import com.wrapper.Print;

@Service(value = "studentSubjectService")
public class StudentSubjectServiceImpl implements IStudentSubjectService
{
	@Resource(name = "studentSubjectMapper")
	private IStudentSubjectMapper studentSubjectMapper;
	
	public StudentSubjectServiceImpl()
	{
	}
	
  //��:
	public int insertStudentSubject(int stuId, int subId)
	{
		Student student = this.studentSubjectMapper.selectStudentToSubjectByStudentIdAndSubjectId(stuId, subId);
		if(student != null)
		{
			Print.info("ѧ��%d�Ѿ�ѡ���˿γ�%d,�����ظ�ѡ��ͬһ�ſγ�!", stuId, subId);
			return 0;
		}
		
		return this.studentSubjectMapper.insertStudentSubject(stuId, subId);
	}
	
	public int insertStudentSubjects(int stuId, int subIds[])
	{
		return this.studentSubjectMapper.insertStudentSubjects(stuId, subIds);
	}
	
	public int insertSubjectStudents(int subId, int stuIds[])
	{
		return this.studentSubjectMapper.insertSubjectStudents(subId, stuIds);
	}
	
	//ɾ:
	public int deleteAllStudentSubjects()
	{
		return this.studentSubjectMapper.deleteAllStudentSubjects();
	}
	
	public int deleteStudentSubjectsByStudentId(int studentId)
	{
		return this.studentSubjectMapper.deleteStudentSubjectsByStudentId(studentId);
	}
	
	public int deleteStudentSubjectsByStudentIds(int studentIds[])
	{
		return this.studentSubjectMapper.deleteStudentSubjectsByStudentIds(studentIds);
	}
	
	public int deleteSubjectStudentsBySubjectId(int subjectId)
	{
		return this.studentSubjectMapper.deleteSubjectStudentsBySubjectId(subjectId);
	}
	
	public int deleteSubjectStudentsBySubjectIds(int subjectIds[])
	{
		return this.studentSubjectMapper.deleteSubjectStudentsBySubjectIds(subjectIds);
	}
	
	public int deleteStudentSubjectByRowId(int id)
	{
		return this.studentSubjectMapper.deleteStudentSubjectByRowId(id);
	}
	
	public int deleteStudentSubjectByRowIds(int ids[])
	{
		return this.studentSubjectMapper.deleteStudentSubjectByRowIds(ids);
	}
	
	public int deleteStudentSubjectByStudentIdAndSubjectId(int stuId, int subId)
	{
		return this.studentSubjectMapper.deleteStudentSubjectByStudentIdAndSubjectId(stuId, subId);
	}
	
	//��:
	//��ҵ������
	
	//��: һλѧ��ѡ�޶��ſγ�
	public List<StudentsSubjects> selectAllStudentsSubjects()
	{
		return this.studentSubjectMapper.selectAllStudentsSubjects();
	}
	
	public List<StudentsSubjects> selectStudentSubjectsByStudentId(int studentId)
	{
		return this.studentSubjectMapper.selectStudentSubjectsByStudentId(studentId);
	}
	
	public List<Student> selectAllStudentsToSubjects()
	{
		return this.studentSubjectMapper.selectAllStudentsToSubjects();
	}
	
	public Student selectStudentToSubjectsByStudentId(int studentId)
	{
		return this.studentSubjectMapper.selectStudentToSubjectsByStudentId(studentId);
	}
	
	public StudentsSubjects selectStudentSubjectByStudentIdAndSubjectId(int studentId, int subjectId)
	{
		return this.studentSubjectMapper.selectStudentSubjectByStudentIdAndSubjectId(studentId, subjectId);
	}
	
	public Student selectStudentToSubjectByStudentIdAndSubjectId(int studentId, int subjectId)
	{
		return this.studentSubjectMapper.selectStudentToSubjectByStudentIdAndSubjectId(studentId, subjectId);
	}
}
