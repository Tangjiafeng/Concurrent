package com.mashibing.c_020;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest3 extends Thread {
	static Lock lock = new ReentrantLock(true);

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			lock.lock();
			System.out.println(Thread.currentThread().getName() + " got lock");
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		ReentrantLockTest3 r3 = new ReentrantLockTest3();
		
		new Thread(r3, "t1").start();
		new Thread(r3, "t2").start();
	}

}
