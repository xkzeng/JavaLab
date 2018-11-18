<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 获取上下文路径:第一种方法"c:set+EL表达式" -->
<c:set var = "contextPath" value = "${pageContext.request.contextPath}" />

<!DOCTYPE HTML>
<TABLE width = "100%" border="1" cellspacing="1" cellpadding="1">
  <TR bgcolor="#DEDEDE">
    <TH>序号</TH>
    <TH>课程编号</TH>
    <TH>课程名称</TH>
    <TH>选修人次</TH>
  </TR>
<c:choose>
  <c:when test = "${number_of_subject_selectcount == 0}">
  <TR>
    <TD colspan = "4">
      &nbsp; <FONT size = "4" face = "新宋体" color = "Red"> 暂时没有选课统计信息! </FONT>
    </TD>
  </TR>
  </c:when>
  <c:otherwise>
    <c:forEach items = "${subjectSelectCountList}" var = "statistics" varStatus = "row">
      <c:choose>
        <c:when test = "${row.count % 2 == 0}">
          <TR bgcolor = "#D1EEEE">
        </c:when>
        <c:otherwise>
          <TR>
        </c:otherwise>
      </c:choose>
            <TD>&nbsp;${row.count}</TD>
            <TD align = "center"> ${statistics.subjectId} </TD>
            <TD>&nbsp;
               <A href="${contextPath}/get_subject_students.do?subjectId=${statistics.subjectId}"> ${statistics.subjectName} </A>
            </TD>
            <TD align = "center"> ${statistics.selectCount} </TD>
          </TR>
    </c:forEach>
          <TR>
            <TD colspan = "4">
              &nbsp;共有 <FONT size = "5" face = "新宋体" color = "Red"> ${number_of_subject_selectcount} </FONT>门课程的热度信息,加油！！！
            </TD>
          </TR>
  </c:otherwise>
</c:choose>
</TABLE>
