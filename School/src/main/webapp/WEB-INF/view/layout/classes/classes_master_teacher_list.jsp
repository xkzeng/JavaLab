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
    <TH>班主任</TH>
    <TH>开设时间</TH>
    <TH>关停时间</TH>
    <TH>操作</TH>
  </TR>
<c:choose>
  <c:when test = "${number_of_classess_and_master_teachers == 0}">
  <TR>
    <TD colspan = "7">
      &nbsp; <FONT size = "4" face = "新宋体" color = "Red"> 学校初建,请尽快设计好班级,并指定班主任,以免耽误您赚钱的宏图大业! </FONT>
    </TD>
  </TR>
  </c:when>
  <c:otherwise>
    <c:forEach items = "${classessAnsMasterTeachersList}" var = "classes" varStatus = "row">
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
            <TD align = "center"> ${classes.id}  </TD>
            <TD>&nbsp;${classes.name}</TD>
            <TD>&nbsp;${classes.masterTeacher.name}</TD>
            <TD align = "center">
              <!-- fmt:formatDate value = "${classes.enterTime}" pattern = "yyyy-MM-dd HH:mm:ss"/ -->
              <fmt:formatDate value = "${classes.enterTime}" pattern = "yyyy-MM-dd"/>
            </TD>
            <TD align = "center">
              <!-- fmt:formatDate value = "${classes.leaveTime}" pattern = "yyyy-MM-dd HH:mm:ss"/ -->
              <fmt:formatDate value = "${classes.leaveTime}" pattern = "yyyy-MM-dd"/>
            </TD>
            
            <TD align = "center">
              <A href="${contextPath}/get_classes.do?id=${classes.id}"> 查看 </A>
            </TD>
          </TR>
    </c:forEach>
          <TR>
            <TD colspan = "7">
              &nbsp;共有 <FONT size = "5" face = "新宋体" color = "Red"> ${number_of_classess_and_master_teachers} </FONT>个班
            </TD>
          </TR>
  </c:otherwise>
</c:choose>
</TABLE>
