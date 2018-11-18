<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>

<!DOCTYPE HTML>
<TABLE width = "90%" align = "center" valign = "top">
  <TR valign="top" bgcolor="#DEDEDE">
    <TH align = "center"> <FONT size = "5" face = "新宋体"> ${student.name}同学的选课信息:Students && Subjects</FONT></TH>
    <TH align = "center"> <FONT size = "5" face = "新宋体"> 给${student.name}同学选课 </FONT></TH>
  </TR>
  
  <TR>
    <TD align = "center" valign = "top">
      <jsp:include page = "student_subjects_list.jsp" flush = "true"/>
    </TD>
    <TD align = "center" valign = "top" rowspan = "2">
      <jsp:include page = "add_student_subjects.jsp" flush = "true"/>
    </TD>
  </TR>
  <TR>
    <TD align = "center" valign = "top">
      <TABLE width = "100%" align = "center" valign = "top">
        <TR valign="top" bgcolor="#DEDEDE">
          <TH align = "center"> <FONT size = "5" face = "新宋体"> ${student.name}同学的选课信息:StudentsSubjects </FONT></TH>
        </TR>
        <TR>
          <TD> <jsp:include page = "studentsubjects_list.jsp" flush = "true"/> </TD>
        </TR>
      </TABLE>
    </TD>
  </TR>
</TABLE>
<BR>

