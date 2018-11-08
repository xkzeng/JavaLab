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
		this.setLooper(_looper);
	}
	
	/** 为当前线程指定一个工作Looper;一个线程只能拥有一个工作Looper; */
	public synchronized void setLooper(Looper _looper)
	{
		if(this.mLooper == null)
		{
			this.mLooper = _looper;
			if(this.mLooper != null)
			{
				this.mLooper.setThread(this);
			}
			else
			{
				Print.error("invalid looper!");
			}
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
	 * @return The thread.
	 */
	public static LooperThread newInstance()
	{
    Looper _looper = Looper.newLooper();       //新建一个工作Looper实例;
    LooperThread _thread = new LooperThread(); //新建一个Looper线程;
    _thread.setLooper(_looper);                //为新建的线程指定一个工作Looper;
//    _looper.setThread(_thread);          //指定新建的Looper实例所属的线程;
//    _thread.setDaemon(true);
//    _thread.start();                     //启动线程;
    return _thread;                     //返回新建的_thread实例;
	}
	
	/**
	 * Initialize and start the thread instance, and return the looper associated with it;
	 * @param quitAllowed <i>{@code true}</i> if allowed this instance to exit, <i>{@code false}</i> otherwise;
	 * @return The looper.
	 */
	public static LooperThread newInstance(boolean quitAllowed)
	{
    Looper _looper = Looper.newLooper(quitAllowed);   //新建一个工作Looper实例;
    LooperThread _thread = new LooperThread(_looper); //新建一个Looper线程;
//    _thread.setLooper(_looper);          //为新建的线程指定一个工作Looper;
//    _looper.setThread(_thread);          //指定新建的Looper实例所属的线程;
//    _thread.setDaemon(true);
//    _thread.start();                     //启动线程;
    return _thread;                     //返回新建的Looper实例;
	}
	
	/**
	 * 在内存中启动消息循环机制;默认已独立的DAEMON线程方式启动;
	 */
	public void runForEver()
	{
		this.setDaemon(true); //设为DAEMON线程;
		this.start();         //启动线程;
		Print.debug("run as alone thread");
	}
	
	/**
	 * 在内存中启动消息循环机制;
	 */
	public void runForEver(boolean isBlocked)
	{
		if(isBlocked == true)
		{
			Print.debug("run as main looper");
			this.run(); //直接在宿主线程中调用run()方法;
			Print.warn("Message loop unexpectedly exited!");
		}
		else
		{
			this.runForEver();
		}
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
