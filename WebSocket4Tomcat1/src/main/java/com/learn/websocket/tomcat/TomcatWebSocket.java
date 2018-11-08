package com.learn.websocket.tomcat;

/* Java内置包 */
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/* WebSocket About */
//import javax.websocket.*;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;

/* 自定义包 */
import com.wrapper.Print;

@ServerEndpoint(value = "/TestWebSocket")
public class TomcatWebSocket
{
	private String TAG = "[注解方式]";
	private static int countOnline = 0;
	private static CopyOnWriteArraySet<TomcatWebSocket> clientSet = new CopyOnWriteArraySet<TomcatWebSocket>();
	private Session clientSession;
	
	public TomcatWebSocket()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * 连接建立成功时的回调方法;
	 * @param session 客户端连接会话;
	 */
	@OnOpen
	public void onConnected(Session session)
	{
		this.clientSession = session;
		TomcatWebSocket.clientSet.add(this);
		TomcatWebSocket.incOnlineCount();
		Print.info("%s 有客户端上线了,当前在线人数为%d人", TAG, TomcatWebSocket.getOnlineCount());
		this.sendMessage(session, TAG + " 欢迎光临，当前认数为" + TomcatWebSocket.getOnlineCount() + "人");
	}
	
	/**
	 * 连接断开时的回调方法;
	 */
	@OnClose
	public void onClosed()
	{
		this.sendMessage(this.clientSession, TAG + " 谢谢您，请慢走，欢迎下次光临!");
		TomcatWebSocket.clientSet.remove(this);
		TomcatWebSocket.decOnlineCount();
		Print.info("%s 有客户端离线了,当前在线连接数为%d", TAG, TomcatWebSocket.getOnlineCount());
	}
	
	/**
	 * 收到消息时的回调方法;
	 * @param message 客户端发送过来的消息;
	 * @param session 可选参数,代表客户端连接会话;
	 */
	@OnMessage
	public void onMessage(String message, Session session)
	{
		Print.info("%s 收到您发来的消息: %s", TAG, message);
		this.sendMessage(session, TAG + " 我们已经收到您发来的消息: " + message);
	}
	
	/**
	 * 发生错误时的回调方法;
	 * @param session 客户端连接会话;
	 * @param error   描述错误信息的Throwable实例;
	 */
	@OnError
	public void onError(Session session, Throwable error)
	{
		Print.error("%s 发生错误了: %s", TAG, error.getMessage());
	}
	
	/**
	 * 给客户端发送消息;
	 */
	public void sendMessage(Session session, String message)
	{
		try
		{
			session.getBasicRemote().sendText(message);
		}
		catch(Exception e)
		{
			Print.exce("%s EXCEPTION: %s", TAG, e.getMessage());
		}
	}
	
	public static synchronized int getOnlineCount()
	{
		return TomcatWebSocket.countOnline;
	}
	
	public static synchronized void incOnlineCount()
	{
		TomcatWebSocket.countOnline++;
	}
	
	public static synchronized void decOnlineCount()
	{
		TomcatWebSocket.countOnline--;
	}
}
