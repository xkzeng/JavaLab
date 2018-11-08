
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler; //部署运行Web APP的方式1;
import org.eclipse.jetty.server.handler.ContextHandler;  //部署运行Web APP的方式2;
import org.eclipse.jetty.server.handler.ContextHandlerCollection; //上下文处理器对象列表,用于部署多个WebApp;
import org.eclipse.jetty.servlet.ServletContextHandler;  //ServletContextHandler继承自ContextHandler;
import org.eclipse.jetty.webapp.WebAppContext;           //WebAppContext继承自ServletContextHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import org.eclipse.jetty.server.Connector;               //连接器;
import org.eclipse.jetty.server.ServerConnector;         //服务端连接器;

public class JettyServer2
{
	/* 通过WebAppContext实现资源处理器 */
	public static void test1() throws Exception
	{
		//实例化Handler;
		WebAppContext webApp = new WebAppContext();
		webApp.setResourceBase("E:\\workspace");
		
		//实例化Jetty服务实例;
		Server service = new Server(8080);
		
		//设置在JVM退出时关闭Jetty的钩子;
		service.setStopAtShutdown(true);
		
		//设置请求处理器;
		service.setHandler(webApp);
		
		//启动服务;
		service.start();
		System.out.println("服务已启动");
		
		//让服务等待;
		service.join();
	}
	
	/* 通过ResourceHandler实现资源处理器功能 */
	public static void test2() throws Exception
	{
		//初始化资源处理器:
		ResourceHandler resourceHandler = new ResourceHandler();
		resourceHandler.setResourceBase("E:\\workspace");
		resourceHandler.setDirectoriesListed(true); //可显示目录结构,类似FTP;也可打开文本文件进行查看;
		
		//实例化Jetty服务实例;
		Server service = new Server(8080);
		
		//设置在JVM退出时关闭Jetty的钩子;
		service.setStopAtShutdown(true); 
		
		//设置请求处理器;
		service.setHandler(resourceHandler);
		
		//启动服务;
		service.start();
		System.out.println("服务已启动");
		
		//让服务等待;
		service.join();
	}
	
	/* 运行内部编写的Servlet */
	public static void test3() throws Exception
	{
		//实例化Handler;
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		
		//http://localhost:8083/hello
		context.addServlet(new ServletHolder(new DemoServlet()), "/hello");
		
		//http://localhost:8083/hello/me
		context.addServlet(new ServletHolder(new DemoServlet("Tsange")), "/hello/me");
		
		//实例化Jetty服务实例;
        Server service = new Server(8080);
		
		//设置在JVM退出时关闭Jetty的钩子;
		service.setStopAtShutdown(true);
		
		//设置请求处理器;
		service.setHandler(context);
		
		//启动服务;
		service.start();
		System.out.println("服务已启动");
		
		//让服务等待;
		service.join();
	}
	
	/* 运行标准的WebApp项目 */
	public static void test4() throws Exception
	{
		//实例化上下文Handler对象;
		WebAppContext context = new WebAppContext();
		
		//设置虚拟路径的根目录,相当于server.xml文件中<Context>标签的path属性值;
		context.setContextPath("/app1");
		
		//设置Web应用程序文件的物理路径,相当于server.xml文件中<Context>标签的docBase属性值;
		context.setResourceBase("E:\\jetty\\apps\\app1");
		context.setDescriptor(".\\WEB-INF\\web.xml");
		context.setDisplayName("Jetty Server");
		context.setParentLoaderPriority(true);
		//context.setConfigurationDiscovered(true);
		
		//实例化Jetty服务实例;
        Server service = new Server(8080);
		
		//设置在JVM退出时关闭Jetty的钩子;
		service.setStopAtShutdown(true);
		
		//设置请求处理器;
		service.setHandler(context);
		
		//启动服务;
		service.start();
		System.out.println("服务已启动");
		
		//让服务等待;
		service.join();
	}
	
	/* 运行多个标准的WebApp项目 */
	public static void test5() throws Exception
	{
		//实例化第一个上下文Handler对象;
		WebAppContext context1 = new WebAppContext();
		context1.setContextPath("/app1");
		context1.setResourceBase("E:\\jetty\\apps\\app1");
		context1.setDescriptor(".\\WEB-INF\\web.xml");
		context1.setDisplayName("Jetty Server1");
		context1.setParentLoaderPriority(true);
		
		//实例化第一个上下文Handler对象;
		WebAppContext context2 = new WebAppContext();
		context2.setContextPath("/app2");
		context2.setResourceBase("E:\\jetty\\apps\\app2");
		context2.setDescriptor(".\\WEB-INF\\web.xml");
		context2.setDisplayName("Jetty Server2");
		context2.setParentLoaderPriority(true);
		
		//通过上下文列表对象来添加多个上下文对象,即:多个WebApp;
		ContextHandlerCollection contextList = new ContextHandlerCollection(); //<<<关键点
		contextList.addHandler(context1); //WebApp1
		contextList.addHandler(context2); //WebApp2
		
		//实例化Jetty服务实例;
        Server service = new Server(8080);
		
		//设置在JVM退出时关闭Jetty的钩子;
		service.setStopAtShutdown(true);
		
		//设置请求处理器列表;
		service.setHandler(contextList); //<<<关键点
		
		//启动服务;
		service.start();
		System.out.println("服务已启动");
		
		//让服务等待;
		service.join();
	}
	
	public static void main(String[] args) throws Exception
	{
		JettyServer2.test3();
	}
}

class DemoServlet extends HttpServlet
{
	private String message;
	
	public DemoServlet()
	{
		super();
		this.message = "Demo Servlet for default";
	}
	
	public DemoServlet(String message)
	{
		super();
		this.message = "Demo Servlet: " + message;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Add your code here;
		request.setCharacterEncoding("UTF-8");
		
		/* 设置响应内容的类型 */
		response.setContentType("text/html;charset=UTF-8");
		
		/* 获取输出流对象 */
		PrintWriter out = response.getWriter();
		
		/* 业务逻辑代码 */
		
		out.print("<H1>" + this.message + "</H1>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Add your code here;
		this.doGet(request, response);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Add your code here;
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Add your code here;
	}
}