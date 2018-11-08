<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<HTML>
	<HEAD>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <LINK type="text/css" rel="stylesheet" href="css/mystyle.css"/>
		<TITLE>MyBatis based on XML</TITLE>
	</HEAD>
	
	<BODY>
	  <DIV>
			 <TABLE width = 40% border="1" cellspacing="1" cellpadding="1" align = "center">
				  <TR>
					   <TD align = "right">
					      <FONT size = "5" face = "新宋体" color = "Red">
					         <A href="javascript: window.history.back();"> 返回 </A>
					      </FONT>
					   </TD>
				  </TR>
			 </TABLE>
		</DIV>
        <BR>
        <DIV>
			 <TABLE width = 40% border="1" cellspacing="1" cellpadding="1" align = "center">
				  <TR>
					   <TD>
					      &nbsp;共有 <FONT size = "5" face = "新宋体" color = "Red"> ${number_of_books} </FONT>本图书
					   </TD>
				  </TR>
			 </TABLE>
	    </DIV>
        <BR>
		<DIV>
			 <TABLE width = 40% border="1" cellspacing="1" cellpadding="1" align = "center">
				  <TR align="middle" bgcolor="#DEDEDE">
					   <TH>编号</TH>
					   <TH>名称</TH>
					   <TH>作者</TH>
				  </TR>
				  
				  <c:forEach items="${bookList}" var="book">
				  <TR>
					   <TD>&nbsp;${book.bookId}  </TD>
					   <TD>&nbsp;${book.bookName}</TD>
					   <TD>&nbsp;${book.author}  </TD>
				  </TR>
				  </c:forEach>
			 </TABLE>
		</DIV>
        <BR>
		<DIV>
			 <TABLE width = 40% border="1" cellspacing="1" cellpadding="1" align = "center">
				  <TR>
					   <TD align = "right">
					      <FONT size = "5" face = "新宋体" color = "Red">
					         <A href="javascript: window.history.back();"> 返回 </A>
					      </FONT>
					   </TD>
				  </TR>
			 </TABLE>
		</DIV>
	</BODY>
</HTML>
