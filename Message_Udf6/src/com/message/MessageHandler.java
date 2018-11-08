/**
 * MessageHandler class is a message handler.It can handle message or command asynchronously in background;
 * You can construct an MessageHandler object with default constructor "MessageHandler()", or you can
 * construct an object with constructor "MessageHandler(Looper _looper)";
 */

package com.message;

import com.wrapper.Print;

public class MessageHandler implements IMessageFilter, IMessageHandler, IMessageCallback
{
  /** 当前消息处理器所内置组合的Looper实例 */
	private Looper mLooper;
    
	/** 当前消息处理器所内置组合的消息队列MessageQueue实例 */
	private MessageQueue mQueue;
	
  /** 默认构造函数 */
	public MessageHandler()
	{
		Print.debug("call MessageHandler()");
		this.mLooper = null;
		this.mQueue = null;
	}
	
	/**
   * 带有一个Looper参数的单参构造函数
   * @param _looper Looper类的实例,它能够从消息队列中取出并处理消息;
   */
	public MessageHandler(Looper _looper)
	{
		Print.debug("call MessageHandler(Looper)");
		this.setLooper(_looper);
	}
  
	/**
	 * 该方法指定当前消息处理器内部所组合的Looper实例;
	 * 
	 * @param _looper Looper类的实例
	 * @return void
	 */
	public synchronized void setLooper(Looper _looper)
	{
		if(this.mLooper == null)
		{
			this.mLooper = _looper;
			if(this.mLooper != null)
			{
				this.mQueue = this.mLooper.getQueue();
			}
			else
			{
				Print.error("Looper is null,this looper is unavailable");
			}
		}
		else
		{
			Print.warn("当前消息处理器已拥有一个Looper实例了,不必更改");
		}
	}
  
  /**
   * 该方法返回当前消息处理器实例内置组合的Looper实例;
   * @param 
   * @return Looper类的实例,该实例能够从消息队列中取出消息并处理;
   */
	public synchronized Looper getLooper()
	{
		return this.mLooper;
	}
	
	/**
	 * 过滤消息;<BR>
	 * 被过滤掉的消息是不会被处理的,即,不会对被过滤掉的消息调用handleMessage()方法;<BR>
	 * 该方法是可选的,子类可以实现它,也可以不实现它.<BR>
	 * @param msg 待过滤的消息Message的实例;;
	 * @return 如果消息需要被过滤掉(忽略掉),则返回<i>{@code true}</i>,否则返回<i>{@code false}</i>;;
	 */
	@Override
	public boolean filterMessage(Message msg)
	{
		// TODO Auto-generated method stub
		Print.warn("MessageHandler.filterMessage() is not be implemeted");
		return false;
	}

	/**
	 * 处理消息;<BR>
	 * 子类必须实现该方法,用于处理接收到的消息.<BR>
	 * @param msg 待处理的消息Message的实例;
	 * @return 如果消息被成功处理,则返回<i>{@code true}</i>,否则返回<i>{@code false}</i>;
	 */
	@Override
	public boolean handleMessage(Message msg)
	{
		// TODO Auto-generated method stub
		Print.warn("MessageHandler.handleMessage() is not be implemeted");
		return false;
	}
	
	/**
	 * 消息处理完成之后需要回调的方法;<BR>
	 * 该方法是可选的,子类可以实现它,也可以不实现它.<BR>
	 * @param msg 已经处理完成的消息Message的实例;
	 * @param result 如果消息被成功地处理完成,则返回<i>{@code true}</i>,否则返回<i>{@code false}</i>;
	 * @return <i>{@code void}</i>
	 */
	@Override
	public void messageCallback(Message msg, boolean result)
	{
		// TODO Auto-generated method stub
		Print.warn("MessageHandler.messageCallback() is not be implemeted");
	}
	
  /**
	 * 消息Message的实例本身可以处理该消息;<BR>
	 * @param msg 待处理的消息Message的实例;
	 * @return 仅仅返回<i>{@code true}</i>;
	 */
	protected final boolean handleSelfMessage(Message msg)
	{
		msg._self.run();
		return true;
	}
	
  /**
	 * Send a message to the current application instance;<BR>
	 * This object only put a message object into the message queue of the current application instance;
	 * @param msg Message instance which will be sent;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(Message msg)
	{
		boolean result = this.mQueue.put_tail(msg);
		return result;
	}
	
  /************************ Send Message from self to self ********************************/
  /**
   * Send Message from self to self
   * @param what Message code;
   * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
   */
	public final boolean sendMessage(int what)
	{
		Message msg = Message.obtain(this, this, what);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
  /**
	 * Send Message from self to self
	 * @param what Message code;
	 * @param obj Other parameters of message;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(int what, Object obj)
	{
		Message msg = Message.obtain(this, this, what, obj);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
  /**
	 * Send Message from self to self
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(int what, int arg1, int arg2)
	{
		Message msg = Message.obtain(this, this, what, arg1, arg2);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
  /**
	 * Send Message from self to self
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @param obj Other parameters of message;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(int what, int arg1, int arg2, Object obj)
	{
		Message msg = Message.obtain(this, this, what, arg1, arg2, obj);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
  /**
	 * Send Message from self to self,this message can be handled by message self;
	 * @param self Message handled it by itself;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(Runnable self)
	{
		Message msg = Message.obtain(this, this, self);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
  /**
	 * Send Message from self to self
	 * @param what Message code;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(int what, boolean needCallback)
	{
		Message msg = Message.obtain(this, this, what);
		msg.setNeedCallback(needCallback);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
  /**
	 * Send Message from self to self
	 * @param what Message code;
	 * @param obj Other parameters of message;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(int what, Object obj, boolean needCallback)
	{
		Message msg = Message.obtain(this, this, what, obj);
		msg.setNeedCallback(needCallback);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
  /**
	 * Send Message from self to self
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message send successfully;
	 */
	public final boolean sendMessage(int what, int arg1, int arg2, boolean needCallback)
	{
		Message msg = Message.obtain(this, this, what, arg1, arg2);
		msg.setNeedCallback(needCallback);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
  /**
	 * Send Message from self to self
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @param obj Other parameters of message;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(int what, int arg1, int arg2, Object obj, boolean needCallback)
	{
		Message msg = Message.obtain(this, this, what, arg1, arg2, obj);
		msg.setNeedCallback(needCallback);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
  /**
	 * Send Message from self to self,this message can be handled by message self;
	 * @param self Message handled it by itself;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(Runnable self, boolean needCallback)
	{
		Message msg = Message.obtain(this, this, self);
		msg.setNeedCallback(needCallback);
		boolean result = this.sendMessage(msg);
		return result;
	}
  
	/************************ Send Message from sender to self ********************************/
	/**
	 * Send Message from sender to self
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param what Message code;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(MessageHandler sender, int what)
	{
		Message msg = Message.obtain(this, sender, what);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to self
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param what Message code;
	 * @param obj Other parameters of message;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(MessageHandler sender, int what, Object obj)
	{
		Message msg = Message.obtain(this, sender, what, obj);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to self
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(MessageHandler sender, int what, int arg1, int arg2)
	{
		Message msg = Message.obtain(this, sender, what, arg1, arg2);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to self
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @param obj Other parameters of message;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(MessageHandler sender, int what, int arg1, int arg2, Object obj)
	{
		Message msg = Message.obtain(this, sender, what, arg1, arg2, obj);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to self,this message can be handled by message self;
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param self Message handled it by itself;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(MessageHandler sender, Runnable self)
	{
		Message msg = Message.obtain(this, sender, self);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to self
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param what Message code;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(MessageHandler sender, int what, boolean needCallback)
	{
		Message msg = Message.obtain(this, sender, what);
		msg.setNeedCallback(needCallback);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to self
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param what Message code;
	 * @param obj Other parameters of message;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(MessageHandler sender, int what, Object obj, boolean needCallback)
	{
		Message msg = Message.obtain(this, sender, what, obj);
		msg.setNeedCallback(needCallback);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to self
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message send successfully;
	 */
	public final boolean sendMessage(MessageHandler sender, int what, int arg1, int arg2, boolean needCallback)
	{
		Message msg = Message.obtain(this, sender, what, arg1, arg2);
		msg.setNeedCallback(needCallback);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to self
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @param obj Other parameters of message;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(MessageHandler sender, int what, int arg1, int arg2, Object obj, boolean needCallback)
	{
		Message msg = Message.obtain(this, sender, what, arg1, arg2, obj);
		msg.setNeedCallback(needCallback);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to self,this message can be handled by message self;
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param self Message handled it by itself;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(MessageHandler sender, Runnable self, boolean needCallback)
	{
		Message msg = Message.obtain(this, sender, self);
		msg.setNeedCallback(needCallback);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
	/************************ Send Message from self to target ********************************/
	/**
	 * Send Message from self to target
	 * @param what Message code;
	 * @param target Instance of MessageHandler, message is received by it;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(int what, MessageHandler target)
	{
		Message msg = Message.obtain(target, this, what);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from self to target
	 * @param what Message code;
	 * @param obj Other parameters of message;
	 * @param target Instance of MessageHandler, message is received by it;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(int what, Object obj, MessageHandler target)
	{
		Message msg = Message.obtain(target, this, what, obj);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from self to target
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @param target Instance of MessageHandler, message is received by it;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(int what, int arg1, int arg2, MessageHandler target)
	{
		Message msg = Message.obtain(target, this, what, arg1, arg2);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from self to target
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @param obj Other parameters of message;
	 * @param target Instance of MessageHandler, message is received by it;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(int what, int arg1, int arg2, Object obj, MessageHandler target)
	{
		Message msg = Message.obtain(target, this, what, arg1, arg2, obj);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from self to target,this message can be handled by message self;
	 * @param self Message handled it by itself;
	 * @param target Instance of MessageHandler, message is received by it;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(Runnable self, MessageHandler target)
	{
		Message msg = Message.obtain(target, this, self);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from self to target
	 * @param what Message code;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @param target Instance of MessageHandler, message is received by it;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(int what, boolean needCallback, MessageHandler target)
	{
		Message msg = Message.obtain(target, this, what);
		msg.setNeedCallback(needCallback);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from self to target
	 * @param what Message code;
	 * @param obj Other parameters of message;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @param target Instance of MessageHandler, message is received by it;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(int what, Object obj, boolean needCallback, MessageHandler target)
	{
		Message msg = Message.obtain(target, this, what, obj);
		msg.setNeedCallback(needCallback);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from self to target
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @param target Instance of MessageHandler, message is received by it;
	 * @return <i>{@code true}</i> if the message send successfully;
	 */
	public final boolean sendMessage(int what, int arg1, int arg2, boolean needCallback, MessageHandler target)
	{
		Message msg = Message.obtain(target, this, what, arg1, arg2);
		msg.setNeedCallback(needCallback);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from self to target
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @param obj Other parameters of message;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @param target Instance of MessageHandler, message is received by it;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(int what, int arg1, int arg2, Object obj, boolean needCallback, MessageHandler target)
	{
		Message msg = Message.obtain(target, this, what, arg1, arg2, obj);
		msg.setNeedCallback(needCallback);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from self to target,this message can be handled by message self;
	 * @param self Message handled it by itself;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @param target Instance of MessageHandler, message is received by it;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(Runnable self, boolean needCallback, MessageHandler target)
	{
		Message msg = Message.obtain(target, this, self);
		msg.setNeedCallback(needCallback);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/************************ Send Message from sender to target ********************************/
	/**
	 * Send Message from sender to target
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param target Instance of MsgApp, message is received by it;
	 * @param what Message code;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public static final boolean sendMessage(MessageHandler sender, MessageHandler target, int what)
	{
		Message msg = Message.obtain(target, sender, what);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to target
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param target Instance of MsgApp, message is received by it;
	 * @param what Message code;
	 * @param obj Other parameters of message;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public static final boolean sendMessage(MessageHandler sender, MessageHandler target, int what, Object obj)
	{
		Message msg = Message.obtain(target, sender, what, obj);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to target
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param target Instance of MsgApp, message is received by it;
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public static final boolean sendMessage(MessageHandler sender, MessageHandler target, int what, int arg1, int arg2)
	{
		Message msg = Message.obtain(target, sender, what, arg1, arg2);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to target
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param target Instance of MsgApp, message is received by it;
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @param obj Other parameters of message;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public static final boolean sendMessage(MessageHandler sender, MessageHandler target, int what, int arg1, int arg2, Object obj)
	{
		Message msg = Message.obtain(target, sender, what, arg1, arg2, obj);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to target,this message can be handled by message self;
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param target Instance of MsgApp, message is received by it;
	 * @param self Message handled it by itself;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public static final boolean sendMessage(MessageHandler sender, MessageHandler target, Runnable self)
	{
		Message msg = Message.obtain(target, sender, self);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to target
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param target Instance of MsgApp, message is received by it;
	 * @param what Message code;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public static final boolean sendMessage(MessageHandler sender, MessageHandler target, int what, boolean needCallback)
	{
		Message msg = Message.obtain(target, sender, what);
		msg.setNeedCallback(needCallback);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to target
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param target Instance of MsgApp, message is received by it;
	 * @param what Message code;
	 * @param obj Other parameters of message;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public static final boolean sendMessage(MessageHandler sender, MessageHandler target, int what, Object obj, boolean needCallback)
	{
		Message msg = Message.obtain(target, sender, what, obj);
		msg.setNeedCallback(needCallback);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to target
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param target Instance of MsgApp, message is received by it;
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message send successfully;
	 */
	public static final boolean sendMessage(MessageHandler sender, MessageHandler target, int what, int arg1, int arg2, boolean needCallback)
	{
		Message msg = Message.obtain(target, sender, what, arg1, arg2);
		msg.setNeedCallback(needCallback);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to target
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param target Instance of MsgApp, message is received by it;
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @param obj Other parameters of message;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public static final boolean sendMessage(MessageHandler sender, MessageHandler target, int what, int arg1, int arg2, Object obj, boolean needCallback)
	{
		Message msg = Message.obtain(target, sender, what, arg1, arg2, obj);
		msg.setNeedCallback(needCallback);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to target,this message can be handled by message self;
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param target Instance of MsgApp, message is received by it;
	 * @param self Message handled it by itself;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public static final boolean sendMessage(MessageHandler sender, MessageHandler target, Runnable self, boolean needCallback)
	{
		Message msg = Message.obtain(target, sender, self);
		msg.setNeedCallback(needCallback);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	@Override
	public String toString()
	{
		return "Handler (" + getClass().getName() + ") {" + Integer.toHexString(System.identityHashCode(this)) + "}";
	}
}
