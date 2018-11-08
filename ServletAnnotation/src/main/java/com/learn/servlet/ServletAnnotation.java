package com.learn.servlet;

import java.lang.ClassLoader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class ServletAnnotation
 */
@WebServlet(urlPatterns = "/ServletAnnotation", loadOnStartup = 1, name = "ServletAnnotation", displayName = "ServletAnnotation")
public final class ServletAnnotation extends HttpServlet
{
	private static final long serialVersionUID = 6435164108632914432L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAnnotation()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
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
		// TODO Auto-generated method stub
		/* ������Ӧ���ݵ����� */
    response.setContentType("text/html;charset=utf-8");
    
		response.getWriter().append("<H1>Served at: ").append(request.getContextPath() + "</H1>");

		/* �����Ϣ��ҳ���� */
    PrintWriter out = response.getWriter();
    out.print("<H1>ʹ��ע��javax.servlet.annotation.WebServletָ��URL��Servlet��ӳ���ϵ</H1>");
    
    String fileName = "web.xml";
    out.print("<H1>" + fileName + "</H1>");
    String filePath = this.getClass().getClassLoader().getResource(fileName).getPath();
    String fileContent = this.getFileContent(filePath);
    fileContent = fileContent.replace("<", "&lt;").replace(">", "&gt;").replace("@@@", "<BR>");
    out.print("<font size=4 face='������'>" + fileContent + "</font><hr>");
    
    fileName = "ServletAnnotation.java";
    out.print("<H1>" + fileName + "</H1>");
    filePath = Thread.currentThread().getContextClassLoader().getResource(fileName).getPath();
    fileContent = this.getFileContent(filePath);
    fileContent = fileContent.replace("<", "&lt;").replace(">", "&gt;").replace("@@@", "<BR>");
    out.print("<font size=4 face='������'>" + fileContent + "</font>");
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
