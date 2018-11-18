<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 获取上下文路径:第一种方法"c:set+EL表达式" -->
<c:set var = "contextPath" value = "${pageContext.request.contextPath}" />

<!DOCTYPE HTML>
<TABLE width = "90%" border="1" cellspacing="1" cellpadding="1">
  <TR bgcolor="#DEDEDE">
    <TH colspan = "7"> <FONT size = "5" face = "新宋体" color = "Red"> 结果集嵌套映射 </FONT> </TH>
  </TR>
  <TR bgcolor="#DEDEDE">
    <!-- TH>索引</TH -->
    <TH>序号</TH>
    <TH>学号</TH>
    <TH>姓名</TH>
    <TH>性别</TH>
    <TH>年龄</TH>
    <TH>入校时间</TH>
    <TH>毕业时间</TH>
  </TR>
<c:choose>
  <c:when test = "${number_of_students == 0}">
  <TR>
    <TD colspan = "7">
      &nbsp; <FONT size = "4" face = "新宋体" color = "Red"> 没有招到学生,好惨呐! </FONT>
    </TD>
  </TR>
  </c:when>
  <c:otherwise>
    <c:forEach items = "${studentsList}" var = "student" varStatus = "row">
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
            <TD align = "center"> ${student.id}  </TD>
            <TD>&nbsp;${student.name}</TD>
            <TD>&nbsp;
               <c:choose>
                  <c:when test = "${student.sex == 0}"> 女 </c:when>
                  <c:otherwise> 男 </c:otherwise>
               </c:choose>
            </TD>
            <TD>&nbsp;${student.age}</TD>
            <TD align = "center">
              <!-- fmt:formatDate value = "${subject.enterTime}" pattern = "yyyy-MM-dd HH:mm:ss"/ -->
              <fmt:formatDate value = "${student.enterTime}" pattern = "yyyy-MM-dd"/>
            </TD>
            <TD align = "center">
              <!-- fmt:formatDate value = "${subject.leaveTime}" pattern = "yyyy-MM-dd HH:mm:ss"/ -->
              <fmt:formatDate value = "${student.leaveTime}" pattern = "yyyy-MM-dd"/>
            </TD>
          </TR>
    </c:forEach>
          <TR>
            <TD colspan = "7">
              &nbsp;共招收 <FONT size = "5" face = "新宋体" color = "Red"> ${number_of_students} </FONT>名学生
            </TD>
          </TR>
  </c:otherwise>
</c:choose>
</TABLE>
