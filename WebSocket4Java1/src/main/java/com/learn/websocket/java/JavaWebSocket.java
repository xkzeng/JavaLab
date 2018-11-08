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
		Print.info("WebSocket服务已在端口%d上启动", this.mPort);
	}
	
	@Override
	public void onOpen(WebSocket ws, ClientHandshake handshake)
	{
		// TODO Auto-generated method stub
		String ip = ws.getRemoteSocketAddress().getHostString();
		int port = ws.getRemoteSocketAddress().getPort();
		Print.info("客户端 %s:%d 上线", ip, port);
		ws.send("Hello, WebSocket, Welcome!");
	}
	
	@Override
	public void onMessage(WebSocket ws, String message)
	{
		// TODO Auto-generated method stub
		String ip = ws.getRemoteSocketAddress().getHostString();
		int port = ws.getRemoteSocketAddress().getPort();
		Print.info("收到客户端 %s:%d 发来的信息: %s", ip, port, message);
		ws.send("已收到您的来信: " + message);
//		if(ws.isConnecting())
//		{
//			Print.info("客户端 %s:%d 正在连接中: %s", ip, port, message);
//		}
//		else if(ws.isOpen())
//		{
//			Print.info("客户端 %s:%d 连接已打开: %s", ip, port, message);
//		}
//		else if(ws.isClosing())
//		{
//			Print.info("客户端 %s:%d 连接正在关闭: %s", ip, port, message);
//		}
//		else if(ws.isClosed())
//		{
//			Print.info("客户端 %s:%d 连接已关闭: %s", ip, port, message);
//		}
//		else
//		{
//			Print.info("客户端 %s:%d 未知消息: %s", ip, port, message);
//		}
	}
	
	@Override
	public void onMessage( WebSocket ws, ByteBuffer message )
	{
		String ip = ws.getRemoteSocketAddress().getHostString();
		int port = ws.getRemoteSocketAddress().getPort();
		Print.info("收到客户端 %s:%d 发来的信息: %s", ip, port, message);
		ws.send("已收到您的来信:" + message);
	}

	@Override
	public void onClose(WebSocket ws, int code, String reason, boolean remote)
	{
		// TODO Auto-generated method stub
		String ip = ws.getRemoteSocketAddress().getHostString();
		int port = ws.getRemoteSocketAddress().getPort();
		Print.info("客户端 %s:%d 离线, code = %d, reason = %s, remote = %b", ip, port, code, reason, remote);
	}

	@Override
	public void onError(WebSocket ws, Exception ex)
	{
		// TODO Auto-generated method stub
		String ip = ws.getRemoteSocketAddress().getHostString();
		int port = ws.getRemoteSocketAddress().getPort();
		Print.error("客户端 %s:%d 发生错误: %s", ip, port, ex.getMessage());
	}
	
	public static void main(String[] args)
	{
		JavaWebSocket websocketServer = new JavaWebSocket(9000);
		websocketServer.start();
	}
}
