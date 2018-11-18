<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 获取上下文路径:第二种方法"直接用JSP的语法格式" -->
<%
  String conextPath = request.getContextPath();
%>

<!DOCTYPE HTML>
<TABLE width = "100%" border="1" cellspacing="1" cellpadding="1">
  <TR bgcolor="#DEDEDE">
    <!-- TH>索引</TH-->
    <TH>序号</TH>
    <TH>编号</TH>
    <TH>名称</TH>
    <TH>操作</TH>
  </TR>
<c:choose>
  <c:when test = "${number_of_publishers == 0}">
  <TR>
    <TD colspan = "4">
      &nbsp; <FONT size = "4" face = "新宋体" color = "Red"> 学校没钱跟任何出版社合作! </FONT>
    </TD>
  </TR>
  </c:when>
  <c:otherwise>
    <c:forEach items = "${publisherList}" var = "publisher" varStatus = "row">
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
            <TD align = "center"> ${publisher.id} </TD>
            <TD>&nbsp;${publisher.name} </TD>
            <TD align = "center">
              <A href="<%=conextPath%>/get_publisher.do?id=${publisher.id}"> 查看 </A>
              <A href="<%=conextPath%>/del_publisher.do?id=${publisher.id}"> 删除 </A>
            </TD>
          </TR>
    </c:forEach>
          <TR>
            <TD colspan = "4">
              &nbsp;共合作了 <FONT size = "5" face = "新宋体" color = "Red"> ${number_of_publishers} </FONT> 家出版社
            </TD>
          </TR>
  </c:otherwise>
</c:choose>
</TABLE>