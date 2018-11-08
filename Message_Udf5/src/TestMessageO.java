
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
	
	public static void testMessage(MessageHandler handler)
	{
		Runnable r = new Runnable()
		{
			public void run()
			{
				int _what1 = 700;
				int _what2 = 800;
				
				while(true)
				{
					try
					{
						Thread.sleep(1000);
					}
					catch(Exception e)
					{}
					
					handler.sendMessage(_what1);
					_what1++;
					
					handler.sendMessage(_what2, true);
					_what2++;
				}
				
			}
		};
		
		Thread _thread = new Thread(r);
		_thread.start();
	}
	
	public static void doTest1()
	{
		Looper _looper = Looper.newLooper();
		LooperThread _looperThread = new LooperThread(_looper);
		
		TestMessageO tm = new TestMessageO(_looper);
		_looperThread.start();
		
		TestMessageO.testMessage(tm);
		
		System.out.println("Thread: " + _looperThread.getName() + ", id = " + _looperThread.getId());
		
		try
		{
			_looperThread.join();
		}
		catch(Exception e)
		{}
	}
	
	public static void doTest2()
	{
		Looper _looper = Looper.newLooper();
		
		LooperThread _looperThread = new LooperThread();
		_looperThread.setLooper(_looper);
		
		TestMessageO tm = new TestMessageO();
		tm.setLooper(_looper);
		_looperThread.start();
		
		TestMessageO.testMessage(tm);
		
		System.out.println("Thread: " + _looperThread.getName() + ", id = " + _looperThread.getId());
		
		try
		{
			_looperThread.join();
		}
		catch(Exception e)
		{}
	}
	
	public static void doTest3()
	{
		Looper _looper = Looper.newLooper();
		
		LooperThread _looperThread = new LooperThread();
		_looperThread.setLooper(_looper);
		
		TestMessageO tm = new TestMessageO();
		tm.setLooper(_looper);
		_looperThread.start();
		
		TestMessageO.testMessage(tm);
		
		System.out.println("Thread: " + _looperThread.getName() + ", id = " + _looperThread.getId());
		
		try
		{
			_looperThread.join();
		}
		catch(Exception e)
		{}
	}
	
	public static void doTest4()
	{
		LooperThread _looperThread = LooperThread.newInstance(false);
		Looper _looper = _looperThread.getLooper();
		
		TestMessageO tm = new TestMessageO();
		tm.setLooper(_looper);
		
		TestMessageO.testMessage(tm);
		
		System.out.println("Thread: " + _looperThread.getName() + ", id = " + _looperThread.getId());
		
		_looperThread.runForEver(true);
	}
	
	public static void doTest5()
	{
		LooperThread _looperThread = LooperThread.newInstance();
		Looper _looper = _looperThread.getLooper();
		
		TestMessageO tm = new TestMessageO();
		tm.setLooper(_looper);
		_looperThread.runForEver();
		
		TestMessageO.testMessage(tm);
		
		System.out.println("Thread: " + _looperThread.getName() + ", id = " + _looperThread.getId());
		
		try
		{
			_looperThread.join();
		}
		catch(Exception e)
		{}
	}
	
	public static void doTest6()
	{
		Looper _looper = Looper.newMainLooper();
		Thread _looperThread = _looper.getThread();
		
		TestMessageO tm = new TestMessageO();
		tm.setLooper(_looper);
		
		TestMessageO.testMessage(tm);
		
		System.out.println("Thread: " + _looperThread.getName() + ", id = " + _looperThread.getId());
		
		_looper.loop();
	}
	
	public static void doTest7()
	{
		LooperThread _looperThread = LooperThread.newInstance();
		Looper _looper = _looperThread.getLooper();
		
		TestMessageO tm = new TestMessageO();
		tm.setLooper(_looper);
		_looperThread.runForEver();
		
		TestMessageO.testMessage(tm);
		
		System.out.println("Thread: " + _looperThread.getName() + ", id = " + _looperThread.getId());
		
		try
		{
			_looperThread.join();
		}
		catch(Exception e)
		{}
	}
	
	public static void main(String[] args)
	{
		//TestMessageO.doTest1();
		//TestMessageO.doTest2();
		//TestMessageO.doTest3();
		//TestMessageO.doTest4();
		//TestMessageO.doTest5();
		TestMessageO.doTest6();
		//TestMessageO.doTest7();
		System.out.println("exit");
	}
}
