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
		// ����Jetty����������:
		Server server = new Server(9000);
		server.setStopAtShutdown(true);

		// ��������WebSocket���ӵ�Handler:
		JettyWebSocketServlet webSocketServlet = new JettyWebSocketServlet();

		// ���������Ķ���:
		WebAppContext context = new WebAppContext(); // �ؼ��� OK;

		// �������Ķ���ָ��Handler:
		// context.addServlet(JettyWebSocketServlet.class, "/chat"); //�ؼ��� OK;
		context.addServlet(new ServletHolder(webSocketServlet), "/chat"); // �ؼ��� OK;

		// ���������Ķ�����Ϣ:
		context.setContextPath("/jetty/websocket4");
		context.setResourceBase("E:\\test\\eclipse\\WebSocket4Jetty4\\target\\WebSocket4Jetty4-1.0.0");
		context.setDisplayName("JettyWebSocket");
		String[] welcomeFiles = {"index.html"};
		context.setWelcomeFiles(welcomeFiles);
		context.setDescriptor(".\\WEB-INF\\web.xml");
		context.setParentLoaderPriority(true);

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
		JettyWebSocketServlet webSocketServlet = new JettyWebSocketServlet();

		// ���������Ķ���:
		WebAppContext context = new WebAppContext(); // �ؼ��� OK;

		// �������Ķ���ָ��Handler:
		// context.addServlet(JettyWebSocketServlet.class, "/chat"); //�ؼ��� OK;
		context.addServlet(new ServletHolder(webSocketServlet), "/chat"); // �ؼ��� OK;

		// ���������Ķ�����Ϣ:
		context.setContextPath("/jetty/websocket4");
		context.setResourceBase("E:\\test\\eclipse\\WebSocket4Jetty4\\target\\WebSocket4Jetty4-1.0.0");
		context.setDisplayName("JettyWebSocket");
		String[] welcomeFiles = {"index.html"};
		context.setWelcomeFiles(welcomeFiles);
		context.setDescriptor(".\\WEB-INF\\web.xml");
		context.setParentLoaderPriority(true);

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
		JettyWebSocketServlet webSocketServlet = new JettyWebSocketServlet();

		// ���������Ķ���:
		WebAppContext context = new WebAppContext(); // �ؼ��� OK;

		// �������Ķ���ָ��Handler:
		// �������Ķ���ָ��Handler:
		// context.addServlet(JettyWebSocketServlet.class, "/chat"); //�ؼ��� OK;
		context.addServlet(new ServletHolder(webSocketServlet), "/chat"); // �ؼ��� OK;

		// ���������Ķ�����Ϣ:
		context.setContextPath("/jetty/websocket4");
		context.setResourceBase("E:\\test\\eclipse\\WebSocket4Jetty4\\target\\WebSocket4Jetty4-1.0.0");
		context.setDisplayName("JettyWebSocket");
		String[] welcomeFiles = {"index.html"};
		context.setWelcomeFiles(welcomeFiles);
		context.setDescriptor(".\\WEB-INF\\web.xml");
		context.setParentLoaderPriority(true);

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
