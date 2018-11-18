<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>

<!DOCTYPE HTML>
<TABLE width = "90%" align = "center" valign = "top">
  <TR valign="middle" bgcolor="#DEDEDE">
    <TH align = "center"> <FONT size = "5" face = "新宋体"> 使用实体类StudentsSubjects </FONT></TH>
  </TR>
  <TR>
    <TD align = "center" valign = "top">
      <jsp:include page = "subjectsstudents_list.jsp" flush = "true"/>
    </TD>
  </TR>
</TABLE>
<BR>

<TABLE width = "90%" align = "center" valign = "top">
  <TR valign="middle" bgcolor="#DEDEDE">
    <TH align = "center"> <FONT size = "5" face = "新宋体"> 使用实体类Students和Subjects</FONT></TH>
  </TR>
  <TR>
    <TD align = "center" valign = "top">
      <jsp:include page = "subjects_students_list.jsp" flush = "true"/>
    </TD>
  </TR>
</TABLE>
<BR>
