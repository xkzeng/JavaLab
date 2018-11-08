/**
 * LooperThread类,它是对Looper实例的封装,并调度Looper实例运行消息队列;
 */

package com.message;

import com.wrapper.Print;

public class LooperThread extends Thread
{
	/** LooperThread类内置组合的Looper实例 */
  private Looper mLooper = null;
	
	/** 默认构造函数 */
	public LooperThread()
	{
		super("LooperThread");
		this.mLooper = null;
	}

	/** 带有一个Looper参数的单参构造函数 */
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
	 * 这个方法返回当前线程所内置组合的Looper实例;
	 * 
	 * @return Looper实例;
	 */
	public synchronized Looper getLooper()
	{
		return this.mLooper;
	}
	
	/**
	 * 执行Looper实例的loop()方法之前可以回调的方法;子类可以覆盖其实现;
	 */
	protected void onPrepare()
	{
		
	}
	
	/**
	 * Looper实例的loop()方法返回之后可以回调的方法;子类可以覆盖其实现;
	 */
	protected void onCleanup()
	{
		
	}
	
	/** 覆盖父类Thread的线程方法 */
	@Override
	public final void run()
	{
		Print.info("LooperThread start");
		this.onPrepare();
		this.mLooper.loop();
		this.onCleanup();
		Print.warn("LooperThread exit");
	}
}
