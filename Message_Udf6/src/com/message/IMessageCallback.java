package com.message;

public interface IMessageCallback
{
	/**
	 * 消息处理完成之后需要回调的方法;<BR>
	 * 该方法是可选的,子类可以实现它,也可以不实现它.<BR>
	 * @param msg 已经处理完成的消息Message的实例;
	 * @param result 如果消息被成功地处理完成,则返回<i>{@code true}</i>,否则返回<i>{@code false}</i>;
	 * @return <i>{@code void}</i>
	 */
	public void messageCallback(Message msg, boolean result);
}
