<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 获取上下文路径:第一种方法"c:set+EL表达式" -->
<c:set var = "contextPath" value = "${pageContext.request.contextPath}"/>

<!DOCTYPE HTML>
<TABLE width = "100%" border="1" cellspacing="1" cellpadding="1">
  <TR bgcolor="#DEDEDE">
    <TH>序号</TH>
    <TH>学号</TH>
    <TH>姓名</TH>
    <TH>性别</TH>
    <TH>年龄</TH>
    <TH>课程编号</TH>
    <TH>课程名称</TH>
    <TH>操作</TH>
  </TR>
<c:choose>
  <c:when test = "${number_of_studentsubjects == 0}">
  <TR>
    <TD colspan = "8">
      &nbsp; <FONT size = "4" face = "新宋体" color = "Red"> ${student.name}同学还没选课呢,赶紧督促她选课! </FONT>
    </TD>
  </TR>
  </c:when>
  <c:otherwise>
    <c:forEach items = "${studentSubjectsList}" var = "studentsubject" varStatus = "row">
      <c:choose>
        <c:when test = "${row.count % 2 == 0}">
          <TR bgcolor = "#D1EEEE">
        </c:when>
        <c:otherwise>
          <TR>
        </c:otherwise>
      </c:choose>
            <TD>&nbsp;${row.count}</TD>
            <TD align = "center"> ${studentsubject.studentId}  </TD>
            <TD>&nbsp;${studentsubject.studentName}</TD>
            <TD align = "center">
               <c:choose>
                  <c:when test = "${studentsubject.studentSex == 0}"> 女 </c:when>
                  <c:otherwise> 男 </c:otherwise>
               </c:choose>
            </TD>
            <TD>&nbsp;${studentsubject.studentAge}</TD>
            <TD align = "center">&nbsp;${studentsubject.subjectId}</TD>
            <TD>&nbsp;${studentsubject.subjectName}</TD>
            <TD align = "center">
              <A href="${contextPath}/del_student_subject.do?studentId=${studentsubject.studentId}&subjectId=${studentsubject.subjectId}"> 取消 </A>
            </TD>
          </TR>
    </c:forEach>
          <TR>
            <TD colspan = "8">
              &nbsp;${student.name}同学共选修 <FONT size = "5" face = "新宋体" color = "Red"> ${number_of_studentsubjects} </FONT> 门课程
            </TD>
          </TR>
  </c:otherwise>
</c:choose>
</TABLE>
