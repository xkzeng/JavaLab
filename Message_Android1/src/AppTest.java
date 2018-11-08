
import com.wrapper.Print;
import com.message.Message;
import com.message.MsgApp;

public class AppTest extends MsgApp
{
	public AppTest()
	{
		Print.info("call AppTest()");
	}
	
	@Override
	public boolean filterMessage(Message msg)
	{
		// TODO Auto-generated method stub
		Print.warn("AppTest.filterMessage() is not be implemeted");
		return false;
	}
	
	@Override
	public boolean handleMessage(Message msg)
	{
		Print.info("handle message " + msg._what + " ......");
		return true;
	}
	
	@Override
	public void messageCallback(Message msg, boolean result)
	{
		Print.info("message " + msg._what + " have been handled:" + result);
	}
	
	public static void main(String[] args)
	{
		AppTest app = new AppTest();
  	
  	Thread _looperThread = app.getLooper().getThread();
		
		System.out.println("Thread: " + _looperThread.getName() + ", id = " + _looperThread.getId());
		
  	try
  	{
  		Thread.sleep(1000);
  		app.sendMessage(10);
  		Thread.sleep(1000);
  		app.sendMessage(20, true);
  		_looperThread.join();
  	}
  	catch(Exception e)
  	{}
  	System.out.println("exit");
	}
}
