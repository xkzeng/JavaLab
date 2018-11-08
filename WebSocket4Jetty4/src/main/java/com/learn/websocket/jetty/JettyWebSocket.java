package com.learn.websocket.jetty;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

import com.wrapper.Print;

public class JettyWebSocket extends WebSocketAdapter
{
	private static String TAG = "[Servlet��ʽ]";
	private Session mSession = null;
	
	public JettyWebSocket()
	{
		// TODO Auto-generated constructor stub
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

	@Override
	public void onWebSocketConnect(Session session)
	{
		// TODO Auto-generated method stub
		this.mSession = session;
		String ip = session.getRemoteAddress().getHostString();
		int port = session.getRemoteAddress().getPort();
		Print.info("%s �ͻ��� %s:%d ����", TAG, ip, port);
		
		String replyText = TAG + " Hello, WebSocket, Welcome!";
		this.sendMessage(session, replyText);
	}

	@Override
	public void onWebSocketBinary(byte[] payload, int offset, int len)
	{
		// TODO Auto-generated method stub
		String ip = this.mSession.getRemoteAddress().getHostString();
		int port = this.mSession.getRemoteAddress().getPort();
		Print.info("%s �ͻ��� %s:%d ������Ϣ:%d", TAG, ip, port, len);
	}

	@Override
	public void onWebSocketText(String message)
	{
		// TODO Auto-generated method stub
		String ip = this.mSession.getRemoteAddress().getHostString();
		int port = this.mSession.getRemoteAddress().getPort();
		Print.info("%s �ͻ��� %s:%d ������Ϣ:%s", TAG, ip, port, message);
		
		String replyText = TAG + " ���յ�����������Ϣ: " + message;
		this.sendMessage(this.mSession, replyText);
	}
	
	@Override
	public void onWebSocketClose(int statusCode, String reason)
	{
		// TODO Auto-generated method stub
		String ip = this.mSession.getRemoteAddress().getHostString();
		int port = this.mSession.getRemoteAddress().getPort();
		Print.info("%s �ͻ��� %s:%d ����; status = %d, reason: %s", TAG, ip, port, statusCode, reason);
	}
	
	@Override
	public void onWebSocketError(Throwable error)
	{
		// TODO Auto-generated method stub
		String ip = this.mSession.getRemoteAddress().getHostString();
		int port = this.mSession.getRemoteAddress().getPort();
		Print.error("%s �ͻ��� %s:%d ��������:%s", TAG, ip, port, error.getMessage());
	}
}
