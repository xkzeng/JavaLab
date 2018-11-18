<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- 获取上下文路径:第一种方法"c:set+EL表达式" -->
<c:set var = "contextPath" value = "${pageContext.request.contextPath}"/>

<!DOCTYPE HTML>
<form id = "addStudentsSubjects" name = "addStudentsSubjects" method = "POST" action = "${contextPath}/add_students_subjects.do">
<TABLE width = "100%" border="1" cellspacing="1" cellpadding="1" align = "center">
  <TR bgcolor="#DEDEDE">
    <TH> <FONT size = "5" face = "新宋体"> 学生列表 </FONT> </TH>
    <TH> <FONT size = "5" face = "新宋体"> 课程列表 </FONT> </TH>
  </TR>
  <TR>
    <TD align = "center" valign = "top">
       <TABLE width = "100%" border="1" cellspacing="1" cellpadding="1" align = "center">
         <TR bgcolor="#DEDEDE">
           <TH>单选</TH>
           <TH>多选</TH>
           <TH>序号</TH>
           <TH>学号</TH>
           <TH>姓名</TH>
           <TH>性别</TH>
           <TH>年龄</TH>
           <TH>班级</TH>
           <TH>班主任</TH>
         </TR>
         <c:choose>
            <c:when test = "${number_of_students == 0}">
            <TR>
              <TD colspan = "9">
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
                    <TD>&nbsp; <INPUT type = "radio" id = "studentId" name = "studentId" value = "${student.id}"/> </TD>
                    <TD>&nbsp; <INPUT type = "checkbox" id = "studentIds" name = "studentIds" value = "${student.id}"/> </TD>
                    <TD>&nbsp;${row.count}</TD>
                    <TD align = "center"> ${student.id}  </TD>
                    <TD>&nbsp;
                       <A href="${contextPath}/get_student_subjects.do?studentId=${student.id}"> ${student.name} </A>
                    </TD>
                    <TD align = "center">
                       <c:choose>
                          <c:when test = "${student.sex == 0}"> 女 </c:when>
                          <c:otherwise> 男 </c:otherwise>
                       </c:choose>
                    </TD>
                    <TD>&nbsp;${student.age}</TD>
                    <TD>&nbsp;${student.classes.name}</TD>
                    <TD>&nbsp;${student.classes.masterTeacher.name}</TD>
                  </TR>
              </c:forEach>
                  <TR>
                    <TD colspan = "9">
                      &nbsp;共招收 <FONT size = "5" face = "新宋体" color = "Red"> ${number_of_students} </FONT> 名学生
                    </TD>
                  </TR>
            </c:otherwise>
         </c:choose>
       </TABLE>
    </TD>
    <TD align = "center" valign = "top">
       <TABLE width = "100%" border="1" cellspacing="1" cellpadding="1" align = "center">
         <TR bgcolor="#DEDEDE">
           <TH>单选</TH>
           <TH>多选</TH>
           <TH>序号</TH>
           <TH>编号</TH>
           <TH>名称</TH>
         </TR>
         <c:choose>
            <c:when test = "${number_of_subjects == 0}">
            <TR>
              <TD colspan = "5">
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
                      <TD>&nbsp; <INPUT type = "radio" id = "subjectId" name = "subjectId" value = "${subject.id}"/> </TD>
                      <TD>&nbsp; <INPUT type = "checkbox" id = "subjectIds" name = "subjectIds" value = "${subject.id}"/> </TD>
                      <TD>&nbsp;${row.count}</TD>
                      <TD align = "center"> ${subject.id} </TD>
                      <TD>&nbsp;
                          <A href="${contextPath}/get_subject_students.do?subjectId=${subject.id}"> ${subject.name} </A>
                      </TD>
                    </TR>
              </c:forEach>
                    <TR>
                      <TD colspan = "5">
                        &nbsp;共开设有 <FONT size = "5" face = "新宋体" color = "Red"> ${number_of_subjects} </FONT>门课程
                      </TD>
                    </TR>
            </c:otherwise>
         </c:choose>
       </TABLE>
    </TD>
  </TR>
  <TR>
    <TD align = "center" colspan = "2">
      <jsp:include page = "../add_buttons.jsp" flush = "true"/>
    </TD>
  </TR>
</TABLE>
</form>