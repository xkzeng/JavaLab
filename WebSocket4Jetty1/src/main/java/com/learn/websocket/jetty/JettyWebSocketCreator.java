package com.learn.websocket.jetty;

import java.util.List;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

import com.learn.websocket.jetty.JettyTextWebSocket;
import com.learn.websocket.jetty.JettyBinaryWebSocket;

import com.wrapper.Print;

public class JettyWebSocketCreator implements WebSocketCreator
{
	private JettyTextWebSocket   mTextWebSocket = null;
	private JettyBinaryWebSocket mBinaryWebSocket = null;
	
	public JettyWebSocketCreator()
	{
		// create the reusable websockets
		this.mTextWebSocket = new JettyTextWebSocket();
		this.mBinaryWebSocket = new JettyBinaryWebSocket();
	}

	@Override
	public Object createWebSocket(ServletUpgradeRequest requ, ServletUpgradeResponse resp)
	{
		//ͨѶЭ��ƥ������;
		List<String> subProtocols = requ.getSubProtocols(); //Header����Sec-WebSocket-Protocol��ֵ;
		for(String subProtocol: subProtocols)
		{
			if("binary".equals(subProtocol))
			{
				Print.debug("AAAAAAAAAAAA");
				resp.setAcceptedSubProtocol(subProtocol);
				//resp.setAcceptedSubProtocol("binary");
				return this.mBinaryWebSocket;
			}
			
			if("text".equals(subProtocol))
			{
				Print.debug("BBBBBBBBBBBB");
			  resp.setAcceptedSubProtocol(subProtocol);
				//resp.setAcceptedSubProtocol("text");
				return this.mTextWebSocket;
			}
			
			//others protocol
			//......
		}
		
    // No valid subprotocol in request, ignore the request
    //return null;
		Print.debug("CCCCCCCCCCCC = %d", subProtocols.size());
		
		//Ĭ�ϵ�ͨѶЭ��:
		//resp.setAcceptedSubProtocol("text"); �������д���,WebSocket���߲��Թ��ߺͿͻ��˾Ͳ�������������;
		return this.mTextWebSocket;
	}
}
