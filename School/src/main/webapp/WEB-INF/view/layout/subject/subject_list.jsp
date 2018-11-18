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
    <TH>开课时间</TH>
    <TH>停课时间</TH>
    <TH>操作</TH>
  </TR>
<c:choose>
  <c:when test = "${number_of_subjects == 0}">
  <TR>
    <TD colspan = "6">
      &nbsp; <FONT size = "4" face = "新宋体" color = "Red"> 学校初建,还没有设计好课程,太懒了! </FONT>
    </TD>
  </TR>
  </c:when>
  <c:otherwise>
    <c:forEach items = "${subjectList}" var = "subject" varStatus = "row">
      <c:choose>
        <c:when test = "${row.count % 2 == 0}">
          <TR bgcolor = "#D1EEEE">
        </c:when>
        <c:otherwise>
          <TR>
        </c:otherwise>
      </c:choose>
            <!-- TD>&nbsp;${row.index}</TD -->
            <TD>&nbsp;${row.count}</TD>
            <TD align = "center"> ${subject.id}  </TD>
            <TD>&nbsp;${subject.name}</TD>
            <TD align = "center">
              <!-- fmt:formatDate value = "${subject.enterTime}" pattern = "yyyy-MM-dd HH:mm:ss"/ -->
              <fmt:formatDate value = "${subject.enterTime}" pattern = "yyyy-MM-dd"/>
            </TD>
            <TD align = "center">
              <!-- fmt:formatDate value = "${subject.leaveTime}" pattern = "yyyy-MM-dd HH:mm:ss"/ -->
              <fmt:formatDate value = "${subject.leaveTime}" pattern = "yyyy-MM-dd"/>
            </TD>
            
            <TD align = "center">
              <A href="${contextPath}/get_subject.do?id=${subject.id}"> 查看 </A>
              <A href="${contextPath}/del_subject/${subject.id}.do"> 删除 </A>
              <A href="${contextPath}/updateSubjectLeave.do?id=${subject.id}"> 停课 </A>
            </TD>
          </TR>
    </c:forEach>
          <TR>
            <TD colspan = "6">
              &nbsp;共开设有 <FONT size = "5" face = "新宋体" color = "Red"> ${number_of_subjects} </FONT>门课程
            </TD>
          </TR>
  </c:otherwise>
</c:choose>
</TABLE>
