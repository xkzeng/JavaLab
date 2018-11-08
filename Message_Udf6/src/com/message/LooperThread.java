/**
 * LooperThread��,���Ƕ�Looperʵ���ķ�װ,������Looperʵ��������Ϣ����;
 */

package com.message;

import com.wrapper.Print;

public class LooperThread extends Thread
{
	/** LooperThread��������ϵ�Looperʵ�� */
  private Looper mLooper = null;
	
	/** Ĭ�Ϲ��캯�� */
	public LooperThread()
	{
		super("LooperThread");
		this.mLooper = null;
	}

	/** ����һ��Looper�����ĵ��ι��캯�� */
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
	 * ����������ص�ǰ�߳���������ϵ�Looperʵ��;
	 * 
	 * @return Looperʵ��;
	 */
	public synchronized Looper getLooper()
	{
		return this.mLooper;
	}
	
	/**
	 * ִ��Looperʵ����loop()����֮ǰ���Իص��ķ���;������Ը�����ʵ��;
	 */
	protected void onPrepare()
	{
		
	}
	
	/**
	 * Looperʵ����loop()��������֮����Իص��ķ���;������Ը�����ʵ��;
	 */
	protected void onCleanup()
	{
		
	}
	
	/** ���Ǹ���Thread���̷߳��� */
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
