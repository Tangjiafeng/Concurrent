package com.mashibing.c_001;

/**
 * 测试类锁和对象锁的区别
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
		synchronized(TT.class) {// 类锁
			for (int i = 0; i < 5; i++) {
				count ++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("类锁：" + count);
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
		// 锁定T1这个对象，必须第一个线程执行完毕后释放锁，第二个线程才会拿到锁开始执行
//		new Thread(() -> T1.m()).start();
//		new Thread(() -> T1.m()).start();
		// 类锁
		new Thread(() -> TT.n()).start();		
	}

}