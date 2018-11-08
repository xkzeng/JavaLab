package com.learn.websocket.jetty;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import com.wrapper.Print;

import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketFrame;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;

@WebSocket(maxBinaryMessageSize = 128*1024)
public class JettyBinaryWebSocket
{
	private static String TAG = "[ע�ⷽʽ-BINARY]";
	
	public JettyBinaryWebSocket()
	{
		// TODO Auto-generated constructor stub
		Print.debug("��������Ƹ�ʽ��WebSocket����");
	}
	
	private void sendMessage(Session session, String message)
	{
		try
		{
			session.getRemote().sendString(message);
		}
		catch(Exception e)
		{
			Print.error("EXCEPTION: %s", e.getMessage());
		}
	}
	
	@OnWebSocketConnect
	public void onConnnected(Session session)
	{
		String ip = session.getRemoteAddress().getHostString();
		int port = session.getRemoteAddress().getPort();
		Print.info("%s �ͻ��� %s:%d ����", TAG, ip, port);
		
		String replyText = TAG + " Hello, WebSocket, Welcome!";
		this.sendMessage(session, replyText);
	}
	
	@OnWebSocketMessage
	public void onBinaryMessage(Session session, byte[] buf, int offset, int length)
	{
		String ip = session.getRemoteAddress().getHostString();
		int port = session.getRemoteAddress().getPort();
		Print.info("%s �ͻ��� %s:%d ������Ϣ:%d", TAG, ip, port, length);
//		
//		String replyText = " ���յ�����������Ϣ:" + message;
//		this.sendMessage(session, replyText);
	}
	
	@OnWebSocketClose
	public void onClose(Session session, int statusCode, String reason)
	{
		String ip = session.getRemoteAddress().getHostString();
		int port = session.getRemoteAddress().getPort();
		Print.info("%s �ͻ��� %s:%d ����; status = %d, reason: %s", TAG, ip, port, statusCode, reason);
	}
	
	@OnWebSocketError
	public void onError(Session session, Throwable error)
	{
		String ip = session.getRemoteAddress().getHostString();
		int port = session.getRemoteAddress().getPort();
		Print.error("%s �ͻ��� %s:%d ��������:%s", TAG, ip, port, error.getMessage());
	}
}
