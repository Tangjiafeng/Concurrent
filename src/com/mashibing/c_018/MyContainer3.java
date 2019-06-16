package com.mashibing.c_018;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 实现两个线程的等待和唤醒，完全没有锁的影响
 * @author zhouy
 *
 */
public class MyContainer3 {
	List<Integer> lists = new ArrayList<Integer>();

	public void add(Integer i) {
		lists.add(i);
	}
	
	public int size() {
		return this.lists.size();
	}
	public static void main(String[] args) {
		MyContainer3 myContainer = new MyContainer3();
		
		CountDownLatch counter = new CountDownLatch(1);
		
		new Thread(() -> {
				System.out.println("t2 start");
				if(5 != myContainer.size()) {
					try {
						counter.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("t2 end");
		}, "t2").start();
		
		new Thread(() -> {
				System.out.println("t1 start");
				for (int i = 0; i < 10; i++) {
					myContainer.add(i);
					System.out.println("added " + i);
					if(5 == myContainer.size()) {
						counter.countDown();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}					
				}
				System.out.println("t1 end");
		},"t1").start();
	}

}
