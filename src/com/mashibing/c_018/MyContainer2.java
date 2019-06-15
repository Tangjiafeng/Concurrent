package com.mashibing.c_018;

import java.util.ArrayList;
import java.util.List;

/**
 * 一道面试题，创建一个容器类，有两个方法，添加元素和返回容器元素个数
 * 创建两个线程，一个线程往容器写10个元素，一个线程在容器元素达到5个的时候给出提示
 * 成员变量 lists 必须由 volatile 修饰
 * 
 * 完善：（1）不是很精确；（2）浪费 CPU
 * 
 * 采用 wait notify
 * @author A
 *
 */
public class MyContainer2 {
	/*volatile*/ List<Integer> lists = new ArrayList<Integer>();

	public void add(Integer i) {
		lists.add(i);
	}
	
	public int size() {
		return this.lists.size();
	}
	
	public static void main(String[] args) {
		MyContainer2 myContainer = new MyContainer2();
		Object lock = new Object();
		
		new Thread(() -> {
			synchronized(lock) {
				System.out.println("t2 start");
				if(5 != myContainer.size()) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("size:" + myContainer.size());
				System.out.println("t2 end");
				lock.notify();
			}
		}, "t2").start();
		
		new Thread(() -> {
			synchronized(lock) {
				System.out.println("t1 start");
				for (int i = 0; i < 10; i++) {
					myContainer.add(i);
					System.out.println("adding " + i);
					if(5 == myContainer.size()) {
						lock.notify();
						try {
							System.out.println("t1 释放锁，让唤醒后的 t2 执行");
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("t1 end");
			}
		}, "t1").start();
		
		/*
		new Thread(() -> {
			System.out.println("t1 start");
			for (int i = 0; i < 10; i++) {
				myContainer.add(i);
				System.out.println("adding " + i);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t1").start();
		
		new Thread(() -> {
			System.out.println("t2 start");
			while(true) {// （2）浪费 CPU 资源
				if(5 == myContainer.size()) {// （1）判读结果相等的时候，别的线程有可能又加上了新的元素					
					break;
				}
			}
			System.out.println("t2 found container size be 5");
		}, "t2").start();
		*/		
	}

}
