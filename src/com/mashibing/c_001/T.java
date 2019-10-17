package com.mashibing.c_001;

public class T implements Runnable {
	private int count = 10;
		
	public static void main(String[] args) {
		T t = new T();
		
		for (int i = 0; i < 5; i++) {
			new Thread(t, "Thread_" + i).start();
		}
	}

	@Override
	public synchronized void run() {// synchronized ��ǵķ������ִ����ԭ���Եģ����ɷ֣���֤countֵ
		this.count --;              // �ڲ��������µ���ȷ�ԡ�
		System.out.println(Thread.currentThread().getName() + " count = " + this.count);
	}

}
