package com.mashibing.c_009;

/**
 * 可重入锁的另一种现象，子类调用父类的同步方法
 * @author A
 *
 */
public class T1 {
	synchronized void m() {
		System.out.println("m start");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m end");
	}
	public static void main(String[] args) {
		new TT().m();
		new TT().n();
	}
}

class TT extends T1 {

	@Override
	synchronized void m() {
		System.out.println("tt m start");
		super.m();
		System.out.println("tt m end");
	}
	
	synchronized void n() {
		System.out.println("tt m start");
		super.m();
		System.out.println("tt m end");
	}
	
}
