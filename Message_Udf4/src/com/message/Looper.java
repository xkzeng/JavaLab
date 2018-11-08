/**
 * Looper class is a message lopper.It can fetch message from message queue and handle message or command asynchronously in background;
 */
package com.message;

import java.lang.Thread;
import java.lang.RuntimeException;
import java.lang.IllegalStateException;
import com.wrapper.Print;

public final class Looper implements IMessageDispatcher
{
	/** Message queue which store messages */
  private MessageQueue mQueue = null;
  
  /** Thread which associate with this looper */
  private Thread       mThread = null;
  
  /** Main looper for application */
  private static Looper stMainLooper = null;
  
  /**
	 * Whether this message looper allowed to quit;<BR>
	 * <i>{@code true}</i> if this message looper allowed to quit, <i>{@code false}</i> otherwise;
	 */
	private final boolean mQuitAllowed;
  
	/**
	 * Constructor with parameters;
	 * @param quitAllowed <i>{@code true}</i> if allowed this instance to exit, <i>{@code false}</i> otherwise;
	 */
  private Looper(boolean quitAllowed)
	{
  	this.mQuitAllowed = quitAllowed;
		this.mQueue = new MessageQueue();
	}
  
  /**
	 * Check whether this message looper allowed to quit
	 * @param <i>{@code void}</i>
	 * @return <i>{@code true}</i> if this message looper allowed to quit; <i>{@code false}</i> otherwise;
	 */
  public synchronized boolean isQuitAllowed()
  {
  	return this.mQuitAllowed;
  }
  
  /**
	 * Initialize the current thread as a looper. This gives you a chance to create
	 * handlers that then reference this looper, before actually starting the loop.
	 * Be sure to call {@link #loop()} after calling this method, and end it by
	 * calling {@link #quit()}.
	 */
  public static Looper newLooper(boolean quitAllowed)
	{
		return new Looper(quitAllowed);
	}
  
  /**
	 * Initialize the current thread as a looper. This gives you a chance to create
	 * handlers that then reference this looper, before actually starting the loop.
	 * Be sure to call {@link #loop()} after calling this method, and end it by
	 * calling {@link #quit()}.
	 */
  public static Looper newLooper()
	{
		return Looper.newLooper(true);
	}
  
  /**
	 * Initialize and start the thread instance, and return the looper associated with it;
	 * @return The looper.
	 */
  public static Looper newInstance()
	{
    Looper _looper = Looper.newLooper(true);   //新建一个工作Looper实例;
    LooperThread _thread = new LooperThread(); //新建一个Looper线程;
    _thread.setLooper(_looper);          //为新建的线程指定一个工作Looper;
//    _looper.setThread(_thread);          //指定新建的Looper实例所属的线程;
    return _looper;                     //返回新建的Looper实例;
	}
  
  /**
	 * Initialize and start the thread instance, and return the looper associated with it;
	 * @param quitAllowed <i>{@code true}</i> if allowed this instance to exit, <i>{@code false}</i> otherwise;
	 * @return The looper.
	 */
	public static Looper newInstance(boolean quitAllowed)
	{
		Looper _looper = Looper.newLooper(quitAllowed);   //新建一个工作Looper实例;
    LooperThread _thread = new LooperThread(_looper); //新建一个Looper线程;
//    _thread.setLooper(_looper);          //为新建的线程指定一个工作Looper;
//    _looper.setThread(_thread);          //指定新建的Looper实例所属的线程;
    return _looper;                     //返回新建的Looper实例;
	}
	
	/**
	 * 在内存中启动消息循环机制;默认以独立的DAEMON线程方式启动;
	 */
	public void runForEver()
	{
		this.mThread.setDaemon(true); //设为DAEMON线程;
		this.mThread.start();         //启动线程;
		Print.info("run as alone thread");
	}
	
	/**
	 * Initialize the current thread as a looper, marking it as an application's
	 * main looper. The main looper for your application is created by the Android
	 * environment, so you should never need to call this function yourself. See
	 * also: {@link #prepare()}
	 */
  public static Looper newMainLooper()
  {
  	synchronized(Looper.class)
		{
			if(Looper.stMainLooper != null)
			{
				throw new IllegalStateException("The main Looper has already been prepared.");
			}
			Looper.stMainLooper = Looper.newLooper(false);
		}
		return Looper.stMainLooper;
	}
	
	/**
	 * Returns the application's main looper, which lives in the main thread of the
	 * application.
	 */
	public static Looper getMainLooper()
	{
		synchronized(Looper.class)
		{
			return Looper.stMainLooper;
		}
	}
	
	/**
	 * 创建一个Looper实例,并把它与主线程关联在一起,然后返回这个新建的Looper实例;
	 * @return Looper实例;
	 */
	public static Looper newMainInstance()
	{
		Thread _mainThread = Thread.currentThread(); //获取当前主线程的实例;
		Looper _looper = Looper.newMainLooper();     //新建一个工作于主线程中的Looper实例;
		_looper.setThread(_mainThread);              //把主线程与主Looper绑定在一起;
    return _looper;                             //返回新建的主Looper实例;
	}
	
	/**
	 * 直接为主线程运行消息循环;它会阻塞主线程;
	 */
	public void runMainForEver()
	{
		Print.info("run as main looper");
		this.loop(); //直接在主线程中调用loop()方法
		Print.warn("Main thread loop unexpectedly exited!");
	}
	
	/**
	 * Return the queue of the current Looper;
	 */
  public synchronized MessageQueue getQueue()
  {
  	return this.mQueue;
  }
  
  /**
   * Associated the thread to this looper;
   * @param looperThread Instance of Thread class;
   */
  public synchronized void setThread(Thread _thread)
	{
  	Print.info("call setThread");
  	if(this.mThread == null)
		{
  		this.mThread = _thread;
  		if(this.mThread == null)
  		{
  			Print.error("thread is invalid!");
  		}
		}
  	else
  	{
  		Print.warn("一个Looper只能绑定到一个线程上!当前Looper已经绑定到一个指定的线程上,不能再次绑定;");
  	}
	}
	
	/**
	 * Return the Thread associated with this Looper.
	 */
	public synchronized Thread getThread()
	{
		return this.mThread;
	}
	
	/**
	 * Returns true if the current thread is this looper's thread.
	 * 
	 * @hide
	 */
	public boolean isCurrentThread()
	{
		return Thread.currentThread() == this.mThread;
	}
	
	/**
	 * Dispatch message;<BR>
	 * Subclasses can not implement this method to dispatch messages that received.<BR>
	 * @param msg Instance of Message that will be dispatched;
	 * @return <i>{@code true}</i> if this message have been dispatched, <i>{@code false}</i> otherwise;
	 */
	@Override
	public final boolean dispatchMessage(Message msg)
	{
		// TODO Auto-generated method stub
		boolean result = false;
		
		if(msg._target == null)
		{
			Print.error("target is null, can not handle this message! what = %d", msg._what);
			return false;
		}
		
		//STEP1:处理消息;
		if(msg._self != null) //消息本身具有处理该消息的能力;
		{
			result = msg._target.handleSelfMessage(msg); //触发消息自身的处理机制;
		}
		else
		{
			result = msg._target.handleMessage(msg);     //交给对应的消息处理器处理;
		}
		
		//STEP2:执行回调;
		if(msg.isNeedCallback())
		{
		  //执行消息发送者的回调操作:
			if(msg._sender != null)
			{
				msg._sender.messageCallback(msg, result);
			}
			else
			{
				Print.error("sender is null, can not do it's callback!");
			}
		}
		return result;
	}
	
	/**
	 * Run the message queue in this thread. Be sure to call {@link #quit()} to end
	 * the loop.
	 */
	@SuppressWarnings("unused")
	public void loop()
	{
		final MessageQueue queue = this.mQueue;
		
		Message msg = null;
		while(true)
		{
			msg = queue.getMessage(); // might block
			if(msg == null)
			{
			  // No message indicates that the message queue is quitting.
				Print.error("the current looper quit");
				break;
			}
			
			if(msg._what <= 0)
			{
			  //recycle the message which cause the looper to quit;
				Message.recycle(msg);
				
				boolean quitAllowed = this.isQuitAllowed();
				if(quitAllowed == true)
				{
					//the non-main looper can be allowed to quit;
					Print.warn("the current looper quit");
					break;
				}
				
				if(quitAllowed == false)
				{
					//the main looper do not be allowed to quit;
					Print.warn("the main looper do not be allowed to quit");
					continue;
				}
			}
			
		  //Dispatch message to target;
			this.dispatchMessage(msg);
			
			//Recycle this message;
			//msg.recycleUnchecked();
			Message.recycle(msg);
		}
		
		//recycle all messages that do not be handled;
		//... add code later;
	}

	public void dump(String prefix)
	{
	}

	public String toString()
	{
		return "Looper (" + mThread.getName() + ", tid " + mThread.getId() + ") {" + Integer.toHexString(System.identityHashCode(this)) + "}";
	}
}
