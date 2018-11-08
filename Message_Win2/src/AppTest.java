
import com.wrapper.Print;
import com.message.Message;
import com.message.MsgApp;

public class AppTest extends MsgApp
{
	public AppTest()
	{
		Print.debug("call AppTest()");
	}
	
	public AppTest(boolean isAlone, boolean quitAllowed)
	{
		super(isAlone, quitAllowed);
		Print.debug("call AppTest(boolean isAlone, boolean quitAllowed)");
	}
	
	/**
	 * Filter Message;<BR>
	 * Message which is filtered will not be handled, that means will not call handleMessage() for this message;<BR>
	 * Maybe subclasses implement this method or not;<BR>
	 * This method is optional;
	 * @param msg Message instance which will be filtered;
	 * @return <i>{@code true}</i> if the message is filtered; <i>{@code false}</i> otherwise;;
	 */
	@Override
	public boolean filterMessage(Message msg)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Handle message;<BR>
	 * Subclasses must implement this method to handle messages that received.<BR>
	 * @param msg Instance of Message that will be handled;
	 * @return <i>{@code true}</i> if this message have been handled, <i>{@code false}</i> otherwise;
	 */
	@Override
	public boolean handleMessage(Message msg)
	{
		Print.debug("handle message " + msg._what + " ......");
		return true;
	}
	
	/**
	 * Message callback;<BR>
	 * Maybe subclasses implement this method or not.<BR>
	 * This method is optional;
	 * @param msg Instance of Message that have been handled;
	 * @param result <i>{@code true}</i> if this message have been handled successfully, <i>{@code false}</i> otherwise;
	 * @return <i>{@code void}</i>
	 */
	@Override
	public void messageCallback(Message msg, boolean result)
	{
		Print.debug("message " + msg._what + " have been handled:" + result);
	}
	
	public static void test1()
	{
		AppTest app = new AppTest();
		app.runForEver();
		
		new Thread()
		{
			@Override
			public final void run()
			{
				try
		  	{
		  		Thread.sleep(1000);
		  		app.sendMessage(10);
		  		Thread.sleep(1000);
		  		app.sendMessage(20, true);
		  		Thread.sleep(1000);
		  		app.sendMessage(30, true);
		  		Thread.sleep(1000);
		  		app.sendMessage(0, true);
		  	}
		  	catch(Exception e)
		  	{}
			}
		}.start();
		
		try
		{
			if(app.isAlone())
				app.join();
		}
		catch(Exception e)
  	{}
	}
	
	public static void test2()
	{
		AppTest app = new AppTest();
		app.start();
		
		try
  	{
  		Thread.sleep(2000);
  		app.sendMessage(10);
  		Thread.sleep(2000);
  		app.sendMessage(20, true);
  		Thread.sleep(2000);
  		app.sendMessage(30, true);
  		app.join();
  	}
  	catch(Exception e)
  	{}
	}
	
	public static void test3()
	{
		AppTest app = new AppTest();
		app.start();
		
		try
  	{
  		Thread.sleep(2000);
  		app.sendMessage(10);
  		Thread.sleep(2000);
  		app.sendMessage(20, true);
  		Thread.sleep(2000);
  		app.sendMessage(30, true);
  		app.join();
  	}
  	catch(Exception e)
  	{}
	}
	
	public static void main(String[] args)
	{
		AppTest.test1();
		//AppTest.test2();
  	System.out.println("exit");
	}
}
