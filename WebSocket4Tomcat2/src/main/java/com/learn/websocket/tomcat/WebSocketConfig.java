package com.learn.websocket.tomcat;

import java.util.HashSet;
import java.util.Set;
import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

public class WebSocketConfig implements ServerApplicationConfig
{
	public WebSocketConfig()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> scanned)
	{
		// TODO Auto-generated method stub
		Set<ServerEndpointConfig> results = new HashSet<>();
		if(scanned.contains(TomcatWebSocket.class))
		{
			results.add(ServerEndpointConfig.Builder.create(TomcatWebSocket.class, "/TestWebSocket").build());
		}
		return results;
	}

	@Override
	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned)
	{
		// TODO Auto-generated method stub
		return scanned;
	}
}
