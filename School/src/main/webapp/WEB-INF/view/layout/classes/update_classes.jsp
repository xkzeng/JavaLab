<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE HTML>
<HTML>
   <HEAD>
      <META http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
      <LINK type="text/css" rel="stylesheet" href="css/mystyle.css"/>
      <TITLE>School-班级管理,集成SSM环境(Spring+SpringMVC+MyBatis++MySQL+JSP)</TITLE>
   </HEAD>
   <BODY>
      <jsp:include page = "../school_header.jsp" flush = "true"/>
      <BR>
		  
      <DIV align = "center">
         <form id = "updateClasses" name = "updateClasses" method = "POST" action = "${contextPath}/update_classes.do">
            <TABLE width = "90%" border="1" cellspacing="1" cellpadding="1" align = "center">
               <TR>
                 <TD align = "center">
                   <jsp:include page = "classes_edit_layout.jsp" flush = "true"/>
                 </TD>
               </TR>
               <TR>
                 <TD align = "center">
                   <jsp:include page = "../update_buttons.jsp" flush = "true"/>
                 </TD>
               </TR>
            </TABLE>
         </form>
         <BR>
         <jsp:include page = "student_list.jsp" flush = "true"/>
         
         <BR>
         <jsp:include page = "student_list2.jsp" flush = "true"/>
      </DIV>
      <BR>
      
      <jsp:include page = "../school_footer.jsp" flush = "true"/>
   </BODY>
</HTML>