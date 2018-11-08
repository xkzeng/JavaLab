
import java.lang.Runtime;
import java.lang.Runnable;

import com.wrapper.Print;
import com.message.Message;
import com.message.MessageQueue;
import com.message.Looper;
import com.message.MessageHandler;
import com.message.LooperThread;

public class TestMessageM extends MessageHandler implements Runnable
{
	private MessageHandler mHandler = new MessageHandler()
	{
		@Override
		public boolean filterMessage(Message msg)
		{
			// TODO Auto-generated method stub
			Print.warn("mHandler.filterMessage() is not be implemeted");
			return false;
		}
		
		public boolean handleMessage(Message msg)
		{
			Print.info("%d: handle messageX %d", msg.hashCode(), msg._what);
			return true;
		}
		
		public void messageCallback(Message msg, boolean result)
		{
			Print.info("%d: messageX %d have been handled: %b", msg.hashCode(), msg._what, result);
		}
	};
	
	public TestMessageM()
	{
	}
	
	public TestMessageM(Looper _looper)
	{
		super(_looper);
	}
	
	@Override
	public boolean filterMessage(Message msg)
	{
		// TODO Auto-generated method stub
		Print.warn("TestMessageM.filterMessage() is not be implemeted");
		return false;
	}
	
	@Override
	public boolean handleMessage(Message msg)
	{
		Print.info("%d: handle message %d ......", msg.hashCode(), msg._what);
		return true;
	}
	
	@Override
	public void messageCallback(Message msg, boolean result)
	{
		Print.info("%d: message %d have been handled: %b", msg.hashCode(), msg._what, result);
	}
	
	public static void main(String[] args)
	{
    Looper.prepareMainLooper();
    Looper _looper = Looper.myLooper();
    
    TestMessageM tm = new TestMessageM();
    
    Thread sendMessageThread = new Thread(tm);
    sendMessageThread.start();
    
    Thread mainThread = _looper.getThread();
    Print.info("mainThread: " + mainThread.getName() + ", id = " + mainThread.getId());
		
    Looper.loop();
    throw new RuntimeException("Main thread loop unexpectedly exited");
	}
	
	/** send test message */
	@Override
	public void run()
	{
		int what = 1;
		int what2 = 100;
		
		while(true)
		{
			try
			{
				Thread.sleep(1000);
			}
			catch(Exception e)
			{}
			
			this.sendMessage(what);
			what++;
			
			mHandler.sendMessage(what2, true);
			what2++;
		}
		
	}
}
