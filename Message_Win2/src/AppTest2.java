
import com.wrapper.Print;
import com.message.Message;
import com.message.MsgApp;

public class AppTest2
{
	private MsgApp mApp1 = new MsgApp()
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
			Print.info("App1: handle message " + msg._what + " ......");
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
			Print.info("App1: message " + msg._what + " have been handled:" + result);
		}
	};
	
	private MsgApp mApp2 = new MsgApp()
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
			Print.info("App2: handle message " + msg._what + " ......");
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
			Print.info("App2: message " + msg._what + " have been handled:" + result);
		}
	};
	
	public AppTest2()
	{
		Print.info("call AppTest2()");
	}
	
	public void test1()
	{
		this.mApp1.runForEver();
		Print.info("mApp1 = %d", mApp1.hashCode());
		this.mApp2.runForEver();
		
		try
  	{
  		Thread.sleep(1000);
  		this.mApp1.sendMessage(10);
  		
  		Thread.sleep(1000);
  		this.mApp2.sendMessage(100);
  		
  		Thread.sleep(1000);
  		this.mApp1.sendMessage(20, true);
  		
  		Thread.sleep(1000);
  		this.mApp2.sendMessage(200);
  		
  		Thread.sleep(1000);
  		this.mApp1.sendMessage(this.mApp2, 300, true);
  		
  		Thread.sleep(1000);
  		this.mApp2.sendMessage(this.mApp1, 400, true);
  		
  		Thread.sleep(1000);
  		this.mApp1.sendMessage(null, 500, true);
  		
  		Thread.sleep(1000);
  		MsgApp.sendMessage(this.mApp1, this.mApp2, 600, true);
  		
  		Thread.sleep(1000);
  		MsgApp.sendMessage(this.mApp2, this.mApp1, 700, true);
  		
  		this.mApp1.join();
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
