
import java.lang.Runnable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.message.Message;
import com.wrapper.Print;
import com.message.Looper;
import com.message.LooperThread;
import com.message.MessageHandler;

public class TestMessageO extends MessageHandler implements Runnable
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
	public boolean filterMessage(Message msg)
	{
		// TODO Auto-generated method stub
		Print.warn("MessageHandler.filterMessage() is not be implemeted");
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
	
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
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
			
			this.sendMessage(_what1);
			_what1++;
			
			this.sendMessage(_what2, true);
			_what2++;
		}
	}
	
	public void startMessageTask()
	{
		//ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.schedule(this, 0, TimeUnit.SECONDS);
		//service.scheduleAtFixedRate(this, 2, 1, TimeUnit.SECONDS);
	}
	
	public static void testMessage(TestMessageO app)
	{
		app.startMessageTask();
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
		Looper _looper = Looper.newInstance();
		Thread _looperThread = _looper.getThread();
		
		TestMessageO tm = new TestMessageO();
		tm.setLooper(_looper);
		_looper.runForEver();
		
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
		Looper _looper = Looper.newMainInstance();
		Thread _looperThread = _looper.getThread();
		
		TestMessageO tm = new TestMessageO();
		tm.setLooper(_looper);
		
		TestMessageO.testMessage(tm);
		
		System.out.println("Thread: " + _looperThread.getName() + ", id = " + _looperThread.getId());
		
		_looper.runMainForEver();
	}
	
	public static void main(String[] args)
	{
		//TestMessageO.doTest1();
		//TestMessageO.doTest2();
		//TestMessageO.doTest3();
		TestMessageO.doTest4();
		System.out.println("exit");
	}
}
