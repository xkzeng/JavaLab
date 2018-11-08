
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler; //部署运行Web APP的方式1;
import org.eclipse.jetty.server.handler.ContextHandler;  //部署运行Web APP的方式2;
import org.eclipse.jetty.servlet.ServletContextHandler;  //ServletContextHandler继承自ContextHandler;
import org.eclipse.jetty.webapp.WebAppContext;           //WebAppContext继承自ServletContextHandler;

import org.eclipse.jetty.server.Connector;               //连接器;
import org.eclipse.jetty.server.ServerConnector;         //服务端连接器;

public class JettyServer
{
	public static void main(String[] args) throws Exception
	{
		Server service = new Server();
		
		//设置在JVM退出时关闭Jetty的钩子;
		service.setStopAtShutdown(true);
		
		//绑定多个端口:
		ServerConnector connector1 = new ServerConnector(service);
		connector1.setPort(12345);
		
		ServerConnector connector2 = new ServerConnector(service);
		connector2.setPort(54321);
		
		//service.setConnectors(new Connector[]{connector1, connector2}); //这个方式也可以;
		service.addConnector(connector1);
		service.addConnector(connector2);
		
		//设置请求处理器;
		service.setHandler(new MyHandler());
		
		//启动服务;
		service.start();
		System.out.println("服务已启动");
		
		//让服务等待;
		service.join();
	}
}

class MyHandler extends AbstractHandler
{
	/*
	 * target: 就是UTL中去掉主机地址和参数之后剩余的部分;例如:"http://localhost:54321/api/v1/get_user_info?name=zhangsan"中的"/api/v1/get_user_info";
	 *         其中,target指的就是"/api/v1/get_user_info";
	 */
	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		System.out.println("target = " + target + ", from port " + baseRequest.getRemotePort() + " to " + baseRequest.getServerPort());
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);
		
		PrintWriter out = response.getWriter();
		out.print("<H3>Hello,Jetty</H3>");
		out.print("<H3>getPathInfo: " + request.getPathInfo() + "</H3>");
		out.print("<H3>getContextPath: " + baseRequest.getContextPath()+ "</H3>");
		out.print("<H3>getRequestURI: " + request.getRequestURI()+ "</H3>");
		out.print("<H3>getServletPath: " + request.getServletPath()+ "</H3>");
		out.print("<H3>getServletContext: " + request.getServletContext()+ "</H3>");
		
		String yourName = request.getParameter("name");
		if(yourName != null)
		{
			out.println("<H3>Hello," + yourName + "</H3>");
		}
		out.flush();
		out.close();
	}
}
