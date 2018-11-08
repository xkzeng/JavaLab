/**
 * LooperThread class,it trigger and run the message queue;
 */

package com.message;

import com.wrapper.Print;

public class LooperThread extends Thread
{
	/** Message Looper that the thread will start and run it */
  private Looper mLooper = null;
	
	/** Default constructor */
	public LooperThread()
	{
		super("LooperThread");
		this.mLooper = null;
	}

	/** Constructor with parameter _looper */
	public LooperThread(Looper _looper)
	{
		super("LooperThread");
		this.mLooper = _looper;
		this.mLooper.setThread(this);
	}
	
	/** 为当前线程指定一个工作Looper;一个线程只能拥有一个工作Looper; */
	public synchronized void setLooper(Looper _looper)
	{
		if(_looper == null)
		{
			Print.error("invalid looper!");
			return;
		}
		
		if(this.mLooper == null)
		{
			this.mLooper = _looper;
			this.mLooper.setThread(this);
		}
  	else
  	{
  		Print.warn("一个线程只能拥有一个工作Looper!当前线程已经拥有一个工作,不能再次指定;");
  	}
	}
	
	/**
	 * This method returns the Looper associated with this thread;
	 * 
	 * @return The looper.
	 */
	public synchronized Looper getLooper()
	{
		return this.mLooper;
	}
	
	/**
	 * Initialize and start the thread instance, and return the looper associated with it;
	 * @return The looper.
	 */
	public static Looper newInstance(boolean quitAllowed)
	{
    Looper _looper = Looper.newLooper(quitAllowed); //新建一个工作Looper实例;
    LooperThread _thread = new LooperThread();      //新建一个Looper线程;
    _thread.setLooper(_looper);          //为新建的线程指定一个工作Looper;
    _looper.setThread(_thread);          //指定新建的Looper实例所属的线程;
    _thread.setDaemon(true);
    _thread.start();                     //启动线程;
    return _looper;                     //返回新建的Looper实例;
	}
	
	/**
	 * Call back method that can be explicitly overridden if needed to execute some
	 * setup before Looper loops.
	 */
	protected void onPrepare()
	{
		
	}
	
	/**
	 * Call back method that can be explicitly overridden if needed to execute some
	 * cleanup after Looper loops.
	 */
	protected void onCleanup()
	{
		
	}
	
	/** Thread method */
	@Override
	public final void run()
	{
		Print.info("LooperThread start");
//		this.mLooper.prepare();
//		this.onPrepare();
		this.mLooper.loop();
//		this.onCleanup();
		Print.warn("LooperThread exit");
	}
}
