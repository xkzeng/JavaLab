package com.message;

public interface IMessageDispatcher
{
	/**
	 * 分派消息;<BR>
	 * @param msg 待分派的消息Message的实例;
	 * @return 如果成功地分派了消息,则返回<i>{@code true}</i>,否则返回<i>{@code false}</i>;
	 */
	public boolean dispatchMessage(Message msg);
}
