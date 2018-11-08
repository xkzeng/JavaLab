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
	
	/** Ϊ��ǰ�߳�ָ��һ������Looper;һ���߳�ֻ��ӵ��һ������Looper; */
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
  		Print.warn("һ���߳�ֻ��ӵ��һ������Looper!��ǰ�߳��Ѿ�ӵ��һ������,�����ٴ�ָ��;");
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
    Looper _looper = Looper.newLooper();       //�½�һ������Looperʵ��;
    LooperThread _thread = new LooperThread(); //�½�һ��Looper�߳�;
    _thread.setLooper(_looper);                //Ϊ�½����߳�ָ��һ������Looper;
//    _looper.setThread(_thread);          //ָ���½���Looperʵ���������߳�;
//    _thread.setDaemon(true);
//    _thread.start();                     //�����߳�;
    return _thread;                     //�����½���_threadʵ��;
	}
	
	/**
	 * Initialize and start the thread instance, and return the looper associated with it;
	 * @param quitAllowed <i>{@code true}</i> if allowed this instance to exit, <i>{@code false}</i> otherwise;
	 * @return The looper.
	 */
	public static LooperThread newInstance(boolean quitAllowed)
	{
    Looper _looper = Looper.newLooper(quitAllowed);   //�½�һ������Looperʵ��;
    LooperThread _thread = new LooperThread(_looper); //�½�һ��Looper�߳�;
//    _thread.setLooper(_looper);          //Ϊ�½����߳�ָ��һ������Looper;
//    _looper.setThread(_thread);          //ָ���½���Looperʵ���������߳�;
//    _thread.setDaemon(true);
//    _thread.start();                     //�����߳�;
    return _thread;                     //�����½���Looperʵ��;
	}
	
	/**
	 * ���ڴ���������Ϣѭ������;Ĭ���Ѷ�����DAEMON�̷߳�ʽ����;
	 */
	public void runForEver()
	{
		this.setDaemon(true); //��ΪDAEMON�߳�;
		this.start();         //�����߳�;
		Print.debug("run as alone thread");
	}
	
	/**
	 * ���ڴ���������Ϣѭ������;
	 */
	public void runForEver(boolean isBlocked)
	{
		if(isBlocked == true)
		{
			Print.debug("run as main looper");
			this.run(); //ֱ���������߳��е���run()����;
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
