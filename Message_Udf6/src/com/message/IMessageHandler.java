package com.message;

public interface IMessageHandler
{
	/**
	 * ������Ϣ;<BR>
	 * �������ʵ�ָ÷���,���ڴ�����յ�����Ϣ.<BR>
	 * @param msg ���������ϢMessage��ʵ��;
	 * @return �����Ϣ���ɹ�����,�򷵻�<i>{@code true}</i>,���򷵻�<i>{@code false}</i>;
	 */
	public boolean handleMessage(Message msg);
}
