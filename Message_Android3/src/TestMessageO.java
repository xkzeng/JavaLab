
import java.lang.Runtime;
import java.lang.Runnable;

import com.message.Message;
import com.message.Looper;
import com.message.MessageHandler;
import com.wrapper.Print;
import com.message.LooperThread;

public class TestMessageO extends MessageHandler implements Runnable
{
	public TestMessageO()
	{
		System.out.println("call TestMessageO()");
	}
	
	public TestMessageO(Looper _looper)
	{
		super(_looper);
		System.out.println("call TestMessageO(Looper _looper)");
	}
	
	@Override
	public boolean filterMessage(Message msg)
	{
		// TODO Auto-generated method stub
		Print.warn("TestMessageO.filterMessage() is not be implemeted");
		return false;
	}
	
	@Override
	public boolean handleMessage(Message msg)
	{
		System.out.println("handle message " + msg._what + " ......");
		return true;
	}
	
	@Override
	public void messageCallback(Message msg, boolean result)
	{
		System.out.println("message " + msg._what + " have been handled:" + result);
	}
	
	public static void main(String[] args)
	{
		Looper _looper = LooperThread.newInstance();
		if(_looper == null)
		{
			System.out.println("looper is null, exit");
			System.exit(-1);
		}
		
		Thread _looperThread = _looper.getThread();
		
  	TestMessageO tm = new TestMessageO(_looper);
  	
  	Thread sendMessageThread = new Thread(tm);
  	sendMessageThread.start();
		
		System.out.println("Thread: " + _looperThread.getName() + ", id = " + _looperThread.getId());
		
  	try
  	{
  		_looperThread.join();
  	}
  	catch(Exception e)
  	{}
  	System.out.println("exit");
	}
	
	/** send test message */
	@Override
	public void run()
	{
		int msgId = 1;
		
		while(true)
		{
			try
			{
				Thread.sleep(1000);
			}
			catch(Exception e)
			{}
			
			//_handler.sendMessage(msgId);
			Message msg = Message.obtain(this, msgId);
			if(msgId % 2 == 0)
			{
				msg.setNeedCallback(true);
			}
			this.sendMessage(msg);
			msgId++;
		}
		
	}
}
