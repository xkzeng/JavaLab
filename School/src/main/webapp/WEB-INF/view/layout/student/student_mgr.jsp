<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>

<!DOCTYPE HTML>
<TABLE width = "90%" align = "center" valign = "middle">
  <TR valign="middle" bgcolor="#DEDEDE">
    <TH align = "center"> <FONT size = "5" face = "新宋体">学生信息</FONT></TH>
  </TR>
  <TR>
    <TD align = "center" valign = "top">
      <jsp:include page = "add_student.jsp" flush = "true"/>
    </TD>
  </TR>
  <TR>
    <TD align = "center" valign = "top">
      <jsp:include page = "student_list.jsp" flush = "true"/>
    </TD>
  </TR>
</TABLE>
<BR>
