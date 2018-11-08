/**
 * LooperThread class,it trigger and run the message queue;
 */

package com.message;

import java.lang.RuntimeException;

import com.wrapper.Print;

public class LooperThread extends Thread
{
	/** Message Looper that the thread will start and run it */
	private Looper mLooper = null;
	
	/** Default constructor */
	public LooperThread()
	{
		super("LooperThread");
	}

	/** Constructor with parameter _looper */
	public LooperThread(Looper _looper)
	{
		super("LooperThread");
		this.mLooper = _looper;
	}
	
	/**
	 * This method returns the Looper associated with this thread. If this thread
	 * not been started or for any reason is isAlive() returns false, this method
	 * will return null. If this thread has been started, this method will block
	 * until the looper has been initialized.
	 * 
	 * @return The looper.
	 */
	public Looper looper()
	{
		if(this.isAlive() == false)
		{
			return null;
		}
		
		synchronized(this)
		{
			if(this.mLooper == null)
			{
				try
				{
					Print.debug("looper is null, waiting ...");
					this.wait();
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
		return this.mLooper;
	}
	
	/**
	 * This method returns the Looper associated with this thread. If this thread
	 * not been started or for any reason is isAlive() returns false, this method
	 * will return null. If this thread has been started, this method will block
	 * until the looper has been initialized.
	 * 
	 * @return The looper.
	 */
	public Looper getLooper()
	{
		if(this.isAlive() == false)
		{
			return null;
		}

		// If the thread has been started, wait until the looper has been created.
		synchronized(this)
		{
			//while((this.isAlive() == true) && (this.mLooper == null))
			while((this.isAlive() == true) && (this.mLooper == null))
			{
				try
				{
					Print.debug("looper is null, waiting ...");
					this.wait();
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
		return this.mLooper;
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
		Looper.prepare();
		synchronized(this)
		{
			this.mLooper = Looper.myLooper();
			if(this.mLooper != null)
			{
				Print.debug("looper is prepared ok, notify all ...");
				this.notifyAll();
			}
		}
		this.onPrepare();
		Looper.loop();
		this.onCleanup();
	}
}
