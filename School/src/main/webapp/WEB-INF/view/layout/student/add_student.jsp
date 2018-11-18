<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- 获取上下文路径:第一种方法"c:set+EL表达式" -->
<c:set var = "contextPath" value = "${pageContext.request.contextPath}" />

<!DOCTYPE HTML>
<form id = "addStudent" name = "addStudent" method = "POST" action = "${contextPath}/add_student.do">
<TABLE width = "100%" border="1" cellspacing="1" cellpadding="1" align = "center">
  <TR>
    <TD>学号:&nbsp; <input id = "studentId" name = "studentId" type = "text"/></TD>
  </TR>
  <TR>
    <TD>姓名:&nbsp; <input id = "studentName" name = "studentName" type = "text"/></TD>
  </TR>
  <TR>
    <TD>性别:&nbsp;
       <input id = "studentSex" name = "studentSex" type = "radio" value = "0" checked = "checked" />女
       <input id = "studentSex" name = "studentSex" type = "radio" value = "1"/>男
    </TD>
  </TR>
  <TR>
    <TD>年龄:&nbsp; <input id = "studentAge" name = "studentAge" type = "text"/></TD>
  </TR>
  <TR>
    <TD>班级:&nbsp;
      <SELECT id = "classesId" name = "classesId">
        <OPTION value = "0" selected>===选择班级===</OPTION>
    <c:forEach var = "classes" varStatus = "row" items = "${classessList}">
        <OPTION value = "${classes.id}">${classes.name}</OPTION>
    </c:forEach>
      </SELECT>
    </TD>
  </TR>
  <TR>
    <TD align = "center">
      &nbsp; <input type = "submit" id = "btnAddStudent" name = "btnAddStudent" value = "添加"/>
      &nbsp; <input type = "reset" id = "btnAddStudentReset" name = "btnAddStudentReset" value = "重置"/>
    </TD>
  </TR>
</TABLE>
</form>