package com.message;

public interface IMessageDispatcher
{
	/**
	 * ������Ϣ;<BR>
	 * @param msg �����ɵ���ϢMessage��ʵ��;
	 * @return ����ɹ��ط�������Ϣ,�򷵻�<i>{@code true}</i>,���򷵻�<i>{@code false}</i>;
	 */
	public boolean dispatchMessage(Message msg);
}
