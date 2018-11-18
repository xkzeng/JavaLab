<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- 获取上下文路径:第二种方法"直接用JSP的语法格式" -->
<%
  String conextPath = request.getContextPath();
%>

<!DOCTYPE HTML>
<form id = "addPublisher" name = "addPublisher" method = "POST" action = "<%=conextPath%>/add_publisher.do">
<TABLE width = "100%" border="1" cellspacing="1" cellpadding="1" align = "center">
  <TR>
    <TD>编号:&nbsp; <input id = "publisherId" name = "publisherId" type = "text"/></TD>
  </TR>
  <TR>
    <TD>名称:&nbsp; <input id = "publisherName" name = "publisherName" type = "text"/></TD>
  </TR>
  <TR>
    <TD align = "center">
      &nbsp; <input type = "submit" id = "btnAddPublisher" name = "btnAddPublisher" value = "添加"/>
      &nbsp; <input type = "reset" id = "btnAddPublisherReset" name = "btnAddPublisherReset" value = "重置"/>
    </TD>
  </TR>
</TABLE>
</form>