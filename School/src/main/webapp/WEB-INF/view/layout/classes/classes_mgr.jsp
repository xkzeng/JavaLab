<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>

<!DOCTYPE HTML>
<TABLE width = "90%" align = "center" valign = "middle">
  <TR valign="middle" bgcolor="#DEDEDE">
    <TH align = "center"> <FONT size = "5" face = "新宋体">班级信息</FONT></TH>
    <TH align = "center"> <FONT size = "5" face = "新宋体">班主任列表</FONT></TH>
  </TR>
  <TR>
    <TD align = "center" valign = "top">
      <jsp:include page = "add_classes.jsp" flush = "true"/>
    </TD>
    <TD align = "center" valign = "top" rowspan = "2">
      <jsp:include page = "../teacher/master_teacher_list.jsp" flush = "true"/>
    </TD>
  </TR>
  <TR>
    <TD align = "center" valign = "top">
      <jsp:include page = "classes_list.jsp" flush = "true"/>
    </TD>
  </TR>
</TABLE>
<BR>

<TABLE width = "90%" align = "center" valign = "middle">
  <TR valign="middle" bgcolor="#DEDEDE">
    <TH align = "center"> <FONT size = "5" face = "新宋体">班级和班主任之间一对一关系的分配结果</FONT></TH>
  </TR>
  <TR>
    <TD align = "center" valign = "top">
      <jsp:include page = "classes_master_teacher_list.jsp" flush = "true"/>
    </TD>
  </TR>
</TABLE>