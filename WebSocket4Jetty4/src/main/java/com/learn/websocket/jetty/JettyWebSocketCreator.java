package com.learn.websocket.jetty;

import java.util.List;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

import com.wrapper.Print;

public class JettyWebSocketCreator implements WebSocketCreator
{
	private JettyWebSocket mWebSocket = null;
	
	public JettyWebSocketCreator()
	{
		// create the reusable websockets
		this.mWebSocket = new JettyWebSocket();
	}

	@Override
	public Object createWebSocket(ServletUpgradeRequest requ, ServletUpgradeResponse resp)
	{
		return this.mWebSocket;
	}
}
