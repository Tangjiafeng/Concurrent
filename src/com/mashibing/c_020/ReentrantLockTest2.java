package com.mashibing.c_020;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可打断锁，可以相应打断拿不到锁等待的线程
 * @author zhouy
 *
 */
public class ReentrantLockTest2 {

	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		
		new Thread(() -> {
			lock.lock();
			try {
				System.out.println("t1 start");
				Thread.sleep(Integer.MAX_VALUE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "t1").start();
		
		Thread t2 = new Thread(() -> {
			try {
				lock.lockInterruptibly();
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("t2 stop to get lock");
			} finally {
//				lock.unlock();
			}
		});
		t2.start();
		t2.interrupt();
	}

}
