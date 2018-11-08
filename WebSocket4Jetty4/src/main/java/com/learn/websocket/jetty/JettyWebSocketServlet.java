package com.learn.websocket.jetty;

import javax.servlet.annotation.WebServlet;

import org.eclipse.jetty.websocket.api.WebSocketPolicy;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import com.wrapper.Print;

//@WebServlet(name = "JettyWebSocket", value = "/chat", urlPatterns = {"/chat"}) 该注解无效
public class JettyWebSocketServlet extends WebSocketServlet //关键点 OK;
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
		
		/* 配置自定义的WebSocket组合 */
		factory.setCreator(new JettyWebSocketCreator()); //方式1 OK
		//factory.register(JettyWebSocket.class);          //方式2 OK
	}
}
