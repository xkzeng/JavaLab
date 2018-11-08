package com.learn.websocket.tomcat;

/* Java内置包 */
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* WebSocket About */
import javax.websocket.Session;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.CloseReason;

/* 自定义包 */
import com.wrapper.Print;

public class TomcatWebSocket extends Endpoint implements MessageHandler.Whole<String>
{
	private String TAG = "[继承方式]";
	private static int countOnline = 0;
	private static CopyOnWriteArraySet<TomcatWebSocket> clientSet = new CopyOnWriteArraySet<TomcatWebSocket>();
	private Session clientSession;
	
	public TomcatWebSocket()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 客户端连接上来时回调的方法;
	 */
	@Override
	public void onOpen(Session session, EndpointConfig config)
	{
		// TODO Auto-generated method stub
		this.clientSession = session;
		this.clientSession.addMessageHandler(this); //继承方式实现WebSocket时,需要给客户端的连接会话对象指定一个消息处理器对象,否则不能处理消息;这点是关键;
		TomcatWebSocket.clientSet.add(this);
		TomcatWebSocket.incOnlineCount();
		Print.info("%s 有客户端上线了,当前在线人数为%d人", TAG, TomcatWebSocket.getOnlineCount());
		this.sendMessage(session, TAG + " 欢迎光临，当前认数为" + TomcatWebSocket.getOnlineCount() + "人");
	}
	
	/**
	 * 收到消息时的回调方法;
	 * @param message 客户端发送过来的消息;
	 * @param last 可选参数;
	 */
	@Override
	public void onMessage(String message)
	{
		// TODO Auto-generated method stub
		Print.info("%s 收到您发来的消息: %s", TAG, message);
		this.sendMessage(this.clientSession, TAG + " 我们已经收到您发来的消息: " + message);
	}

	/**
	 * 连接断开时的回调方法;
	 */
	public void onClose(Session session, CloseReason closeReason)
	{
		// NO-OP by default
		this.sendMessage(this.clientSession, TAG + " 谢谢您，请慢走，欢迎下次光临!");
		TomcatWebSocket.clientSet.remove(this);
		TomcatWebSocket.decOnlineCount();
		Print.info("%s 有客户端离线了,当前在线连接数为%d", TAG, TomcatWebSocket.getOnlineCount());
	}
	
	/**
	 * 发生错误时的回调方法;
	 * @param session 客户端连接会话;
	 * @param error   描述错误信息的Throwable实例;
	 */
	public void onError(Session session, Throwable throwable)
	{
		// NO-OP by default
		Print.error("%s 发生错误了: %s", TAG, throwable.getMessage());
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
