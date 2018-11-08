package com.message;

import com.wrapper.Print;

public final class Message
{
	Message prev;
	Message next;
	
	/** Message code */
	public int _what;
	
	/** The first parameter with integer */
	public int _arg1;
	
	/** The second parameter with integer */
	public int _arg2;
	
	/** Other parameters of message */
	public Object _obj;
	
	/** Flags of message */
	private int _flags;

	/** Instance of MessageHandler, message is sent by it */
	MessageHandler _sender;
	
	/**
	 * If set message is in use. This flag is set when the message is enqueued and
	 * remains set while it is delivered and afterwards when it is recycled. The
	 * flag is only cleared when a new message is created or obtained since that is
	 * the only time that applications are allowed to modify the contents of the
	 * message. It is an error to attempt to enqueue or recycle a message that is
	 * already in use.
	 */
	private static final int FLAG_IN_USE = 1 << 0;

	/** If set message is asynchronous */
	private static final int FLAG_ASYNCHRONOUS = 1 << 1;
	
	private static final int FLAG_NEED_CALLBACK = 1 << 2;
	
  
	public Message()
	{
		this.clear();
	}
  
  public void clear()
  {
  	this._what = 0;
  	this._arg1 = 0;
  	this._arg2 = 0;
  	this._obj = null;
  	this._flags = 0;
  	this._sender = null;
  	this.next = null;
  	this.prev = null;
  }
  
  public boolean isNeedCallback()
	{
		return (this._flags & FLAG_NEED_CALLBACK) != 0;
	}
  
  public void setNeedCallback(boolean needCallback)
	{
		if(needCallback)
		{
			this._flags |= FLAG_NEED_CALLBACK;
		}
		else
		{
			this._flags &= ~FLAG_NEED_CALLBACK;
		}
	}
  
	public boolean isAsynchronous()
	{
		return (this._flags & FLAG_ASYNCHRONOUS) != 0;
	}
	
	public void setAsynchronous(boolean async)
	{
		if(async)
		{
			this._flags |= FLAG_ASYNCHRONOUS;
		}
		else
		{
			this._flags &= ~FLAG_ASYNCHRONOUS;
		}
	}

	private boolean isInUse()
	{
		return ((this._flags & FLAG_IN_USE) == FLAG_IN_USE);
	}

	private void markInUse()
	{
		this._flags |= FLAG_IN_USE;
	}
	
	//private static final Object	sPoolSync	= new Object();
	private static Message			  sPool;
	private static int					  sPoolSize	= 0;
	private static final int MAX_POOL_SIZE = 128;
	
	public static int capacity()
	{
		return MAX_POOL_SIZE;
	}
	
	public static int size()
	{
		return sPoolSize;
	}
	
	public synchronized static Message obtain()
	{
		Message m = null;
		//synchronized(sPoolSync)
		//{
			if(sPool != null)
			{
				m = sPool;
				sPool = m.next;
				m.next = null;
				m.prev = null;
				sPoolSize--;
				Print.debug("%d: <1>request a message from message pool, _what = %d", m.hashCode(), m._what);
				return m;
			}
			
			m = new Message();
			Print.debug("%d: <1>request a message from memory, _what = %d", m.hashCode(), m._what);
		//}
		return m;
	}
	
	public static Message obtain(Message orig)
	{
		Message m = obtain();
		m._what = orig._what;
		m._arg1 = orig._arg1;
		m._arg2 = orig._arg2;
		m._obj = orig._obj;
		m._flags = orig._flags;
		m._sender = orig._sender;
		return m;
	}
	
	public static Message obtain(MessageHandler sender)
	{
		Message m = obtain();
		m._sender = sender;
		return m;
	}
	
	public static Message obtain(MessageHandler sender, int what)
	{
		Message m = obtain();
		m._sender = sender;
		m._what = what;
		return m;
	}
	
	public static Message obtain(MessageHandler sender, int what, Object obj)
	{
		Message m = obtain();
		m._sender = sender;
		m._what = what;
		m._obj = obj;
		return m;
	}
	
	public static Message obtain(MessageHandler sender, int what, int arg1, int arg2)
	{
		Message m = obtain();
		m._sender = sender;
		m._what = what;
		m._arg1 = arg1;
		m._arg2 = arg2;
		return m;
	}
	
	public static Message obtain(MessageHandler sender, int what, int arg1, int arg2, Object obj)
	{
		Message m = obtain();
		m._sender = sender;
		m._what = what;
		m._arg1 = arg1;
		m._arg2 = arg2;
		m._obj = obj;
		return m;
	}
	
	public synchronized void recycle()
	{
		this.clear();
		
		//synchronized(sPoolSync)
		//{
			//if(sPoolSize < MAX_POOL_SIZE)
			//{
				next = sPool;
				sPool = this;
				sPoolSize++;
			//}
		//}
	}
	
	public synchronized static void recycle(Message msg)
	{
		Print.debug("%d: <7>recycle the message, what = %d", msg.hashCode(), msg._what);
		msg.clear();
		Print.debug("%d: <8>clear the message, what = %d", msg.hashCode(), msg._what);
    //synchronized(sPoolSync)
    //{
      //if(sPoolSize < MAX_POOL_SIZE)
			//{
				msg.next = sPool;
				sPool = msg;
				sPoolSize++;
      //}
    //}
	}

	public void setSender(MessageHandler sender)
	{
		this._sender = sender;
	}
	
	public MessageHandler getSender()
	{
		return this._sender;
	}

	@Override
	public String toString()
	{
		StringBuilder b = new StringBuilder();
		b.append("{ when=");

		if(this._sender != null)
		{
			b.append(" what=");
			b.append(this._what);

			if(this._arg1 != 0)
			{
				b.append(" arg1=");
				b.append(this._arg1);
			}

			if(this._arg2 != 0)
			{
				b.append(" arg2=");
				b.append(this._arg2);
			}

			if(this._obj != null)
			{
				b.append(" obj=");
				b.append(this._obj);
			}

			b.append(" sender=");
			b.append(this._sender.getClass().getName());
		}
		else
		{
			b.append(" barrier=");
			b.append(this._arg1);
		}

		b.append(" }");
		return b.toString();
	}
}
