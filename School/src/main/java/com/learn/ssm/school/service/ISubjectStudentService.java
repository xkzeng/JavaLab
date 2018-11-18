package com.learn.ssm.school.service;

import java.util.List;

import com.learn.ssm.school.entity.StudentsSubjects;
import com.learn.ssm.school.entity.Subject;

public interface ISubjectStudentService
{
  //��:
	public int insertSubjectStudent(int subjectId, int studentId);
	public int insertSubjectStudents(int subjectId, int studentIds[]);
	public int insertStudentSubjects(int studentId, int subjectIds[]);
	
	//ɾ:
	public int deleteAllSubjectStudents();
	public int deleteSubjectStudentsBySubjectId(int subjectId);
	public int deleteSubjectStudentsBySubjectIds(int subjectIds[]);
	public int deleteStudentSubjectsByStudentId(int studentId);
	public int deleteStudentSubjectsByStudentIds(int studentIds[]);
	public int deleteSubjectStudentByRowId(int id);
	public int deleteSubjectStudentByRowIds(int ids[]);
	public int deleteSubjectStudentBySubjectIdAndStudentId(int subId, int stuId);
	
	//��:
	//��ҵ������;
	
	//��: һ�ſγ̱���λѧ��ѡ��;
	public List<StudentsSubjects> selectAllSubjectsStudents();
	public List<StudentsSubjects> selectSubjectStudentsBySubjectId(int subjectId);
	public List<Subject> selectAllSubjectsToStudents();
	public Subject selectSubjectToStudentsBySubjectId(int subjectId);
}
