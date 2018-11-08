package com.learn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.StringBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletXML
 */
public final class ServletXML extends HttpServlet
{
	private static final long serialVersionUID = 6435164108632914433L;
	
	protected String getFileContent(String filePath)
	{
		String fileContent = null;
		StringBuilder sb = new StringBuilder();
		
    try
    {
      String strLine = null;
      BufferedReader br = new BufferedReader(new FileReader(filePath));
      while((strLine = br.readLine()) != null)
      {
      	sb.append(strLine + "@@@");
      }
      br.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
      sb.append(">>>>> " + e.getLocalizedMessage());
    }
    
    fileContent = sb.toString();
		return fileContent;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		/* 设置响应内容的类型 */
    response.setContentType("text/html;charset=utf-8");
    
		// TODO Auto-generated method stub
		response.getWriter().append("<H1>Served at: ").append(request.getContextPath() + "</H1>");

		/* 输出信息到页面中 */
    PrintWriter out = response.getWriter();
    out.print("<H1>使用XML指定URL到Servlet的映射关系</H1>");
    
    String fileName = "web.xml";
    out.print("<H1>" + fileName + "</H1>");
    String filePath = ServletXML.class.getClassLoader().getResource(fileName).getPath();
    String fileContent = this.getFileContent(filePath);
    fileContent = fileContent.replace("<", "&lt;").replace(">", "&gt;").replace("@@@", "<BR>");
    out.print("<font size=4 face='新宋体'>" + fileContent + "</font><hr>");
    
    fileName = "ServletXML.java";
    out.print("<H1>" + fileName + "</H1>");
    filePath = Thread.currentThread().getContextClassLoader().getResource(fileName).getPath();
    fileContent = this.getFileContent(filePath);
    fileContent = fileContent.replace("<", "&lt;").replace(">", "&gt;").replace("@@@", "<BR>");
    out.print("<font size=4 face='新宋体'>" + fileContent + "</font>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doHead(HttpServletRequest, HttpServletResponse)
	 */
	protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doOptions(HttpServletRequest, HttpServletResponse)
	 */
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doTrace(HttpServletRequest, HttpServletResponse)
	 */
	protected void doTrace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}
}
