package com.mashibing.c_018;

import java.util.ArrayList;
import java.util.List;

/**
 * 一道面试题，创建一个容器类，有两个方法，添加元素和返回容器元素个数
 * 创建两个线程，一个线程往容器写10个元素，一个线程在容器元素达到5个的时候给出提示
 * 成员变量 lists 必须由 volatile 修饰
 * @author A
 *
 */
public class MyContainer1 {
	/*volatile*/ List<Integer> lists = new ArrayList<Integer>();

	public void add(Integer i) {
		lists.add(i);
	}
	
	public int size() {
		return this.lists.size();
	}
	
	public static void main(String[] args) {
		MyContainer1 myContainer = new MyContainer1();
		
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
			while(true) {
				if(5 == myContainer.size()) {					
					break;
				}
			}
			System.out.println("t2 found container size be 5");
		}, "t2").start();
	}

}
