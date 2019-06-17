package com.mashibing.c_021;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ���һ���̶�����ͬ��������ӵ�� put��get���Լ� getCount ����
 * �ܹ�֧��2���������̣߳��Լ�10���������̵߳���������
 * 
 * ʹ�� wait �� notify/notifyAll ��ʵ��
 * @author zhouy
 *
 */
public class MyContainer1<T> {
	List<T> lists = new ArrayList<T>();
	public static final int MAX = 10;
	
	public synchronized T get() {
		while(lists.size() == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notifyAll();		
		return lists.remove(0);
	}
	
	public synchronized void put(T t) {
		while(lists.size() == MAX) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		lists.add(t);
		System.out.println(Thread.currentThread().getName() + " put a element");
		this.notifyAll();
	}
	
	public synchronized int getCount() {
		return lists.size();
	}
	public static void main(String[] args) {
		MyContainer1<Integer> container = new MyContainer1<>();
		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + " start");
				for (int j = 0; j < 25; j++) {
					container.put(new Random().nextInt(5));					
				}
			}, "p" + i).start();
		}
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + " start");
				for (int j = 0; j < 5; j++) {
					System.out.println(Thread.currentThread().getName() + " got " + container.get());
				}
			}, "c" + i).start();
		}
	}

}
