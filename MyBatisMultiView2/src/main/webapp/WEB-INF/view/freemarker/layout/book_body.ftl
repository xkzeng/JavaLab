  <DIV align = "center">
    <TABLE width = 70% border="1" cellspacing="1" cellpadding="1">
      <TR>
        <TD>
          &nbsp;共有 <FONT size = "5" face = "新宋体" color = "Red"> ${number_of_books} </FONT>本图书
          &nbsp;这个页面是使用FreeMarker渲染出来的;
        </TD>
      </TR>
    </TABLE>
  </DIV>
  <BR>

  <DIV align = "center">
    <TABLE width = 70% border="1" cellspacing="1" cellpadding="1">
      <TR align="middle" bgcolor="#DEDEDE">
        <TH>序号</TH>
        <TH>编号</TH>
        <TH>名称</TH>
        <TH>作者</TH>
      </TR>
      
      <#list bookList as book>
        <#assign lineNumber = book_index+1>
        <#if lineNumber % 2 == 0>
      <TR bgcolor = "#D1EEEE">
        <#else>
      <TR>
        </#if>
        <TD>&nbsp;${lineNumber}</TD>
        <TD>&nbsp;${book.bookId}  </TD>
        <TD>&nbsp;${book.bookName}</TD>
        <TD>&nbsp;${book.author}  </TD>
      </TR>
      </#list>
    </TABLE>
  </DIV>