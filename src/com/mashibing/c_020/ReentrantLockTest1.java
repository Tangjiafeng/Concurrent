package com.mashibing.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 手工锁，需要手工上锁和解锁，使用更灵活
 * 1，ReentrantLock，finally 语句里一定一定一定要处理 unlock
 * 2，trylock，可以根据是否拿到锁做逻辑处理，trylock 可以指定线程拿锁的时间限制
 *  
 * @author zhouy
 *
 */
public class ReentrantLockTest1 {
	Lock lock = new ReentrantLock();
	
	void m1() {
		lock.lock();
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println(i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} finally {
			lock.unlock();
		}
	}
	
	void m2() {
//		lock.lock();
		boolean locked = false;
		try {
//			locked = lock.tryLock();
			locked = lock.tryLock(15, TimeUnit.SECONDS);
			System.out.println("m2 start");
			if(locked) {
				System.out.println("get the lock");
			} else {
				System.out.println("failed to get the lock");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if(locked) lock.unlock();
		}		
	}
	public static void main(String[] args) {
		ReentrantLockTest1 t = new ReentrantLockTest1();
		
		new Thread(t::m1, "t1").start();
		new Thread(t::m2, "t2").start();
		
		
	}

}
