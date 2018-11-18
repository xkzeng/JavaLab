<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- 获取上下文路径:第二种方法"直接用JSP的语法格式" -->
<%
  String conextPath = request.getContextPath();
%>

<!DOCTYPE HTML>
<form id = "addMasterTeacher" name = "addMasterTeacher" method = "POST" action = "<%=conextPath%>/add_master_teacher.do">
<TABLE width = "100%" border="1" cellspacing="1" cellpadding="1" align = "center">
  <TR>
    <TD>编号:&nbsp; <input id = "masterTeacherId" name = "masterTeacherId" type = "text"/></TD>
  </TR>
  <TR>
    <TD>姓名:&nbsp; <input id = "masterTeacherName" name = "masterTeacherName" type = "text"/></TD>
  </TR>
  <TR>
    <TD align = "center">
      &nbsp; <input type = "submit" id = "btnAddMasterTeacher" name = "btnAddMasterTeacher" value = "添加"/>
      &nbsp; <input type = "reset" id = "btnAddMasterTeacherReset" name = "btnAddMasterTeacherReset" value = "重置"/>
    </TD>
  </TR>
</TABLE>
</form>