package com.mashibing.c_001;

/**
 * ���������Ͷ�����������
 * 
 * @author A
 *
 */
public class TT {
	static int count = 0;
	public synchronized void m() {
		System.out.println("m method" + Thread.currentThread().getName());
		for (int i = 0; i < 5; i++) {
			count ++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("m " + count);
		}
		synchronized(TT.class) {// ����
			for (int i = 0; i < 5; i++) {
				count ++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("������" + count);
			}
		}
	}

	public synchronized static void n() {
		System.out.println("n method" + Thread.currentThread().getName());
		for (int i = 0; i < 5; i++) {
			count ++;
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("n " + count);
		}
		
	}
	
	public static void main(String[] args) {
		TT T1 = new TT();
		TT T2 = new TT();
		// ����T1������󣬱����һ���߳�ִ����Ϻ��ͷ������ڶ����̲߳Ż��õ�����ʼִ��
//		new Thread(() -> T1.m()).start();
//		new Thread(() -> T1.m()).start();
		// ����
		new Thread(() -> TT.n()).start();		
	}

}