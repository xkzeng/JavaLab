<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 获取上下文路径:第一种方法"c:set+EL表达式" -->
<c:set var = "contextPath" value = "${pageContext.request.contextPath}" />

<!DOCTYPE HTML>
<TABLE width = "100%" border="1" cellspacing="1" cellpadding="1">
  <TR bgcolor="#DEDEDE">
    <!-- TH>索引</TH -->
    <TH>序号</TH>
    <TH>编号</TH>
    <TH>名称</TH>
    <TH>作者</TH>
    <TH>出版社</TH>
    <TH>操作</TH>
  </TR>
<c:choose>
  <c:when test = "${number_of_books == 0}">
  <TR>
    <TD colspan = "6">
      &nbsp; <FONT size = "4" face = "新宋体" color = "Red"> 学校没钱买图书! </FONT>
    </TD>
  </TR>
  </c:when>
  <c:otherwise>
    <c:forEach items = "${bookList}" var = "book" varStatus = "row">
      <c:choose>
        <c:when test = "${row.count % 2 == 0}">
          <TR bgcolor = "#D1EEEE">
        </c:when>
        <c:otherwise>
          <TR>
        </c:otherwise>
      </c:choose>
            <!-- TD>&nbsp;${row.index}</TD -->
            <TD>&nbsp;${row.count} </TD>
            <TD align = "center"> ${book.id} </TD>
            <TD>&nbsp;${book.name} </TD>
            <TD>&nbsp;${book.author} </TD>
            <TD>&nbsp;${book.publisher.name} </TD>
            <TD align = "center">
              <A href="${contextPath}/get_book.do?id=${book.id}"> 查看 </A>
              <A href="${contextPath}/del_book.do?id=${book.id}"> 删除 </A>
            </TD>
          </TR>
    </c:forEach>
          <TR>
            <TD colspan = "6">
              &nbsp;共有 <FONT size = "5" face = "新宋体" color = "Red"> ${number_of_books} </FONT>本图书
            </TD>
          </TR>
  </c:otherwise>
</c:choose>
</TABLE>