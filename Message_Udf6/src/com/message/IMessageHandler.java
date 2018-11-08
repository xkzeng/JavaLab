package com.message;

public interface IMessageHandler
{
	/**
	 * 处理消息;<BR>
	 * 子类必须实现该方法,用于处理接收到的消息.<BR>
	 * @param msg 待处理的消息Message的实例;
	 * @return 如果消息被成功处理,则返回<i>{@code true}</i>,否则返回<i>{@code false}</i>;
	 */
	public boolean handleMessage(Message msg);
}
