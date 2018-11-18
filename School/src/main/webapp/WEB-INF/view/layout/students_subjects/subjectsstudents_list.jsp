<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 获取上下文路径:第一种方法"c:set+EL表达式" -->
<c:set var = "contextPath" value = "${pageContext.request.contextPath}"/>

<!DOCTYPE HTML>
<TABLE width = "100%" border="1" cellspacing="1" cellpadding="1">
  <TR bgcolor="#DEDEDE">
    <TH>序号</TH>
    <TH>选课序号</TH>
    <TH>课程编号</TH>
    <TH>课程名称</TH>
    <TH>学号</TH>
    <TH>姓名</TH>
    <TH>性别</TH>
    <TH>年龄</TH>
    <TH>操作</TH>
  </TR>
<c:choose>
  <c:when test = "${number_of_subjectsstudents == 0}">
  <TR>
    <TD colspan = "9">
      &nbsp; <FONT size = "4" face = "新宋体" color = "Red"> 没有学生选课呢,赶紧做好宣传! </FONT>
    </TD>
  </TR>
  </c:when>
  <c:otherwise>
    <c:forEach items = "${subjectsStudentsList}" var = "subjectstudent" varStatus = "row">
      <c:choose>
        <c:when test = "${row.count % 2 == 0}">
          <TR bgcolor = "#D1EEEE">
        </c:when>
        <c:otherwise>
          <TR>
        </c:otherwise>
      </c:choose>
            <TD>&nbsp;${row.count}</TD>
            <TD align = "center">&nbsp;
               <c:choose>
                  <c:when test = "${subjectstudent.studentId == 0}"> --- </c:when>
                  <c:otherwise> ${subjectstudent.id} </c:otherwise>
               </c:choose>
            </TD>
            <TD align = "center">&nbsp;${subjectstudent.subjectId}</TD>
            <TD>&nbsp;
               <A href="${contextPath}/get_subject_students.do?subjectId=${subjectstudent.subjectId}"> ${subjectstudent.subjectName} </A>
            </TD>
            <c:choose>
               <c:when test = "${subjectstudent.studentId == 0}">
                  <TD align = "center"> --- </TD>
                  <TD align = "center"> --- </TD>
                  <TD align = "center"> --- </TD>
                  <TD align = "center"> --- </TD>
                  <TD align = "center"> --- </TD>
               </c:when>
               <c:otherwise>
                  <TD align = "center"> ${subjectstudent.studentId} </TD>
                  <TD>&nbsp;
                    <A href="${contextPath}/get_subject_students.do?subjectId=${subjectstudent.subjectId}"> ${subjectstudent.studentName} </A>
                  </TD>
                  <TD align = "center">
                     <c:choose>
                        <c:when test = "${subjectstudent.studentSex == 0}"> 女 </c:when>
                        <c:otherwise> 男 </c:otherwise>
                     </c:choose>
                  </TD>
                  <TD align = "center">&nbsp;${subjectstudent.studentAge}</TD>
                  <TD align = "center">
                    <A href="${contextPath}/del_subjectstudent/${subjectstudent.id}.do"> 取消 </A>
                  </TD>
               </c:otherwise>
            </c:choose>
          </TR>
    </c:forEach>
          <TR>
            <TD colspan = "9">
              &nbsp;共有 <FONT size = "5" face = "新宋体" color = "Red"> ${number_of_subjectsstudents} </FONT> 条记录
            </TD>
          </TR>
  </c:otherwise>
</c:choose>
</TABLE>
