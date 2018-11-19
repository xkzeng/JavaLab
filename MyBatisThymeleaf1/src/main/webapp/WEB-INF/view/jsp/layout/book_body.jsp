<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <DIV align = "center">
    <TABLE width = 70% border="1" cellspacing="1" cellpadding="1">
      <TR>
        <TD>
          &nbsp;共有 <FONT size = "5" face = "新宋体" color = "Red"> ${number_of_books} </FONT>本图书
          &nbsp;这个页面是使用JSP/JSTL渲染出来的;
        </TD>
      </TR>
    </TABLE>
  </DIV>
  <BR>

  <DIV align = "center">
    <TABLE width = 70% border="1" cellspacing="1" cellpadding="1">
      <TR align="middle" bgcolor="#DEDEDE">
        <TH>索引</TH>
        <TH>序号</TH>
        <TH>编号</TH>
        <TH>名称</TH>
        <TH>作者</TH>
      </TR>
      
<c:forEach items = "${bookList}" var = "book" varStatus = "row">
   <c:choose>
      <c:when test = "${row.count % 2 == 0}">
      <TR bgcolor = "#D1EEEE">
      </c:when>
      <c:otherwise>
      <TR>
      </c:otherwise>
   </c:choose>
        <TD>&nbsp;${row.index}</TD>
        <TD>&nbsp;${row.count}</TD>
        <TD>&nbsp;${book.bookId}  </TD>
        <TD>&nbsp;${book.bookName}</TD>
        <TD>&nbsp;${book.author}  </TD>
      </TR>
</c:forEach>
    </TABLE>
  </DIV>