<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>

<!DOCTYPE HTML>
<TABLE width = "90%" align = "center" valign = "middle">
  <TR bgcolor="#DEDEDE">
    <TH align = "center"> <FONT size = "5" face = "新宋体">课程选修热度</FONT></TH>
  </TR>
  <TR>
    <TD align = "center" valign = "top">
      <jsp:include page = "subject_selectcount.jsp" flush = "true"/>
    </TD>
  </TR>
</TABLE>
<BR>
