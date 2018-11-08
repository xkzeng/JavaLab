/**
 * Looper class is a message lopper.It can fetch message from message queue and handle message or command asynchronously in background;
 */

package com.message;

import java.lang.Thread;
import java.lang.ThreadLocal;
import java.lang.RuntimeException;
import java.lang.IllegalStateException;
import com.wrapper.Print;

public final class Looper implements IMessageDispatcher
{
	/**
	 * Thread local store for a thread, it store a looper object for the current thread;
	 * sThreadLocal.get() will return null unless you've called prepare().
	 */
  private static final ThreadLocal<Looper> stTLS = new ThreadLocal<Looper>();
  
  /** Main looper for application */
  private static Looper stMainLooper = null; //guarded by Looper.class
  
  /** Message queue which store messages */
  private final MessageQueue mQueue;
  
  /** Thread which associate with this looper */
  private final Thread       mThread;
  
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
		this.mQueue = new MessageQueue();
		this.mQuitAllowed = quitAllowed;
		this.mThread = Thread.currentThread(); //Looper <-> Thread;
	}
  
  /**
	 * Check whether this message looper allowed to quit
	 * @param <i>{@code void}</i>
	 * @return <i>{@code true}</i> if this message looper allowed to quit; <i>{@code false}</i> otherwise;
	 */
  public boolean isQuitAllowed()
  {
  	return this.mQuitAllowed;
  }
  
  /**
	 * Initialize the current thread as a looper. This gives you a chance to create
	 * handlers that then reference this looper, before actually starting the loop.
	 * Be sure to call {@link #loop()} after calling this method, and end it by
	 * calling {@link #quit()}.
	 */
	public static void prepare()
	{
		Looper.prepare(true);
	}
	
	private static void prepare(boolean quitAllowed)
	{
		if(Looper.stTLS.get() != null)
		{
			throw new RuntimeException("Only one Looper may be created per thread");
		}
		Looper.stTLS.set(new Looper(quitAllowed));
	}
	
	/**
	 * Return the Looper object associated with the current thread. Returns null if
	 * the calling thread is not associated with a Looper.
	 */
	public static Looper myLooper()
	{
		return Looper.stTLS.get();
	}
	
	/**
	 * Initialize the current thread as a looper, marking it as an application's
	 * main looper. The main looper for your application is created by the Android
	 * environment, so you should never need to call this function yourself. See
	 * also: {@link #prepare()}
	 */
	public static void prepareMainLooper()
	{
		Looper.prepare(false);
		synchronized(Looper.class)
		{
			if(Looper.stMainLooper != null)
			{
				throw new IllegalStateException("The main Looper has already been prepared.");
			}
			Looper.stMainLooper = Looper.myLooper();
		}
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
	 * Return the queue of the current Looper;
	 */
  public MessageQueue getQueue()
  {
  	return this.mQueue;
  }
	
	/**
	 * Return the {@link MessageQueue} object associated with the current thread.
	 * This must be called from a thread running a Looper, or a NullPointerException
	 * will be thrown.
	 */
	public static MessageQueue myQueue()
	{
		return Looper.myLooper().getQueue();
	}
	
	/**
	 * Return the Thread associated with this Looper.
	 */
	public Thread getThread()
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
			//this.messageResult(msg, result);
			//msg._callback.messageCallback(msg, result);
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
	public static void loop()
	{
		final Looper _looper = Looper.myLooper();
		if(_looper == null)
		{
			throw new RuntimeException("No Looper; Looper.prepare() wasn't called on this thread.");
		}
		
		final MessageQueue queue = _looper.getQueue();
		
		Message msg = null;
		while(true)
		{
			msg = queue.getMessage(); // might block
			Print.debug("%d: <4>get message, what = %d", msg.hashCode(), msg._what);
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
				
				boolean quitAllowed = _looper.isQuitAllowed();
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
			_looper.dispatchMessage(msg);
			
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
