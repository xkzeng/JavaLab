package com.message;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.lang.InterruptedException;
import com.wrapper.Print;

public final class MessageQueue
{
	private Message mHead = null;
	private Message mTail = null;
	private int     mSize = 0;
	private Lock      mLock = null;
	private Condition mCond = null;
	
	public MessageQueue()
	{
		this.mHead = null;
		this.mTail = null;
		this.mSize = 0;
		this.mLock = new ReentrantLock();
		this.mCond = this.mLock.newCondition();
	}
	
	public int length()
	{
		this.mLock.lock();
		int size = this.mSize;
		this.mLock.unlock();
		return size;
	}
	
	private boolean empty()
	{
		boolean result = false;
		
		if((this.mSize <= 0) || (this.mHead == null) || (this.mTail == null))
		{
			result = true;
		}
		else
		{
			result = false;
		}
		
		return result;
	}
	
	private boolean is_empty()
	{
		boolean result = false;
		
		this.mLock.lock();
		result = this.empty();
		this.mLock.unlock();
		
		return result;
	}
	
	public Message get_tail()
	{
		Message msg = null;
		this.mLock.lock();
		try
		{
			if(this.empty())
			{
				Print.debug("no message, waiting......");
				this.mCond.await();
				Print.debug("message comes.");
			}
			
			msg = this.mTail;
			this.mTail = msg.prev;
			
			if(this.mTail != null) //tail do not point to the first node;
			{
				this.mTail.next = null;
			}
			else                   //tail point to the first node;
			{
				this.mHead = null;
			}
			
			msg.next = null;
			msg.prev = null;
			this.mSize--;
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		finally
		{
			this.mLock.unlock();
		}
		return msg;
	}
	
	public boolean put_tail(Message msg)
	{
		this.mLock.lock();
		
		msg.next = null;
		msg.prev = this.mTail;
		
		if(this.mTail != null)
		{
			this.mTail.next = msg;
		}
		
		this.mTail = msg;
		
		if(this.mHead == null)
		{
			this.mHead = msg;
			Print.debug("set the head of queue");
		}
		
		this.mSize++;
		
		if(this.empty() == false)
		{
			this.mCond.signal();
		}
		this.mLock.unlock();
		return true;
	}
	
	public Message get_head()
	{
		Message msg = null;
		this.mLock.lock();
		try
		{
			if(this.empty())
			{
				Print.debug("no message, waiting......");
				this.mCond.await();
				Print.debug("message comes.");
			}
			
			msg = this.mHead;
			this.mHead = msg.next;
			
			if(this.mHead != null) //head do not point to the first node;
			{
				this.mHead.prev = null;
			}
			else                   //head point to the first node;
			{
				this.mTail = null;
			}
			
			msg.next = null;
			msg.prev = null;
			this.mSize--;
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		finally
		{
			this.mLock.unlock();
		}
		return msg;
	}
	
	public boolean put_head(Message msg)
	{
		this.mLock.lock();
		
		msg.prev = null;
		msg.next = this.mHead;
		
		if(this.mHead != null)
		{
			this.mHead.prev = msg;
		}
		
		this.mHead = msg;
		
		if(this.mTail == null)
		{
			this.mTail = msg;
			Print.debug("set the tail of queue");
		}
		
		this.mSize++;
		
		if(this.empty() == false)
		{
			this.mCond.signal();
		}
		
		this.mLock.unlock();
		return true;
	}

	public Message next()
	{
		return this.get_head();
	}
	
	public Message getMessage()
	{
		return this.get_head();
	}
	
	public boolean enqueueMessage(Message msg)
	{
		return this.put_tail(msg);
	}

	void dump(String prefix)
	{
	}
}
