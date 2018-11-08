package com.message;

public interface IMessageCallback
{
	/**
	 * Message callback;<BR>
	 * Maybe subclasses implement this method or not.<BR>
	 * This method is optional;
	 * @param msg Instance of Message that have been handled;
	 * @param result <i>{@code true}</i> if this message have been handled successfully, <i>{@code false}</i> otherwise;
	 * @return <i>{@code void}</i>
	 */
	public void messageCallback(Message msg, boolean result);
}
