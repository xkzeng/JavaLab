<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 获取上下文路径:第一种方法"c:set+EL表达式" -->
<c:set var = "contextPath" value = "${pageContext.request.contextPath}" />

<!DOCTYPE HTML>
<HTML>
   <HEAD>
      <META http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
      <LINK type="text/css" rel="stylesheet" href="css/mystyle.css"/>
      <TITLE>School管理,集成SSM环境(Spring+SpringMVC+MyBatis++MySQL+JSP)</TITLE>
   </HEAD>
   <BODY>
      <jsp:include page = "/WEB-INF/view/layout/school_header.jsp" flush = "true"/>
      <BR>
		  
      <DIV>
         <!-- jsp:include page = "layout/classes/classes_mgr.jsp" flush = "true"/ -->
         <TABLE width = "90%" border="1" cellspacing="1" cellpadding="1" align = "center">
            <TR bgcolor="#DEDEDE">
               <TH colspan = "2">
                 <FONT size = "5" face = "新宋体"> 学校管理目录 </FONT>
              </TH>
            </TR>
            <TR>
               <TD>
                  &nbsp; <A href="${contextPath}/subjects.do"> <FONT size = "4" face = "新宋体"> 课程 </FONT> </A>
               </TD>
               <TD>
                  &nbsp; <A href="${contextPath}/classess.do"> <FONT size = "4" face = "新宋体"> 班级 </FONT> </A>
               </TD>
            </TR>
            <TR>
               <TD>
                  &nbsp; <A href="${contextPath}/teachers.do"> <FONT size = "4" face = "新宋体"> 教师 </FONT> </A>
               </TD>
               <TD>
                  &nbsp; <A href="${contextPath}/books.do"> <FONT size = "4" face = "新宋体"> 图书 </FONT> </A>
               </TD>
            </TR>
            <TR>
               <TD>
                  &nbsp;<A href="${contextPath}/students.do"> <FONT size = "4" face = "新宋体"> 学生 </FONT> </A>
               </TD>
               <TD>
                  &nbsp; <A href="${contextPath}/students_subjects.do"> <FONT size = "4" face = "新宋体"> 学生选课 </FONT> </A>
               </TD>
            </TR>
            
            <TR>
               <TD>
                  &nbsp;<A href="${contextPath}/subjects_students.do"> <FONT size = "4" face = "新宋体"> 课程选修 </FONT> </A>
               </TD>
               <TD>
                  &nbsp; <A href="${contextPath}/statistics.do"> <FONT size = "4" face = "新宋体"> 课程统计 </FONT> </A>
               </TD>
            </TR>
         </TABLE>
      </DIV>
      <BR>
      
      <jsp:include page = "/WEB-INF/view/layout/school_footer.jsp" flush = "true"/>
   </BODY>
</HTML>