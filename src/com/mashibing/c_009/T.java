package com.mashibing.c_009;

/**
 * ����������һ��ͬ���������������ڲ�������һ��ͬ�����������ٴμ�������Ϊ��������
 * ͬһ���̣߳�ͬһ���������Կ�������
 * @author A
 *
 */
public class T {
	synchronized void m1() {
		System.out.println("m1 start");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m2();
		System.out.println("m2 end");
	}
	
	synchronized void m2() {
		System.out.println("m2 start");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m2 end");
	}
	
	public static void main(String[] args) {
		new T().m1();
	}
}
