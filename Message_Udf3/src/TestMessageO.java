
import com.message.Message;
import com.wrapper.Print;
import com.message.Looper;
import com.message.LooperThread;
import com.message.MessageHandler;

public class TestMessageO extends MessageHandler
{
	public TestMessageO()
	{
		Print.info("call TestMessageO()");
	}
	
	public TestMessageO(Looper _looper)
	{
		super(_looper);
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
		Looper _looper = Looper.newLooper();
		LooperThread _looperThread = new LooperThread(_looper);
		TestMessageO tm = new TestMessageO(_looper);
		_looperThread.start();
		
		new Thread(
				new Runnable()
				{
					public void run()
					{
						int _what = 1;
						
						while(true)
						{
							try
							{
								Thread.sleep(1000);
							}
							catch(Exception e)
							{}
							
							tm.sendMessage(_what);
							_what++;
						}
						
					}
				}
				).start();
		

		
		System.out.println("ThreadY: " + _looperThread.getName() + ", id = " + _looperThread.getId());
		
		try
		{
			_looperThread.join();
		}
		catch(Exception e)
		{}
		System.out.println("exit");
	}
}
