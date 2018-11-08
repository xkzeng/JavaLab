package com.message;

public interface IMessageFilter
{
	/**
	 * ������Ϣ;<BR>
	 * �����˵�����Ϣ�ǲ��ᱻ�����,��,����Ա����˵�����Ϣ����handleMessage()����;<BR>
	 * �÷����ǿ�ѡ��,�������ʵ����,Ҳ���Բ�ʵ����.<BR>
	 * @param msg �����˵���ϢMessage��ʵ��;;
	 * @return �����Ϣ��Ҫ�����˵�(���Ե�),�򷵻�<i>{@code true}</i>,���򷵻�<i>{@code false}</i>;;
	 */
	public boolean filterMessage(Message msg);
}
