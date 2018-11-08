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
 * test.runForEver();
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
 * test.runForEver();
 * </pre>
 */

package com.message;

import com.wrapper.Print;

public class MsgApp extends MessageHandler
{
	public MsgApp()
	{
		Print.debug("call MsgApp()");
	}
	
	public MsgApp(boolean isAlone, boolean quitAllowed)
	{
		super(isAlone, quitAllowed);
		Print.debug("call MsgApp(boolean isAlone, boolean quitAllowed)");
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
}
