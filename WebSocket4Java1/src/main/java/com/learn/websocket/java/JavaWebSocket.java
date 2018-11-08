package com.learn.websocket.java;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;
import org.java_websocket.server.WebSocketServer;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.WebSocketListener;
import org.java_websocket.WebSocketAdapter;

import com.wrapper.Print;

public class JavaWebSocket extends WebSocketServer
{
	private int mPort = 2018;
	
	public JavaWebSocket()
	{
		// TODO Auto-generated constructor stub
	}
	
	public JavaWebSocket(int port)
	{
		// TODO Auto-generated constructor stub
		super(new InetSocketAddress(port));
		this.mPort = port;
	}
	
	@Override
	public void onStart()
	{
		// TODO Auto-generated method stub
		Print.info("WebSocket�������ڶ˿�%d������", this.mPort);
	}
	
	@Override
	public void onOpen(WebSocket ws, ClientHandshake handshake)
	{
		// TODO Auto-generated method stub
		String ip = ws.getRemoteSocketAddress().getHostString();
		int port = ws.getRemoteSocketAddress().getPort();
		Print.info("�ͻ��� %s:%d ����", ip, port);
		ws.send("Hello, WebSocket, Welcome!");
	}
	
	@Override
	public void onMessage(WebSocket ws, String message)
	{
		// TODO Auto-generated method stub
		String ip = ws.getRemoteSocketAddress().getHostString();
		int port = ws.getRemoteSocketAddress().getPort();
		Print.info("�յ��ͻ��� %s:%d ��������Ϣ: %s", ip, port, message);
		ws.send("���յ���������: " + message);
//		if(ws.isConnecting())
//		{
//			Print.info("�ͻ��� %s:%d ����������: %s", ip, port, message);
//		}
//		else if(ws.isOpen())
//		{
//			Print.info("�ͻ��� %s:%d �����Ѵ�: %s", ip, port, message);
//		}
//		else if(ws.isClosing())
//		{
//			Print.info("�ͻ��� %s:%d �������ڹر�: %s", ip, port, message);
//		}
//		else if(ws.isClosed())
//		{
//			Print.info("�ͻ��� %s:%d �����ѹر�: %s", ip, port, message);
//		}
//		else
//		{
//			Print.info("�ͻ��� %s:%d δ֪��Ϣ: %s", ip, port, message);
//		}
	}
	
	@Override
	public void onMessage( WebSocket ws, ByteBuffer message )
	{
		String ip = ws.getRemoteSocketAddress().getHostString();
		int port = ws.getRemoteSocketAddress().getPort();
		Print.info("�յ��ͻ��� %s:%d ��������Ϣ: %s", ip, port, message);
		ws.send("���յ���������:" + message);
	}

	@Override
	public void onClose(WebSocket ws, int code, String reason, boolean remote)
	{
		// TODO Auto-generated method stub
		String ip = ws.getRemoteSocketAddress().getHostString();
		int port = ws.getRemoteSocketAddress().getPort();
		Print.info("�ͻ��� %s:%d ����, code = %d, reason = %s, remote = %b", ip, port, code, reason, remote);
	}

	@Override
	public void onError(WebSocket ws, Exception ex)
	{
		// TODO Auto-generated method stub
		String ip = ws.getRemoteSocketAddress().getHostString();
		int port = ws.getRemoteSocketAddress().getPort();
		Print.error("�ͻ��� %s:%d ��������: %s", ip, port, ex.getMessage());
	}
	
	public static void main(String[] args)
	{
		JavaWebSocket websocketServer = new JavaWebSocket(9000);
		websocketServer.start();
	}
}
