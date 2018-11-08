
import com.wrapper.Print;
import com.message.Message;
import com.message.Looper;
import com.message.MessageHandler;

public class TestMessageMO extends MessageHandler
{
	public TestMessageMO()
	{
		Print.info("call TestMessageO()");
	}
	
	public TestMessageMO(Looper _looper)
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
		Looper _looper = Looper.newMainInstance();
		TestMessageO tm = new TestMessageO(_looper);
		
		new Thread(
				new Runnable()
				{
					public void run()
					{
						int _what = 300;
						int _what2 = 400;
						
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
							
							tm.sendMessage(_what2, true);
							_what2++;
						}
						
					}
				}
				).start();
		

		Thread _looperThread = _looper.getThread(); //Thread.currentThread();
		System.out.println("ThreadY: " + _looperThread.getName() + ", id = " + _looperThread.getId());
		
		_looper.runMainForEver();
		throw new RuntimeException("Main thread loop unexpectedly exited");
	}
}
