package com.message;

public interface IMessageCallback
{
	/**
	 * ��Ϣ�������֮����Ҫ�ص��ķ���;<BR>
	 * �÷����ǿ�ѡ��,�������ʵ����,Ҳ���Բ�ʵ����.<BR>
	 * @param msg �Ѿ�������ɵ���ϢMessage��ʵ��;
	 * @param result �����Ϣ���ɹ��ش������,�򷵻�<i>{@code true}</i>,���򷵻�<i>{@code false}</i>;
	 * @return <i>{@code void}</i>
	 */
	public void messageCallback(Message msg, boolean result);
}
