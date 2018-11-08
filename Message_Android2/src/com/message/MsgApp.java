package com.message;

import com.wrapper.Print;

public class MsgApp extends MessageHandler
{
    /**
     * Default constructor
     */
	public MsgApp()
	{
		super(LooperThread.newInstance());
		Print.debug("call MsgApp()");
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
		Print.warn("MsgApp.handleMessage() is not be implemeted");
		return true;
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
		Print.warn("MsgApp.handleMessage() is not be implemeted");
	}
	
	public static void main(String[] args)
	{
        MsgApp app = new MsgApp();
  	
        Thread _looperThread = app.getLooper().getThread();
		
		System.out.println("Thread: " + _looperThread.getName() + ", id = " + _looperThread.getId());
        
        try
        {
            Thread.sleep(2000);
            app.sendMessage(10);
            Thread.sleep(2000);
            app.sendMessage(20, true);
            _looperThread.join();
        }
        catch(Exception e)
        {}
        System.out.println("exit");
	}
}
