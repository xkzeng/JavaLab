
import com.wrapper.Print;
import com.message.Message;
import com.message.MessageHandler;
import com.message.MsgApp;

public class AppTest2
{
	private MessageHandler mHandler = new MessageHandler()
	{
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
			Print.info("MessageHandler: handle message " + msg._what + " ......");
			return true;
		}
		
		@Override
		public void messageCallback(Message msg, boolean result)
		{
			Print.info("MessageHandler: message " + msg._what + " have been handled:" + result);
		}
	};
	
	private MsgApp mApp = new MsgApp()
	{
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
			Print.info("MsgApp: handle message " + msg._what + " ......");
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
			Print.info("MsgApp: message " + msg._what + " have been handled:" + result);
		}
	};
	
	public AppTest2()
	{
		Print.info("call AppTest2()");
	}
	
	public void test1()
	{
		this.mHandler.runForEver();
		
		try
  	{
  		Thread.sleep(2000);
  		this.mHandler.sendMessage(10);
  		Thread.sleep(2000);
  		this.mHandler.sendMessage(20, true);
  		
  		Thread.sleep(2000);
  		this.mHandler.sendMessage(-1, true);
  		this.mHandler.join();
  	}
  	catch(Exception e)
  	{}
	}
	
	public void test2()
	{
		this.mHandler.runForEver();
		Print.info("mHandler = %d", mHandler.hashCode());
		this.mApp.runForEver();
		
		try
  	{
  		Thread.sleep(1000);
  		this.mHandler.sendMessage(10);
  		
  		Thread.sleep(1000);
  		this.mApp.sendMessage(100);
  		
  		
  		Thread.sleep(1000);
  		this.mHandler.sendMessage(20, true);
  		
  		Thread.sleep(1000);
  		this.mApp.sendMessage(200);
  		
  		Thread.sleep(1000);
  		this.mHandler.sendMessage(this.mApp, 300, true);
  		
  		Thread.sleep(1000);
  		this.mApp.sendMessage(this.mHandler, 400, true);
  		
  		Thread.sleep(1000);
  		this.mHandler.sendMessage(null, 500, true);
  		
  		Thread.sleep(1000);
  		MsgApp.sendMessage(this.mHandler, this.mApp, 600, true);
  		
  		Thread.sleep(1000);
  		MsgApp.sendMessage(this.mApp, this.mHandler, 700, true);
  		
  		this.mHandler.join();
  	}
  	catch(Exception e)
  	{}
	}
	
	public static void main(String[] args)
	{
		AppTest2 app = new AppTest2();
  	//app.test1();
		app.test2();
  	System.out.println("exit");
	}
}
