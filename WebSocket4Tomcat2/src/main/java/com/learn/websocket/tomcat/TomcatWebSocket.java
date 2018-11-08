package com.learn.websocket.tomcat;

/* Java���ð� */
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* WebSocket About */
import javax.websocket.Session;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.CloseReason;

/* �Զ���� */
import com.wrapper.Print;

public class TomcatWebSocket extends Endpoint implements MessageHandler.Whole<String>
{
	private String TAG = "[�̳з�ʽ]";
	private static int countOnline = 0;
	private static CopyOnWriteArraySet<TomcatWebSocket> clientSet = new CopyOnWriteArraySet<TomcatWebSocket>();
	private Session clientSession;
	
	public TomcatWebSocket()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * �ͻ�����������ʱ�ص��ķ���;
	 */
	@Override
	public void onOpen(Session session, EndpointConfig config)
	{
		// TODO Auto-generated method stub
		this.clientSession = session;
		this.clientSession.addMessageHandler(this); //�̳з�ʽʵ��WebSocketʱ,��Ҫ���ͻ��˵����ӻỰ����ָ��һ����Ϣ����������,�����ܴ�����Ϣ;����ǹؼ�;
		TomcatWebSocket.clientSet.add(this);
		TomcatWebSocket.incOnlineCount();
		Print.info("%s �пͻ���������,��ǰ��������Ϊ%d��", TAG, TomcatWebSocket.getOnlineCount());
		this.sendMessage(session, TAG + " ��ӭ���٣���ǰ����Ϊ" + TomcatWebSocket.getOnlineCount() + "��");
	}
	
	/**
	 * �յ���Ϣʱ�Ļص�����;
	 * @param message �ͻ��˷��͹�������Ϣ;
	 * @param last ��ѡ����;
	 */
	@Override
	public void onMessage(String message)
	{
		// TODO Auto-generated method stub
		Print.info("%s �յ�����������Ϣ: %s", TAG, message);
		this.sendMessage(this.clientSession, TAG + " �����Ѿ��յ�����������Ϣ: " + message);
	}

	/**
	 * ���ӶϿ�ʱ�Ļص�����;
	 */
	public void onClose(Session session, CloseReason closeReason)
	{
		// NO-OP by default
		this.sendMessage(this.clientSession, TAG + " лл���������ߣ���ӭ�´ι���!");
		TomcatWebSocket.clientSet.remove(this);
		TomcatWebSocket.decOnlineCount();
		Print.info("%s �пͻ���������,��ǰ����������Ϊ%d", TAG, TomcatWebSocket.getOnlineCount());
	}
	
	/**
	 * ��������ʱ�Ļص�����;
	 * @param session �ͻ������ӻỰ;
	 * @param error   ����������Ϣ��Throwableʵ��;
	 */
	public void onError(Session session, Throwable throwable)
	{
		// NO-OP by default
		Print.error("%s ����������: %s", TAG, throwable.getMessage());
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
