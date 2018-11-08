package com.message;

public interface IMessageFilter
{
	/**
	 * 过滤消息;<BR>
	 * 被过滤掉的消息是不会被处理的,即,不会对被过滤掉的消息调用handleMessage()方法;<BR>
	 * 该方法是可选的,子类可以实现它,也可以不实现它.<BR>
	 * @param msg 待过滤的消息Message的实例;;
	 * @return 如果消息需要被过滤掉(忽略掉),则返回<i>{@code true}</i>,否则返回<i>{@code false}</i>;;
	 */
	public boolean filterMessage(Message msg);
}
