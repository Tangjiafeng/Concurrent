package com.mashibing.c_023;

/**
 * �̰߳�ȫ�ĵ���ģʽ
 * @author A
 *
 */
public class Singleton {

	public static Singleton getSingle() {
		return Inner.s;
	}
	
	public Singleton() {
		System.out.println("single");
	}

	private static class Inner {
		private static Singleton s = new Singleton();// ���ü�����Ҳ��ʵ��������
	}
	
	public static void main(String[] args) {

	}

}
