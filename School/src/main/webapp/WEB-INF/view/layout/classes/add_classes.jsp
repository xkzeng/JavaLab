<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- 获取上下文路径:第一种方法"c:set+EL表达式" -->
<c:set var = "contextPath" value = "${pageContext.request.contextPath}" />

<!DOCTYPE HTML>
<form id = "addClasses" name = "addClasses" method = "POST" action = "${contextPath}/add_classes.do">
<TABLE width = "100%" border="1" cellspacing="1" cellpadding="1" align = "center">
  <TR>
    <TD>编号:&nbsp; <input id = "classesId" name = "classesId" type = "text"/></TD>
  </TR>
  <TR>
    <TD>名称:&nbsp; <input id = "classesName" name = "classesName" type = "text"/></TD>
  </TR>
  <TR>
    <TD>班主任:&nbsp;
      <SELECT id = "masterTeacherId" name = "masterTeacherId">
        <OPTION value = "0" selected>===选择班主任===</OPTION>
    <c:forEach var = "masterTeacher" varStatus = "row" items = "${masterTeacherList}">
        <OPTION value = "${masterTeacher.id}">${masterTeacher.name}</OPTION>
    </c:forEach>
      </SELECT>
    </TD>
  </TR>
  <TR>
    <TD align = "center">
      &nbsp; <input type = "submit" id = "btnAddClasses" name = "btnAddClasses" value = "添加"/>
      &nbsp; <input type = "reset" id = "btnAddClassesReset" name = "btnAddClassesReset" value = "重置"/>
    </TD>
  </TR>
</TABLE>
</form>