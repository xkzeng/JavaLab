/**
 * Looper class is a message lopper.It can fetch message from message queue and handle message or command asynchronously in background;
 */
package com.message;

import java.lang.Thread;
import java.lang.RuntimeException;
import java.lang.IllegalStateException;
import com.wrapper.Print;

public final class Looper
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
  public static Looper newLooper()
	{
		return Looper.newLooper(true);
	}
  
	private static Looper newLooper(boolean quitAllowed)
	{
		return new Looper(quitAllowed);
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
	
	public void prepare()
	{
		this.mThread = Thread.currentThread(); //Looper <-> Thread;
	}
	
	/**
	 * Return the queue of the current Looper;
	 */
  public MessageQueue getQueue()
  {
  	return this.mQueue;
  }
  
  /**
   * Associated the thread to this looper;
   * @param looperThread Instance of Thread class;
   */
	protected void setThread(Thread looperThread)
	{
		this.mThread = looperThread;
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
			msg._target.dispatchMessage(msg);
			
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
