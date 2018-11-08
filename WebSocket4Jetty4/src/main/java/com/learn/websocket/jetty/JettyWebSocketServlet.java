package com.learn.websocket.jetty;

import javax.servlet.annotation.WebServlet;

import org.eclipse.jetty.websocket.api.WebSocketPolicy;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import com.wrapper.Print;

//@WebServlet(name = "JettyWebSocket", value = "/chat", urlPatterns = {"/chat"}) ��ע����Ч
public class JettyWebSocketServlet extends WebSocketServlet //�ؼ��� OK;
{
	public JettyWebSocketServlet()
	{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void configure(WebSocketServletFactory factory)
	{
		// TODO Auto-generated method stub
		WebSocketPolicy policy = factory.getPolicy();
		policy.setIdleTimeout(10L * 60L * 1000L);
		policy.setAsyncWriteTimeout(10L * 1000L);
		
		/* �����Զ����WebSocket��� */
		factory.setCreator(new JettyWebSocketCreator()); //��ʽ1 OK
		//factory.register(JettyWebSocket.class);          //��ʽ2 OK
	}
}
