package com.learn.websocket.tomcat;

/* Java���ð� */
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

/* �Զ���� */
import com.wrapper.Print;

@ServerEndpoint(value = "/TestWebSocket")
public class TomcatWebSocket
{
	private String TAG = "[ע�ⷽʽ]";
	private static int countOnline = 0;
	private static CopyOnWriteArraySet<TomcatWebSocket> clientSet = new CopyOnWriteArraySet<TomcatWebSocket>();
	private Session clientSession;
	
	public TomcatWebSocket()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * ���ӽ����ɹ�ʱ�Ļص�����;
	 * @param session �ͻ������ӻỰ;
	 */
	@OnOpen
	public void onConnected(Session session)
	{
		this.clientSession = session;
		TomcatWebSocket.clientSet.add(this);
		TomcatWebSocket.incOnlineCount();
		Print.info("%s �пͻ���������,��ǰ��������Ϊ%d��", TAG, TomcatWebSocket.getOnlineCount());
		this.sendMessage(session, TAG + " ��ӭ���٣���ǰ����Ϊ" + TomcatWebSocket.getOnlineCount() + "��");
	}
	
	/**
	 * ���ӶϿ�ʱ�Ļص�����;
	 */
	@OnClose
	public void onClosed()
	{
		this.sendMessage(this.clientSession, TAG + " лл���������ߣ���ӭ�´ι���!");
		TomcatWebSocket.clientSet.remove(this);
		TomcatWebSocket.decOnlineCount();
		Print.info("%s �пͻ���������,��ǰ����������Ϊ%d", TAG, TomcatWebSocket.getOnlineCount());
	}
	
	/**
	 * �յ���Ϣʱ�Ļص�����;
	 * @param message �ͻ��˷��͹�������Ϣ;
	 * @param session ��ѡ����,����ͻ������ӻỰ;
	 */
	@OnMessage
	public void onMessage(String message, Session session)
	{
		Print.info("%s �յ�����������Ϣ: %s", TAG, message);
		this.sendMessage(session, TAG + " �����Ѿ��յ�����������Ϣ: " + message);
	}
	
	/**
	 * ��������ʱ�Ļص�����;
	 * @param session �ͻ������ӻỰ;
	 * @param error   ����������Ϣ��Throwableʵ��;
	 */
	@OnError
	public void onError(Session session, Throwable error)
	{
		Print.error("%s ����������: %s", TAG, error.getMessage());
	}
	
	/**
	 * ���ͻ��˷�����Ϣ;
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
