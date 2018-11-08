/**
 * Application base-class based message;
 * @author zengxiankui
 * @version 0.0.0.1
 * @date 2018-10-05
 * <pre>
 * MsgApp test = new MsgApp()
 * {
 *   @Override
 *   public boolean filterMessage(Message msg)
 *   {
 *     // TODO Auto-generated method stub
 *     return false;
 *   }
 *   
 *   @Override
 *   public boolean handleMessage(Message msg)
 *   {
 *     // TODO Auto-generated method stub
 *     return true;
 *   }
 *   
 *   @Override
 *   public void messageCallback(Message msg, boolean result)
 *   {
 *     // TODO Auto-generated method stub
 *   }
 * };
 * test.runAsAlone();
 * or
 * test.runAsMain();
 * or
 * test.runForEver(false, false);
 * or
 * test.runForEver(true, true);
 * </pre>
 * 
 * <pre>
 * public class AppTest extends MsgApp
 * {
 *   @Override
 *   public boolean filterMessage(Message msg)
 *   {
 *     // TODO Auto-generated method stub
 *     return false;
 *   }
 *   
 *   @Override
 *   public boolean handleMessage(Message msg)
 *   {
 *     // TODO Auto-generated method stub
 *     return true;
 *   }
 *   
 *   @Override
 *   public void messageCallback(Message msg, boolean result)
 *   {
 *     // TODO Auto-generated method stub
 *   }
 * };
 * AppTest test = new AppTest();
 * test.runAsAlone();
 * or
 * test.runAsMain();
 * or
 * test.runForEver(false, false);
 * or
 * test.runForEver(true, true);
 * </pre>
 */

package com.message;

import java.lang.Thread;
import com.wrapper.Print;

public class MsgApp extends Thread implements IMessageFilter, IMessageHandler, IMessageCallback
{
	/** Looper that handle message */
	private MessageLooper mLooper = null;
	
	/** Message queue which store messages */
	private MessageQueue mQueue = null;
	
	/** Default constructor */
	public MsgApp()
	{
		Print.debug("call MsgApp()");
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
		Message msg = Message.obtain(this, what);
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
		Message msg = Message.obtain(this, what, obj);
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
		Message msg = Message.obtain(this, what, arg1, arg2);
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
		Message msg = Message.obtain(this, what, arg1, arg2, obj);
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
		Message msg = Message.obtain(this, what);
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
		Message msg = Message.obtain(this, what, obj);
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
		Message msg = Message.obtain(this, what, arg1, arg2);
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
		Message msg = Message.obtain(this, what, arg1, arg2, obj);
		msg.setNeedCallback(needCallback);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
	/************************ Send Message from sender to self ********************************/
	/**
	 * Send Message from sender to self
	 * @param sender Instance of MsgApp, message is sent by it;
	 * @param what Message code;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(MsgApp sender, int what)
	{
		Message msg = Message.obtain(sender, what);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to self
	 * @param sender Instance of MsgApp, message is sent by it;
	 * @param what Message code;
	 * @param obj Other parameters of message;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(MsgApp sender, int what, Object obj)
	{
		Message msg = Message.obtain(sender, what, obj);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to self
	 * @param sender Instance of MsgApp, message is sent by it;
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(MsgApp sender, int what, int arg1, int arg2)
	{
		Message msg = Message.obtain(sender, what, arg1, arg2);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to self
	 * @param sender Instance of MsgApp, message is sent by it;
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @param obj Other parameters of message;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(MsgApp sender, int what, int arg1, int arg2, Object obj)
	{
		Message msg = Message.obtain(sender, what, arg1, arg2, obj);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to self
	 * @param sender Instance of MsgApp, message is sent by it;
	 * @param what Message code;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(MsgApp sender, int what, boolean needCallback)
	{
		Message msg = Message.obtain(sender, what);
		msg.setNeedCallback(needCallback);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to self
	 * @param sender Instance of MsgApp, message is sent by it;
	 * @param what Message code;
	 * @param obj Other parameters of message;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(MsgApp sender, int what, Object obj, boolean needCallback)
	{
		Message msg = Message.obtain(sender, what, obj);
		msg.setNeedCallback(needCallback);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to self
	 * @param sender Instance of MsgApp, message is sent by it;
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(MsgApp sender, int what, int arg1, int arg2, boolean needCallback)
	{
		Message msg = Message.obtain(sender, what, arg1, arg2);
		msg.setNeedCallback(needCallback);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to self
	 * @param sender Instance of MsgApp, message is sent by it;
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @param obj Other parameters of message;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(MsgApp sender, int what, int arg1, int arg2, Object obj, boolean needCallback)
	{
		Message msg = Message.obtain(sender, what, arg1, arg2, obj);
		msg.setNeedCallback(needCallback);
		boolean result = this.sendMessage(msg);
		return result;
	}
	
	/************************ Send Message from self to target ********************************/
	/**
	 * Send Message from self to target
	 * @param what Message code;
	 * @param target Instance of MsgApp, message is received by it;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(int what, MsgApp target)
	{
		Message msg = Message.obtain(this, what);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from self to target
	 * @param what Message code;
	 * @param obj Other parameters of message;
	 * @param target Instance of MsgApp, message is received by it;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(int what, Object obj, MsgApp target)
	{
		Message msg = Message.obtain(this, what, obj);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from self to target
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @param target Instance of MsgApp, message is received by it;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(int what, int arg1, int arg2, MsgApp target)
	{
		Message msg = Message.obtain(this, what, arg1, arg2);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from self to target
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @param obj Other parameters of message;
	 * @param target Instance of MsgApp, message is received by it;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(int what, int arg1, int arg2, Object obj, MsgApp target)
	{
		Message msg = Message.obtain(this, what, arg1, arg2, obj);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from self to target
	 * @param what Message code;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @param target Instance of MsgApp, message is received by it;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(int what, boolean needCallback, MsgApp target)
	{
		Message msg = Message.obtain(this, what);
		msg.setNeedCallback(needCallback);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from self to target
	 * @param what Message code;
	 * @param obj Other parameters of message;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @param target Instance of MsgApp, message is received by it;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(int what, Object obj, boolean needCallback, MsgApp target)
	{
		Message msg = Message.obtain(this, what, obj);
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
	 * @param target Instance of MsgApp, message is received by it;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(int what, int arg1, int arg2, boolean needCallback, MsgApp target)
	{
		Message msg = Message.obtain(this, what, arg1, arg2);
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
	 * @param target Instance of MsgApp, message is received by it;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(int what, int arg1, int arg2, Object obj, boolean needCallback, MsgApp target)
	{
		Message msg = Message.obtain(this, what, arg1, arg2, obj);
		msg.setNeedCallback(needCallback);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/************************ Send Message from sender to target ********************************/
	/**
	 * Send Message from sender to target
	 * @param sender Instance of MsgApp, message is sent by it;
	 * @param target Instance of MsgApp, message is received by it;
	 * @param what Message code;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public static final boolean sendMessage(MsgApp sender, MsgApp target, int what)
	{
		Message msg = Message.obtain(sender, what);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to target
	 * @param sender Instance of MsgApp, message is sent by it;
	 * @param target Instance of MsgApp, message is received by it;
	 * @param what Message code;
	 * @param obj Other parameters of message;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public static final boolean sendMessage(MsgApp sender, MsgApp target, int what, Object obj)
	{
		Message msg = Message.obtain(sender, what, obj);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to target
	 * @param sender Instance of MsgApp, message is sent by it;
	 * @param target Instance of MsgApp, message is received by it;
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public static final boolean sendMessage(MsgApp sender, MsgApp target, int what, int arg1, int arg2)
	{
		Message msg = Message.obtain(sender, what, arg1, arg2);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to target
	 * @param sender Instance of MsgApp, message is sent by it;
	 * @param target Instance of MsgApp, message is received by it;
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @param obj Other parameters of message;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public static final boolean sendMessage(MsgApp sender, MsgApp target, int what, int arg1, int arg2, Object obj)
	{
		Message msg = Message.obtain(sender, what, arg1, arg2, obj);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to target
	 * @param sender Instance of MsgApp, message is sent by it;
	 * @param target Instance of MsgApp, message is received by it;
	 * @param what Message code;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public static final boolean sendMessage(MsgApp sender, MsgApp target, int what, boolean needCallback)
	{
		Message msg = Message.obtain(sender, what);
		msg.setNeedCallback(needCallback);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to target
	 * @param sender Instance of MsgApp, message is sent by it;
	 * @param target Instance of MsgApp, message is received by it;
	 * @param what Message code;
	 * @param obj Other parameters of message;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public static final boolean sendMessage(MsgApp sender, MsgApp target, int what, Object obj, boolean needCallback)
	{
		Message msg = Message.obtain(sender, what, obj);
		msg.setNeedCallback(needCallback);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to target
	 * @param sender Instance of MsgApp, message is sent by it;
	 * @param target Instance of MsgApp, message is received by it;
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public static final boolean sendMessage(MsgApp sender, MsgApp target, int what, int arg1, int arg2, boolean needCallback)
	{
		Message msg = Message.obtain(sender, what, arg1, arg2);
		msg.setNeedCallback(needCallback);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to target
	 * @param sender Instance of MsgApp, message is sent by it;
	 * @param target Instance of MsgApp, message is received by it;
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @param obj Other parameters of message;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public static final boolean sendMessage(MsgApp sender, MsgApp target, int what, int arg1, int arg2, Object obj, boolean needCallback)
	{
		Message msg = Message.obtain(sender, what, arg1, arg2, obj);
		msg.setNeedCallback(needCallback);
		boolean result = target.sendMessage(msg);
		return result;
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
		Print.warn("MsgApp.filterMessage() is not be implemeted");
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
		Print.warn("MsgApp.handleMessage() is not be implemeted");
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
		Print.warn("MsgApp.messageCallback() is not be implemeted");
	}
	
	/**
	 * Check whether this instance run as an alone thread or not;
	 * @param <i>{@code void}</i>
	 * @return <i>{@code true}</i> if this instance run as an alone thread; <i>{@code false}</i> if this instance run as main-thread;
	 */
	public final boolean isAlone()
	{
		return this.mLooper.isAlone();
	}
	
	/**
	 * Run this instance as an alone thread; Only call start() method of this instance;
	 * @param <i>{@code void}</i>
	 * @return <i>{@code void}</i>
	 */
	public final void runAsAlone()
	{
		Print.debug("run as alone");
		this.mLooper = new MessageLooper(true, true, this);
		this.mQueue = this.mLooper.getQueue();
		this.setDaemon(true);
		this.start();
	}
	
	/**
	 * Run this instance as main-thread; Only call run() method of this instance;
	 * @param <i>{@code void}</i>
	 * @return <i>{@code void}</i>
	 */
	public final void runAsMain()
	{
		Print.debug("run as main");
		this.mLooper = new MessageLooper(false, false, this);
		this.mQueue = this.mLooper.getQueue();
		this.run();
	}
	
	/**
	 * Run this instance for ever;
	 * @param isAlone <i>{@code true}</i> if run this instance as an alone thread; <i>{@code false}</i> if run this instance as main-thread;
	 * @param quitAllowed <i>{@code true}</i> if allowed this instance to exit, <i>{@code false}</i> otherwise;
	 * @return <i>{@code void}</i>
	 */
	public final void runForEver(boolean isAlone, boolean quitAllowed)
	{
		this.mLooper = new MessageLooper(isAlone, quitAllowed, this);
		this.mQueue = this.mLooper.getQueue();
		
		if(this.mLooper.isAlone())
		{
			this.setDaemon(true);
			this.start();
		}
		else
		{
			this.run();
		}
	}
	
	/**
	 * Method of this thread instance;
	 * @param <i>{@code void}</i>
	 * @return <i>{@code void}</i>
	 */
	@Override
	public final void run()
	{
		this.mLooper.loop();
		Print.warn("message loop unexpectedly exited");
	}
}
