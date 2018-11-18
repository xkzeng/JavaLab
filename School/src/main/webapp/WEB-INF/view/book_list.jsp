<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE HTML>
<HTML>
   <HEAD>
      <META http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
      <LINK type="text/css" rel="stylesheet" href="css/mystyle.css"/>
      <TITLE>School管理,集成SSM环境(Spring+SpringMVC+MyBatis++MySQL+JSP)</TITLE>
   </HEAD>
   <BODY>
      <jsp:include page = "layout/school_header.jsp" flush = "true"/>
      <BR>
		  
      <DIV> <jsp:include page = "layout/book/book_mgr.jsp" flush = "true"/> </DIV>
      <BR>
      
      <jsp:include page = "layout/school_footer.jsp" flush = "true"/>
   </BODY>
</HTML>