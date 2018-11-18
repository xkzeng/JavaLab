package com.learn.ssm.school.mapper;

import java.util.List;
import java.sql.Timestamp;
import org.springframework.stereotype.Repository;
import com.learn.ssm.school.entity.Subject;

@Repository(value = "subjectMapper")
public interface ISubjectMapper
{
  //Ôö:
	public int insertSubject(Subject subject);
	
	//É¾:
	public int deleteAllSubjects();
	public int deleteSubjects(int ids[]);
	public int deleteSubject(Subject subject);
	public int deleteSubjectById(int id);
	
	//¸Ä:
	public int updateSubject(Subject subject);
	public int updateSubjectLeaveTimeById(int id);
	
	//²é:
	public List<Subject> selectAllSubjects();
	public List<Subject> selectOpendSubjects();
	public List<Subject> selectCloseSubjects();
	public Subject selectSubjectById(int id);
}
