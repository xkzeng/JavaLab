<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!-- 获取上下文路径:第一种方法"c:set+EL表达式" -->
<c:set var = "contextPath" value = "${pageContext.request.contextPath}"/>

<!DOCTYPE HTML>
<TABLE width = "100%" border="1" cellspacing="1" cellpadding="1">
  <TR bgcolor="#DEDEDE">
    <TH>序号</TH>
    <TH>课程编号</TH>
    <TH>课程名称</TH>
    <TH>序号</TH>
    <TH>学号</TH>
    <TH>姓名</TH>
    <TH>性别</TH>
    <TH>年龄</TH>
    <TH>操作</TH>
  </TR>
<c:choose>
  <c:when test = "${number_of_subjects_students == 0}">
  <TR>
    <TD colspan = "9">
      &nbsp; <FONT size = "4" face = "新宋体" color = "Red"> 没有学生选课呢,赶紧做好宣传! </FONT>
    </TD>
  </TR>
  </c:when>
  <c:otherwise>
     <c:set var = "number_of_subjects_selected" value = "0" />
     <c:forEach items = "${subjects_studentsList}" var = "subject" varStatus = "row">
           <c:choose>
              <c:when test = "${empty subject.students}">
                <c:set var = "rowSpanNumber" value = "0" />
              </c:when>
              <c:otherwise>
                <c:set var = "rowSpanNumber" value = "${fn:length(subject.students)}" />
              </c:otherwise>
           </c:choose>
           
           <c:choose>
              <c:when test = "${rowSpanNumber == 0}">
                 <c:choose>
                    <c:when test = "${row.count % 2 == 0}">
                       <TR bgcolor = "#D1EEEE">
                    </c:when>
                    <c:otherwise>
                       <TR>
                    </c:otherwise>
                 </c:choose>
                          <TD rowspan = "${rowSpanNumber}"> &nbsp;${row.count} </TD>
                          <TD rowspan = "${rowSpanNumber}" align = "center"> ${subject.id}</TD>
                          <TD rowspan = "${rowSpanNumber}">&nbsp;
                             <A href="${contextPath}/get_subject_students.do?subjectId=${subject.id}"> ${subject.name} </A>
                          </TD>
                          <TD align = "center"> --- </TD>
                          <TD align = "center"> --- </TD>
                          <TD align = "center"> --- </TD>
                          <TD align = "center"> --- </TD>
                          <TD align = "center"> --- </TD>
                          <TD align = "center"> --- </TD>
                       </TR>
              </c:when>
              
              <c:otherwise>
                 <c:set var = "number_of_subjects_selected" value = "${number_of_subjects_selected + 1}" />
                 <!-- 选修该课程的学生 -->  
                 <c:forEach items = "${subject.students}" var = "student" varStatus = "row2">
                 <c:choose>
                    <c:when test = "${row.count % 2 == 0}">
                       <TR bgcolor = "#D1EEEE">
                    </c:when>
                    <c:otherwise>
                       <TR>
                    </c:otherwise>
                 </c:choose>
                 <c:if test = "${row2.count == 1}">
                           <TD rowspan = "${rowSpanNumber}"> &nbsp;${row.count} </TD>
                           <TD rowspan = "${rowSpanNumber}" align = "center">${subject.id}</TD>
                           <TD rowspan = "${rowSpanNumber}">&nbsp;
                              <A href="${contextPath}/get_subject_students.do?subjectId=${subject.id}"> ${subject.name} </A>
                           </TD>
                 </c:if>
                           <!-- 一名学生 -->
                           <TD >&nbsp;${row2.count}</TD>
                           <TD align = "center"> ${student.id} </TD>
                           <TD>&nbsp;
                              <A href="${contextPath}/get_subject_students.do?subjectId=${subject_student.id}"> ${student.name} </A>
                           </TD>
                           <TD align = "center">
                              <c:choose>
                                 <c:when test = "${student.sex == 0}"> 女 </c:when>
                                 <c:otherwise> 男 </c:otherwise>
                              </c:choose>
                           </TD>
                           <TD align = "center">&nbsp;${student.age}</TD>
                           <TD align = "center">
                               <A href="${contextPath}/del_subject_student.do?subjectId=${subject.id}&studentId=${student.id}"> 取消 </A>
                           </TD>
                       </TR>
                 </c:forEach>
              </c:otherwise>
           </c:choose>
     </c:forEach>
          <TR>
            <TD colspan = "9">
              &nbsp;共有<FONT size = "8" face = "新宋体" color = "Red">${number_of_subjects_students}</FONT>门课程,其中,
              有<FONT size = "8" face = "新宋体" color = "Red">${number_of_subjects_selected}</FONT>门课程被选修过,还有<FONT size = "8" face = "新宋体" color = "Red">${number_of_subjects_students - number_of_subjects_selected}</FONT>门课程没有被选修过
            </TD>
          </TR>
  </c:otherwise>
</c:choose>
</TABLE>
