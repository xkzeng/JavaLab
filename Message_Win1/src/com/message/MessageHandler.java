/**
 * Application base-class based message;
 * @author zengxiankui
 * @version 0.0.0.1
 * @date 2018-10-05
 * <pre>
 * MessageHandler test = new MessageHandler()
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
 * test.runForEver();
 * </pre>
 * 
 * <pre>
 * public class AppTest extends MessageHandler
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
 * test.runForEver();
 * </pre>
 */

package com.message;

import java.lang.Thread;
import com.wrapper.Print;

public class MessageHandler extends Thread implements IMessageFilter, IMessageDispatcher, IMessageHandler, IMessageCallback
{
	/** Message queue which store messages */
	private final MessageQueue mQueue;
	
	/**
	 * Whether this instance run as an alone thread or not;<BR>
	 * <i>{@code true}</i> if run this instance as an alone thread; <i>{@code false}</i> if run this instance as main-thread;
	 */
	private final boolean mIsAlone;
	
	/**
	 * Whether this message looper allowed to quit;<BR>
	 * <i>{@code true}</i> if this message looper allowed to quit, <i>{@code false}</i> otherwise;
	 */
	private final boolean mQuitAllowed;
	
	/**
	 * Constructor with parameters;
	 * @param isAlone <i>{@code true}</i> if run this instance as an alone thread; <i>{@code false}</i> if run this instance as main-thread;
	 * @param quitAllowed <i>{@code true}</i> if allowed this instance to exit, <i>{@code false}</i> otherwise;
	 */
	public MessageHandler(boolean isAlone, boolean quitAllowed)
	{
		this.mQueue = new MessageQueue();
		this.mIsAlone = isAlone;
		this.mQuitAllowed = quitAllowed;
		Print.debug("call MessageHandler(boolean isAlone, boolean quitAllowed)");
	}
	
	/**
	 * Default constructor
	 */
	public MessageHandler()
	{
		this(true, true);
//		this.mQueue = new MessageQueue();
//		this.mIsAlone = true;
//		this.mQuitAllowed = true;
		Print.debug("call MessageHandler()");
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
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param what Message code;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(MessageHandler sender, int what)
	{
		Message msg = Message.obtain(sender, what);
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
		Message msg = Message.obtain(sender, what, obj);
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
		Message msg = Message.obtain(sender, what, arg1, arg2);
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
		Message msg = Message.obtain(sender, what, arg1, arg2, obj);
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
		Message msg = Message.obtain(sender, what);
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
		Message msg = Message.obtain(sender, what, obj);
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
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(MessageHandler sender, int what, int arg1, int arg2, boolean needCallback)
	{
		Message msg = Message.obtain(sender, what, arg1, arg2);
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
		Message msg = Message.obtain(sender, what, arg1, arg2, obj);
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
		Message msg = Message.obtain(this, what);
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
		Message msg = Message.obtain(this, what, obj);
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
	 * @param target Instance of MessageHandler, message is received by it;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(int what, int arg1, int arg2, Object obj, MessageHandler target)
	{
		Message msg = Message.obtain(this, what, arg1, arg2, obj);
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
	 * @param target Instance of MessageHandler, message is received by it;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(int what, Object obj, boolean needCallback, MessageHandler target)
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
	 * @param target Instance of MessageHandler, message is received by it;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(int what, int arg1, int arg2, boolean needCallback, MessageHandler target)
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
	 * @param target Instance of MessageHandler, message is received by it;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public final boolean sendMessage(int what, int arg1, int arg2, Object obj, boolean needCallback, MessageHandler target)
	{
		Message msg = Message.obtain(this, what, arg1, arg2, obj);
		msg.setNeedCallback(needCallback);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/************************ Send Message from sender to target ********************************/
	/**
	 * Send Message from sender to target
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param target Instance of MessageHandler, message is received by it;
	 * @param what Message code;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public static final boolean sendMessage(MessageHandler sender, MessageHandler target, int what)
	{
		Message msg = Message.obtain(sender, what);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to target
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param target Instance of MessageHandler, message is received by it;
	 * @param what Message code;
	 * @param obj Other parameters of message;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public static final boolean sendMessage(MessageHandler sender, MessageHandler target, int what, Object obj)
	{
		Message msg = Message.obtain(sender, what, obj);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to target
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param target Instance of MessageHandler, message is received by it;
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public static final boolean sendMessage(MessageHandler sender, MessageHandler target, int what, int arg1, int arg2)
	{
		Message msg = Message.obtain(sender, what, arg1, arg2);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to target
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param target Instance of MessageHandler, message is received by it;
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @param obj Other parameters of message;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public static final boolean sendMessage(MessageHandler sender, MessageHandler target, int what, int arg1, int arg2, Object obj)
	{
		Message msg = Message.obtain(sender, what, arg1, arg2, obj);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to target
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param target Instance of MessageHandler, message is received by it;
	 * @param what Message code;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public static final boolean sendMessage(MessageHandler sender, MessageHandler target, int what, boolean needCallback)
	{
		Message msg = Message.obtain(sender, what);
		msg.setNeedCallback(needCallback);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to target
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param target Instance of MessageHandler, message is received by it;
	 * @param what Message code;
	 * @param obj Other parameters of message;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public static final boolean sendMessage(MessageHandler sender, MessageHandler target, int what, Object obj, boolean needCallback)
	{
		Message msg = Message.obtain(sender, what, obj);
		msg.setNeedCallback(needCallback);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to target
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param target Instance of MessageHandler, message is received by it;
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public static final boolean sendMessage(MessageHandler sender, MessageHandler target, int what, int arg1, int arg2, boolean needCallback)
	{
		Message msg = Message.obtain(sender, what, arg1, arg2);
		msg.setNeedCallback(needCallback);
		boolean result = target.sendMessage(msg);
		return result;
	}
	
	/**
	 * Send Message from sender to target
	 * @param sender Instance of MessageHandler, message is sent by it;
	 * @param target Instance of MessageHandler, message is received by it;
	 * @param what Message code;
	 * @param arg1 The first parameter with integer;
	 * @param arg2 The second parameter with integer;
	 * @param obj Other parameters of message;
	 * @param needCallback <i>{@code true}</i> if need to notify the sender after handle this message; <i>{@code false}</i> otherwise;
	 * @return <i>{@code true}</i> if the message is sent successfully, <i>{@code false}</i> otherwise;
	 */
	public static final boolean sendMessage(MessageHandler sender, MessageHandler target, int what, int arg1, int arg2, Object obj, boolean needCallback)
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
	 * Get message queue
	 * @return Instance of MessageQueue;
	 */
	public final MessageQueue getQueue()
	{
		return this.mQueue;
	}
	
	/**
	 * Check whether this instance run as an alone thread or not;
	 * @param <i>{@code void}</i>
	 * @return <i>{@code true}</i> if this instance run as an alone thread; <i>{@code false}</i> if this instance run as main-thread;
	 */
	public final boolean isAlone()
	{
		return this.mIsAlone;
	}
	
	/**
	 * Check whether this message looper allowed to quit;
	 * @param <i>{@code void}</i>
	 * @return <i>{@code true}</i> if this message looper allowed to quit; <i>{@code false}</i> otherwise;
	 */
	public final boolean isQuitAllowed()
	{
		return this.mQuitAllowed;
	}
	
	/**
	 * Run this instance as an alone thread; Only call start() method of this instance;
	 * @param <i>{@code void}</i>
	 * @return <i>{@code void}</i>
	 */
	public final void runAsAlone()
	{
		Print.debug("run as alone");
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
		this.run();
	}
	
	/**
	 * Run this instance for ever;
	 * @param isAlone <i>{@code true}</i> if run this instance as an alone thread; <i>{@code false}</i> if run this instance as main-thread;
	 * @param quitAllowed <i>{@code true}</i> if allowed this instance to exit, <i>{@code false}</i> otherwise;
	 * @return <i>{@code void}</i>
	 */
	public final void runForEver()
	{
		if(this.isAlone())
		{
			this.runAsAlone();
		}
		else
		{
			this.runAsMain();
		}
	}
	
	/**
	 * Dispatch message;<BR>
	 * Subclasses can not implement this method to dispatch messages that received.<BR>
	 * @param msg Instance of Message that will be dispatched;
	 * @return <i>{@code true}</i> if this message have been dispatched, <i>{@code false}</i> otherwise;
	 */
	@Override
	public final boolean dispatchMessage(Message msg)
	{
		// TODO Auto-generated method stub
		boolean result = false;
		
		//STEP1:处理消息;
		result = this.handleMessage(msg); //交给消息处理器处理;
		
		//STEP2:执行回调;
		if(msg.isNeedCallback())
		{
		  //执行消息发送者的回调操作:
			//this.messageCallback(msg, result); //逻辑上的回调意义含糊;
			if(msg._sender != null)
			{
				msg._sender.messageCallback(msg, result);
			}
			else
			{
				Print.error("sender is null, can not do it's callback!");
			}
		}
		return result;
	}
	
	/**
	 * Execute the message looper to handle message
	 * @param <i>{@code void}</i>
	 * @return <i>{@code void}</i>
	 */
	private void loop()
	{
		final MessageQueue queue = this.mQueue;
		
		Message msg = null;
		while(true)
		{
			msg = queue.getMessage(); // might block
			if(msg == null)
			{
			  // No message indicates that the message queue is quitting.
				Print.error("the current message looper quit");
				break;
			}
			
			if(msg._what <= 0)
			{
			  //recycle the message which cause the looper to quit;
				Message.recycle(msg);
				
				boolean quitAllowed = this.isQuitAllowed();
				if(quitAllowed == true)
				{
					//the non-main looper can be allowed to quit;
					Print.warn("the current looper quit");
					break;
				}
				
				if(quitAllowed == false)
				{
					//the main looper do not be allowed to quit;
					Print.warn("the main looper do not be allowed to quit");
					continue;
				}
			}
			
			//Dispatch message to target;
			this.dispatchMessage(msg);
			
			//Recycle this message;
			//msg.recycleUnchecked();
			Message.recycle(msg);
		}
		
		//recycle all messages that do not be handled;
		//... add code later;
	}
	
	/**
	 * Method of this thread instance;
	 * @param <i>{@code void}</i>
	 * @return <i>{@code void}</i>
	 */
	@Override
	public final void run()
	{
		this.loop();
		Print.warn("message loop unexpectedly exited");
	}
	
	@Override
	public String toString()
	{
		return "Handler (" + getClass().getName() + ") {" + Integer.toHexString(System.identityHashCode(this)) + "}";
	}
}
