package com.mashibing.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * �ֹ�������Ҫ�ֹ������ͽ�����ʹ�ø����
 * 1��ReentrantLock��finally �����һ��һ��һ��Ҫ���� unlock
 * 2��trylock�����Ը����Ƿ��õ������߼�����trylock ����ָ���߳�������ʱ������
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
