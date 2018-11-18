<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- 获取上下文路径:第一种方法"c:set+EL表达式" -->
<c:set var = "contextPath" value = "${pageContext.request.contextPath}" />

<!DOCTYPE HTML>
<form id = "addBook" name = "addBook" method = "POST" action = "${contextPath}/add_book.do">
<TABLE width = "100%" border="1" cellspacing="1" cellpadding="1" align = "center">
  <TR>
    <TD>编号:&nbsp; <input id = "bookId" name = "bookId" type = "text"/></TD>
  </TR>
  <TR>
    <TD>名称:&nbsp; <input id = "bookName" name = "bookName" type = "text"/></TD>
  </TR>
  <TR>
    <TD>作者:&nbsp; <input id = "bookAuthor" name = "bookAuthor" type = "text"/></TD>
  </TR>
  <TR>
    <TD>出版社:&nbsp;
      <SELECT id = "publisherId" name = "publisherId">
        <OPTION value = "0" selected>===选择出版社===</OPTION>
    <c:forEach var = "publisher" varStatus = "row" items = "${publisherList}">
        <OPTION value = "${publisher.id}">${publisher.name}</OPTION>
    </c:forEach>
      </SELECT>
    </TD>
  </TR>
  <TR>
    <TD align = "center">
      &nbsp; <input type = "submit" id = "btnAddBook" name = "btnAddBook" value = "添加"/>
      &nbsp; <input type = "reset" id = "btnAddBookReset" name = "btnAddBookReset" value = "重置"/>
    </TD>
  </TR>
</TABLE>
</form>