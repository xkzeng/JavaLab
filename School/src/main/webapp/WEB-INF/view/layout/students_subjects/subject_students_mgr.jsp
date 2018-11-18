<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>

<!DOCTYPE HTML>
<TABLE width = "90%" align = "center" valign = "top">
  <TR valign="top" bgcolor="#DEDEDE">
    <TH align = "center"> <FONT size = "5" face = "新宋体"> ${subject.name}课的选修情况:Students && Subjects</FONT></TH>
  </TR>
  
  <TR>
    <TD align = "center" valign = "top">
      <jsp:include page = "subject_students_list.jsp" flush = "true"/>
    </TD>
  </TR>
  <TR>
    <TD align = "center" valign = "top">
      <TABLE width = "100%" align = "center" valign = "top">
        <TR valign="top" bgcolor="#DEDEDE">
          <TH align = "center"> <FONT size = "5" face = "新宋体"> ${subject.name}课的选修情况:StudentsSubjects </FONT></TH>
        </TR>
        <TR>
          <TD> <jsp:include page = "subjectstudents_list.jsp" flush = "true"/> </TD>
        </TR>
      </TABLE>
    </TD>
  </TR>
</TABLE>
<BR>

