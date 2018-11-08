package com.learn.websocket.jetty;

import org.eclipse.jetty.websocket.api.WebSocketPolicy;
import org.eclipse.jetty.io.ByteBufferPool;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import com.wrapper.Print;

//import com.learn.websocket.jetty.JettyWebSocketCreator;

public class JettyWebSocketHandler extends WebSocketHandler
{
	public JettyWebSocketHandler()
	{
		// TODO Auto-generated constructor stub
	}

	public JettyWebSocketHandler(ByteBufferPool bufferPool)
	{
		super(bufferPool);
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
		//factory.setCreator(new JettyWebSocketCreator()); //方式1 OK
		factory.register(JettyWebSocket.class);          //方式2 OK
	}
}
