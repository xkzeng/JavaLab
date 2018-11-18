<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<TABLE width = "90%" border="1" cellspacing="1" cellpadding="1" align = "center">
  <TR>
    <TD>编号:&nbsp; <input id = "classesId" name = "classesId" type = "text" value = "${classes.id}"/> == ${classes2.id}</TD>
  </TR>
  <TR>
    <TD>名称:&nbsp; <input id = "classesName" name = "classesName" type = "text" value = "${classes.name}"/> == ${classes2.name}</TD>
  </TR>
  <TR>
    <TD>班主任:&nbsp;
      <SELECT id = "masterTeacherId" name = "masterTeacherId">
        <OPTION value = "0">===选择班主任===</OPTION>
    <c:forEach var = "masterTeacher" varStatus = "row" items = "${masterTeacherList}">
        <OPTION value = "${masterTeacher.id}"
          <c:if test = "${masterTeacher.id == classes.masterTeacher.id}"> selected </c:if>
        >
           ${masterTeacher.name}
        </OPTION>
    </c:forEach>
      </SELECT>
    </TD>
  </TR>
</TABLE>
