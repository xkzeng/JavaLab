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
    <TH>学号</TH>
    <TH>姓名</TH>
    <TH>性别</TH>
    <TH>年龄</TH>
    <TH>选课序号</TH>
    <TH>课程编号</TH>
    <TH>课程名称</TH>
    <TH>操作</TH>
  </TR>
<c:choose>
  <c:when test = "${number_of_students_subjects == 0}">
  <TR>
    <TD colspan = "9">
      &nbsp; <FONT size = "4" face = "新宋体" color = "Red"> 没有学生选课呢,赶紧做好宣传! </FONT>
    </TD>
  </TR>
  </c:when>
  <c:otherwise>
     <c:set var = "number_of_students_selected_subject" value = "0" />
     <c:forEach items = "${students_subjectsList}" var = "student_subject" varStatus = "row">
           <c:choose>
              <c:when test = "${empty student_subject.subjects}">
                 <c:choose>
                    <c:when test = "${row.count % 2 == 0}">
                       <TR bgcolor = "#D1EEEE">
                    </c:when>
                    <c:otherwise>
                       <TR>
                    </c:otherwise>
                 </c:choose>
                          <!-- 一名学生 -->
                          <TD>&nbsp;${row.count}</TD>
                          <TD align = "center"> ${student_subject.id}  </TD>
                          <TD>&nbsp;
                             <A href="${contextPath}/get_student_subjects.do?studentId=${student_subject.id}"> ${student_subject.name} </A>
                          </TD>
                          <TD align = "center">
                             <c:choose>
                                <c:when test = "${student_subject.sex == 0}"> 女 </c:when>
                                <c:otherwise> 男 </c:otherwise>
                             </c:choose>
                          </TD>
                          <TD>&nbsp;${student_subject.age}</TD>
                          <TD align = "center"> --- </TD>
                          <TD align = "center"> --- </TD>
                          <TD align = "center"> --- </TD>
                          <TD align = "center"> --- </TD>
                       </TR>
              </c:when>
              
              <c:otherwise>
                 <c:set var = "number_of_students_selected_subject" value = "${number_of_students_selected_subject + 1}" />
                 <c:set var = "rowSpanNumber" value = "${fn:length(student_subject.subjects)}" />
                 <!-- 该学生选修的课程 -->  
                 <c:forEach items = "${student_subject.subjects}" var = "subject" varStatus = "row2">
                    <c:choose>
                       <c:when test = "${row.count % 2 == 0}">
                          <TR bgcolor = "#D1EEEE">
                       </c:when>
                       <c:otherwise>
                          <TR>
                       </c:otherwise>
                    </c:choose>
                    <c:if test = "${row2.count == 1}">
                              <!-- 一名学生 -->
                              <TD rowspan = "${rowSpanNumber}">&nbsp;${row.count}</TD>
                              <TD rowspan = "${rowSpanNumber}" align = "center"> ${student_subject.id}  </TD>
                              <TD rowspan = "${rowSpanNumber}">&nbsp;
                                 <A href="${contextPath}/get_student_subjects.do?studentId=${student_subject.id}"> ${student_subject.name} </A>
                              </TD>
                              <TD rowspan = "${rowSpanNumber}">
                                 <c:choose>
                                    <c:when test = "${student_subject.sex == 0}"> 女 </c:when>
                                    <c:otherwise> 男 </c:otherwise>
                                 </c:choose>
                              </TD>
                              <TD rowspan = "${rowSpanNumber}">&nbsp;${student_subject.age}</TD>
                    </c:if>
                              <TD> &nbsp;${row2.count} </TD>
                              <TD align = "center">${subject.id}</TD>
                              <TD>&nbsp;
                                 <A href="${contextPath}/get_subject_students.do?subjectId=${subject.id}"> ${subject.name} </A>
                              </TD>
                              <TD align = "center">
                                  <A href="${contextPath}/del_student_subject.do?studentId=${student_subject.id}&subjectId=${subject.id}"> 取消 </A>
                              </TD>
                          </TR>
                 </c:forEach>
              </c:otherwise>
           </c:choose>
     </c:forEach>
          <TR>
            <TD colspan = "9">
              &nbsp;总共有<FONT size = "9" face = "新宋体" color = "Red"> ${number_of_students_subjects}</FONT>名学生,其中,
              只有<FONT size = "9" face = "新宋体" color = "Red">${number_of_students_selected_subject}</FONT>名同学选修了课程,
              还有<FONT size = "9" face = "新宋体" color = "Red">${number_of_students_subjects - number_of_students_selected_subject}</FONT>名同学没有选课
            </TD>
          </TR>
  </c:otherwise>
</c:choose>
</TABLE>
