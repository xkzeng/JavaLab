import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.webapp.WebAppContext;
//import org.eclipse.jetty.server.handler.ContextHandlerCollection;
//import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.ServerConnector;

import com.learn.websocket.jetty.JettyWebSocketHandler;
import com.wrapper.Print;

public class JettyWebSocketMain
{
	public JettyWebSocketMain()
	{
		// TODO Auto-generated constructor stub
	}

	public static void test1()
	{
		// ����Jetty����������:
		Server server = new Server(9000);
		server.setStopAtShutdown(true);

		// ��������WebSocket���ӵ�Handler:
		JettyWebSocketHandler webSocketHandler = new JettyWebSocketHandler();

		// ���������Ķ���:
		ContextHandler context = new ContextHandler();

		// �������Ķ���ָ��Handler:
		context.setHandler(webSocketHandler);

		// ���������Ķ������Ϣ:
		context.setContextPath("/jetty/websocket3");
		context.setResourceBase("E:\\test\\eclipse\\WebSocket4Jetty3\\target\\WebSocket4Jetty3-1.0.0");
		context.setDisplayName("JettyWebSocket");
		String[] welcomeFiles = {"index.html"};
		context.setWelcomeFiles(welcomeFiles);
		// context.setDescriptor(".\\WEB-INF\\web.xml");
		// context.setParentLoaderPriority(true);

		// ������������serverָ����������Ϣ:
		server.setHandler(context);

		// ����Jetty������:
		try
		{
			// ����������:
			server.start();

			Print.info("������������");

			// �ȴ�����������;
			server.join();
		}
		catch(Exception e)
		{
			Print.exce("EXCEPTION: %s", e.getMessage());
		}
	}

	public static void test2()
	{
		// ����Jetty����������:
		Server server = new Server();

		// ����������1,���󶨶˿�1;
		ServerConnector connector1 = new ServerConnector(server);
		connector1.setPort(9000);

		// ����������2,���󶨶˿�2;
		ServerConnector connector2 = new ServerConnector(server);
		connector2.setPort(9001);

		// �����������������������server������;
		server.addConnector(connector1);
		server.addConnector(connector2);

		// ��������WebSocket���ӵ�Handler:
		JettyWebSocketHandler webSocketHandler = new JettyWebSocketHandler();

		// ���������Ķ���:
		ContextHandler context = new ContextHandler();

		// �������Ķ���ָ��Handler:
		context.setHandler(webSocketHandler);

		// ���������Ķ������Ϣ:
		context.setContextPath("/jetty/websocket3");
		context.setResourceBase("E:\\test\\eclipse\\WebSocket4Jetty3\\target\\WebSocket4Jetty3-1.0.0");
		context.setDisplayName("JettyWebSocket");
		String[] welcomeFiles = {"index.html"};
		context.setWelcomeFiles(welcomeFiles);
		// context.setDescriptor(".\\WEB-INF\\web.xml");
		// context.setParentLoaderPriority(true);

		// ������������serverָ����������Ϣ:
		server.setHandler(context);

		// ����Jetty������:
		try
		{
			// ����������:
			server.start();

			Print.info("������������");

			// �ȴ�����������;
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
		// ����Jetty����������:
		Server server = new Server();

		// ����������1,���󶨶˿�1;
		ServerConnector connector1 = new ServerConnector(server);
		connector1.setPort(9000);

		// ����������2,���󶨶˿�2;
		ServerConnector connector2 = new ServerConnector(server);
		connector2.setPort(9001);

		// �����������������������server������;
		server.addConnector(connector1);
		server.addConnector(connector2);

		// ������������server�󶨷���˿�,���԰󶨶������˿�;
		bindPort(server, 9000, 9001, 9002);

		// ��������WebSocket���ӵ�Handler:
		JettyWebSocketHandler webSocketHandler = new JettyWebSocketHandler();

		// ���������Ķ���:
		ContextHandler context = new ContextHandler();

		// �������Ķ���ָ��Handler:
		context.setHandler(webSocketHandler);

		// ���������Ķ������Ϣ:
		context.setContextPath("/jetty/websocket3");
		context.setResourceBase("E:\\test\\eclipse\\WebSocket4Jetty3\\target\\WebSocket4Jetty3-1.0.0");
		context.setDisplayName("JettyWebSocket");
		String[] welcomeFiles = {"index.html"};
		context.setWelcomeFiles(welcomeFiles);
		// context.setDescriptor(".\\WEB-INF\\web.xml");
		// context.setParentLoaderPriority(true);

		// ������������serverָ����������Ϣ:
		server.setHandler(context);

		// ����Jetty������:
		try
		{
			// ����������:
			server.start();

			Print.info("������������");

			// �ȴ�����������;
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
