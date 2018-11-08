package com.message;

public interface IMessageFilter
{
	/**
	 * Filter Message;<BR>
	 * Message which is filtered will not be handled, that means will not call handleMessage() for this message;<BR>
	 * Maybe subclasses implement this method or not;<BR>
	 * This method is optional;
	 * @param msg Message instance which will be filtered;
	 * @return <i>{@code true}</i> if the message is filtered; <i>{@code false}</i> otherwise;;
	 */
	public boolean filterMessage(Message msg);
}
