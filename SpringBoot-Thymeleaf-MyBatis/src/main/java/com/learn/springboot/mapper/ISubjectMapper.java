package com.learn.springboot.mapper;

import java.util.List;
import java.sql.Timestamp;

//import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.learn.springboot.entity.Subject;

@Repository(value = "subjectMapper") //此处无效;
//@Mapper //或者使用注解@MapperScan修饰SpringBoot的启动类,并指定Mapper所在的包;
public interface ISubjectMapper
{
	public List<Subject> selectAllSubjects();
}
