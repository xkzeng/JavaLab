<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<HTML>
   <HEAD>
      <META http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
      <LINK type="text/css" rel="stylesheet" href="css/mystyle.css"/>
      <TITLE>MyBatis based on FreeMarker</TITLE>
   </HEAD>
   <BODY>
      <jsp:include page = "layout/school_header.jsp" flush = "true"/>
		  <BR>
		  
      <DIV> <jsp:include page = "layout/tips/message.jsp" flush = "true"/> </DIV>
      <BR>
      
      <jsp:include page = "layout/school_footer.jsp" flush = "true"/>
   </BODY>
</HTML>