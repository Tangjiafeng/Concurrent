package com.mashibing.c_009;

/**
 * 可重入锁，一个同步方法，可以在内部调用另一个同步方法，会再次加锁，称为可重入锁
 * 同一个线程，同一把锁，所以可以重入
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
