package com.message;

public interface IMessageDispatcher
{
	/**
	 * Dispatch message;<BR>
	 * @param msg Instance of Message that will be dispatched;
	 * @return <i>{@code true}</i> if this message have been dispatched, <i>{@code false}</i> otherwise;
	 */
	public boolean dispatchMessage(Message msg);
}
