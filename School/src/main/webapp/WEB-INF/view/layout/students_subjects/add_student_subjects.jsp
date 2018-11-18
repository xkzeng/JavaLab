<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- 获取上下文路径:第一种方法"c:set+EL表达式" -->
<c:set var = "contextPath" value = "${pageContext.request.contextPath}"/>

<!DOCTYPE HTML>
<form id = "addStudentSubjects" name = "addStudentSubjects" method = "POST" action = "${contextPath}/add_student_subjects.do">
<TABLE width = "100%" border="1" cellspacing="1" cellpadding="1" align = "center">
  <TR>
    <TD>
      <INPUT type = "hidden" id = "studentId" name = "studentId" value = "${student.id}"/>
      &nbsp; 学号:${student.id} &nbsp;&nbsp;姓名:${student.name}
       &nbsp;&nbsp;性别:
       <c:choose>
          <c:when test = "${student.sex == 0}"> 女 </c:when>
          <c:otherwise> 男 </c:otherwise>
       </c:choose>
       &nbsp;&nbsp;年龄:${student.age}岁
       &nbsp;&nbsp;班级:${student.classes.name}
       &nbsp;&nbsp;班主任:${student.classes.masterTeacher.name}
    </TD>
  </TR>
  <TR>
    <TD>
      <jsp:include page = "../add_buttons.jsp" flush = "true"/>
    </TD>
  </TR>
  <TR>
    <TD align = "center" valign = "top">
       <TABLE width = "100%" border="1" cellspacing="1" cellpadding="1" align = "center">
         <TR bgcolor="#DEDEDE">
           <TH>多选</TH>
           <TH>序号</TH>
           <TH>编号</TH>
           <TH>名称</TH>
         </TR>
         <c:choose>
            <c:when test = "${number_of_subjects == 0}">
            <TR>
              <TD colspan = "4">
                &nbsp; <FONT size = "4" face = "新宋体" color = "Red"> 学校初建,还没有设计好课程,太懒了! </FONT>
              </TD>
            </TR>
            </c:when>
            <c:otherwise>
              <c:forEach items = "${subjectsList}" var = "subject" varStatus = "row">
                <c:choose>
                  <c:when test = "${row.count % 2 == 0}">
                    <TR bgcolor = "#D1EEEE">
                  </c:when>
                  <c:otherwise>
                    <TR>
                  </c:otherwise>
                </c:choose>
                      <TD>&nbsp; <INPUT type = "checkbox" id = "subjectIds" name = "subjectIds" value = "${subject.id}"/> </TD>
                      <TD>&nbsp;${row.count}</TD>
                      <TD align = "center"> ${subject.id}  </TD>
                      <TD>&nbsp;
                          <A href="${contextPath}/get_subject_students.do?subjectId=${subject.id}"> ${subject.name} </A>
                      </TD>
                    </TR>
              </c:forEach>
                    <TR>
                      <TD colspan = "4">
                        &nbsp;共开设有 <FONT size = "5" face = "新宋体" color = "Red"> ${number_of_subjects} </FONT>门课程
                      </TD>
                    </TR>
            </c:otherwise>
         </c:choose>
       </TABLE>
    </TD>
  </TR>
</TABLE>
</form>