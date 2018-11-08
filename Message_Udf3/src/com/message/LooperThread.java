/**
 * LooperThread class,it trigger and run the message queue;
 */

package com.message;

import com.wrapper.Print;

public class LooperThread extends Thread
{
	/** Message Looper that the thread will start and run it */
	Looper mLooper = null;
	
	/** Default constructor */
	public LooperThread()
	{
		super("LooperThread");
		this.mLooper = Looper.newLooper();
		this.mLooper.setThread(this);
	}

	/** Constructor with parameter _looper */
	public LooperThread(Looper _looper)
	{
		super("LooperThread");
		this.mLooper = _looper;
		this.mLooper.setThread(this);
	}
	
	/**
	 * Initialize and start the thread instance, and return the looper associated with it;
	 * @return The looper.
	 */
	public static Looper newInstance()
	{
		LooperThread _looperThread = new LooperThread();
		_looperThread.start();
		
		Looper _looper = _looperThread.getLooper();
		if(_looper == null)
		{
			throw new RuntimeException("looper is null");
		}
		return _looper;
	}
	
	/**
	 * This method returns the Looper associated with this thread;
	 * 
	 * @return The looper.
	 */
	public Looper looper()
	{
		return this.mLooper;
	}
	
	/**
	 * This method returns the Looper associated with this thread;
	 * 
	 * @return The looper.
	 */
	public Looper getLooper()
	{
		return this.mLooper;
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
		this.mLooper.prepare();
		this.onPrepare();
		this.mLooper.loop();
		this.onCleanup();
		Print.warn("LooperThread exit");
	}
}
