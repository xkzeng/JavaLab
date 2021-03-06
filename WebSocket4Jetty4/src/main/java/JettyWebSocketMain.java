import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
//import org.eclipse.jetty.server.handler.ContextHandlerCollection;
//import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.ServerConnector;

import com.learn.websocket.jetty.JettyWebSocketServlet;
import com.wrapper.Print;

public class JettyWebSocketMain
{
	public JettyWebSocketMain()
	{
		// TODO Auto-generated constructor stub
	}

	public static void test1()
	{
		// 创建Jetty服务器对象:
		Server server = new Server(9000);
		server.setStopAtShutdown(true);

		// 创建处理WebSocket连接的Handler:
		JettyWebSocketServlet webSocketServlet = new JettyWebSocketServlet();

		// 创建上下文对象:
		WebAppContext context = new WebAppContext(); // 关键点 OK;

		// 给上下文对象指定Handler:
		// context.addServlet(JettyWebSocketServlet.class, "/chat"); //关键点 OK;
		context.addServlet(new ServletHolder(webSocketServlet), "/chat"); // 关键点 OK;

		// 配置上下文对象信息:
		context.setContextPath("/jetty/websocket4");
		context.setResourceBase("E:\\test\\eclipse\\WebSocket4Jetty4\\target\\WebSocket4Jetty4-1.0.0");
		context.setDisplayName("JettyWebSocket");
		String[] welcomeFiles = {"index.html"};
		context.setWelcomeFiles(welcomeFiles);
		context.setDescriptor(".\\WEB-INF\\web.xml");
		context.setParentLoaderPriority(true);

		// 给服务器对象server指定上下文信息:
		server.setHandler(context);

		// 启动Jetty服务器:
		try
		{
			// 启动服务器:
			server.start();

			Print.info("服务器已启动");

			// 等待并处理请求;
			server.join();
		}
		catch(Exception e)
		{
			Print.exce("EXCEPTION: %s", e.getMessage());
		}
	}

	public static void test2()
	{
		// 创建Jetty服务器对象:
		Server server = new Server();

		// 创建连接器1,并绑定端口1;
		ServerConnector connector1 = new ServerConnector(server);
		connector1.setPort(9000);

		// 创建连接器2,并绑定端口2;
		ServerConnector connector2 = new ServerConnector(server);
		connector2.setPort(9001);

		// 把两个连接器与服务器对象server绑定起来;
		server.addConnector(connector1);
		server.addConnector(connector2);

		// 创建处理WebSocket连接的Handler:
		JettyWebSocketServlet webSocketServlet = new JettyWebSocketServlet();

		// 创建上下文对象:
		WebAppContext context = new WebAppContext(); // 关键点 OK;

		// 给上下文对象指定Handler:
		// context.addServlet(JettyWebSocketServlet.class, "/chat"); //关键点 OK;
		context.addServlet(new ServletHolder(webSocketServlet), "/chat"); // 关键点 OK;

		// 配置上下文对象信息:
		context.setContextPath("/jetty/websocket4");
		context.setResourceBase("E:\\test\\eclipse\\WebSocket4Jetty4\\target\\WebSocket4Jetty4-1.0.0");
		context.setDisplayName("JettyWebSocket");
		String[] welcomeFiles = {"index.html"};
		context.setWelcomeFiles(welcomeFiles);
		context.setDescriptor(".\\WEB-INF\\web.xml");
		context.setParentLoaderPriority(true);

		// 给服务器对象server指定上下文信息:
		server.setHandler(context);

		// 启动Jetty服务器:
		try
		{
			// 启动服务器:
			server.start();

			Print.info("服务器已启动");

			// 等待并处理请求;
			server.join();
		}
		catch(Exception e)
		{
			Print.exce("EXCEPTION: %s", e.getMessage());
		}
	}

	public static void bindPort(Server server, int ... ports)
	{
		ServerConnector connector = null;

		for(int port: ports)
		{
			connector = new ServerConnector(server);
			connector.setPort(port);
			server.addConnector(connector);
		}
	}

	public static void test3()
	{
		// 创建Jetty服务器对象:
		Server server = new Server();

		// 创建连接器1,并绑定端口1;
		ServerConnector connector1 = new ServerConnector(server);
		connector1.setPort(9000);

		// 创建连接器2,并绑定端口2;
		ServerConnector connector2 = new ServerConnector(server);
		connector2.setPort(9001);

		// 把两个连接器与服务器对象server绑定起来;
		server.addConnector(connector1);
		server.addConnector(connector2);

		// 给服务器对象server绑定服务端口,可以绑定多个服务端口;
		bindPort(server, 9000, 9001, 9002);

		// 创建处理WebSocket连接的Handler:
		JettyWebSocketServlet webSocketServlet = new JettyWebSocketServlet();

		// 创建上下文对象:
		WebAppContext context = new WebAppContext(); // 关键点 OK;

		// 给上下文对象指定Handler:
		// 给上下文对象指定Handler:
		// context.addServlet(JettyWebSocketServlet.class, "/chat"); //关键点 OK;
		context.addServlet(new ServletHolder(webSocketServlet), "/chat"); // 关键点 OK;

		// 配置上下文对象信息:
		context.setContextPath("/jetty/websocket4");
		context.setResourceBase("E:\\test\\eclipse\\WebSocket4Jetty4\\target\\WebSocket4Jetty4-1.0.0");
		context.setDisplayName("JettyWebSocket");
		String[] welcomeFiles = {"index.html"};
		context.setWelcomeFiles(welcomeFiles);
		context.setDescriptor(".\\WEB-INF\\web.xml");
		context.setParentLoaderPriority(true);

		// 给服务器对象server指定上下文信息:
		server.setHandler(context);

		// 启动Jetty服务器:
		try
		{
			// 启动服务器:
			server.start();

			Print.info("服务器已启动");

			// 等待并处理请求;
			server.join();
		}
		catch(Exception e)
		{
			Print.exce("EXCEPTION: %s", e.getMessage());
		}
	}

	public static void main(String[] args)
	{
		JettyWebSocketMain.test1();
	}
}
