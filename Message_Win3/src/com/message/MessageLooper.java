/**
 * MessageLooper class is a message handler.It can handle message or command asynchronously in background;
 */

package com.message;

import com.wrapper.Print;

public class MessageLooper implements IMessageDispatcher
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
	 * Instance of MsgApp, it handle messages;
	 */
	private final MsgApp mHandler;
	
	/**
	 * Constructor with parameters;
	 * @param isAlone <i>{@code true}</i> if run this instance as an alone thread; <i>{@code false}</i> if run this instance as main-thread;
	 * @param quitAllowed <i>{@code true}</i> if allowed this instance to exit, <i>{@code false}</i> otherwise;
	 * @param handler Instance of MsgApp, it handle message;
	 */
	public MessageLooper(boolean isAlone, boolean quitAllowed, MsgApp handler)
	{
		this.mQueue = new MessageQueue();
		this.mIsAlone = isAlone;
		this.mQuitAllowed = quitAllowed;
		this.mHandler = handler;
		Print.debug("call MessageHandler(boolean isAlone, boolean quitAllowed)");
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
	 * Check whether this message looper allowed to quit
	 * @param <i>{@code void}</i>
	 * @return <i>{@code true}</i> if this message looper allowed to quit; <i>{@code false}</i> otherwise;
	 */
	public final boolean isQuitAllowed()
	{
		return this.mQuitAllowed;
	}
	
	/**
	 * Execute the message looper to handle message
	 * @param <i>{@code void}</i>
	 * @return <i>{@code void}</i>
	 */
	protected void loop()
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
		result = this.mHandler.handleMessage(msg); //交给消息处理器实例App处理;
		
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
	
	@Override
	public String toString()
	{
		return "Handler (" + getClass().getName() + ") {" + Integer.toHexString(System.identityHashCode(this)) + "}";
	}
}
