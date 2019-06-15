package com.mashibing.c_018;

import java.util.ArrayList;
import java.util.List;

/**
 * һ�������⣬����һ�������࣬���������������Ԫ�غͷ�������Ԫ�ظ���
 * ���������̣߳�һ���߳�������д10��Ԫ�أ�һ���߳�������Ԫ�شﵽ5����ʱ�������ʾ
 * ��Ա���� lists ������ volatile ����
 * 
 * ���ƣ���1�����Ǻܾ�ȷ����2���˷� CPU
 * 
 * ���� wait notify
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
							System.out.println("t1 �ͷ������û��Ѻ�� t2 ִ��");
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
			while(true) {// ��2���˷� CPU ��Դ
				if(5 == myContainer.size()) {// ��1���ж������ȵ�ʱ�򣬱���߳��п����ּ������µ�Ԫ��					
					break;
				}
			}
			System.out.println("t2 found container size be 5");
		}, "t2").start();
		*/		
	}

}
