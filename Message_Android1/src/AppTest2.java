
import com.message.Message;
import com.message.MessageHandler;
import com.wrapper.Print;

public class AppTest2
{
	private MessageHandler mHandler = new MessageHandler()
	{
		@Override
		public boolean filterMessage(Message msg)
		{
			// TODO Auto-generated method stub
			Print.warn("AppTest2.filterMessage() is not be implemeted");
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
	};
	
	public AppTest2()
	{
		Print.info("call AppTest2()");
	}
	
	public void test1()
	{
		Thread _looperThread = this.mHandler.getLooper().getThread();
		
		System.out.println("Thread: " + _looperThread.getName() + ", id = " + _looperThread.getId());
		
		try
  	{
  		Thread.sleep(1000);
  		this.mHandler.sendMessage(10);
  		Thread.sleep(1000);
  		this.mHandler.sendMessage(20, true);
  		_looperThread.join();
  	}
  	catch(Exception e)
  	{}
	}
	
	public static void main(String[] args)
	{
		AppTest2 app = new AppTest2();
  	app.test1();
  	System.out.println("exit");
	}
}
