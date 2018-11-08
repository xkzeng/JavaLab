/**
 * MessageHandler class is a message handler.It can handle message or command asynchronously in background;
 * You can construct an MessageHandler object with default constructor "MessageHandler()", or you can
 * construct an object with constructor "MessageHandler(Looper _looper)";
 */

package com.message;

import java.lang.NullPointerException;
import com.wrapper.Print;

public class MessageHandler implements IMessageFilter, IMessageHandler, IMessageCallback
{
  /** Message looper which fetch message from message queue and handle message */
	private Looper mLooper;
    
  /** Message queue which store messages */
	private MessageQueue mQueue;
	
  /** Default constructor */
	public MessageHandler()
	{
		Print.debug("call MessageHandler()");
		this.mLooper = null;
		this.mQueue = null;
	}
	
	/**
   * Constructor with parameter _looper
   * @param _looper Instance of Looper class,it can fetch message from message queue and handle message;
   */
	public MessageHandler(Looper _looper)
	{
		Print.debug("call MessageHandler(Looper)");
		this.setLooper(_looper);
	}
  
	/**
	 * Set message looper for this message handler
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
   * Get the message looper object of current message handler instance;
   * @param _looper Instance of Looper class,it can fetch message from message queue and handle message;
   * @return Instance of Looper class;
   */
	public synchronized Looper getLooper()
	{
		return this.mLooper;
	}
	
  /**
	 * Filter Message;<BR>
	 * Message which is filtered will not be handled, that means will not call handleMessage() for this message;<BR>
	 * Maybe subclasses implement this method or not;<BR>
	 * This method is optional;
	 * @param msg Message instance which will be filtered;
	 * @return <i>{@code true}</i> if the message is filtered; <i>{@code false}</i> otherwise;;
	 */
	@Override
	public boolean filterMessage(Message msg)
	{
		// TODO Auto-generated method stub
		Print.warn("MessageHandler.filterMessage() is not be implemeted");
		return false;
	}

	/**
	 * Handle message;<BR>
	 * Subclasses must implement this method to handle messages that received.<BR>
	 * @param msg Instance of Message that will be handled;
	 * @return <i>{@code true}</i> if this message have been handled, <i>{@code false}</i> otherwise;
	 */
	@Override
	public boolean handleMessage(Message msg)
	{
		// TODO Auto-generated method stub
		Print.warn("MessageHandler.handleMessage() is not be implemeted");
		return false;
	}
	
  /**
	 * Message callback;<BR>
	 * Maybe subclasses implement this method or not.<BR>
	 * This method is optional;
	 * @param msg Instance of Message that have been handled;
	 * @param result <i>{@code true}</i> if this message have been handled successfully, <i>{@code false}</i> otherwise;
	 * @return <i>{@code void}</i>
	 */
	@Override
	public void messageCallback(Message msg, boolean result)
	{
		// TODO Auto-generated method stub
		Print.warn("MessageHandler.messageCallback() is not be implemeted");
	}
	
  /**
	 * Handle message by message-self;<BR>
	 * @param msg Instance of Message that will be handled;
	 * @return <i>{@code true}</i> for ever;
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
