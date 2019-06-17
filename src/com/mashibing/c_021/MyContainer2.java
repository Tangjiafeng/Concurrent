package com.mashibing.c_021;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ���һ���̶�����ͬ��������ӵ�� put��get���Լ� getCount ����
 * �ܹ�֧��2���������̣߳��Լ�10���������̵߳���������
 * 
 * ����ȷ�ķ�ʽ�ǲ��� Lock �� Condition 
 * @author zhouy
 *
 */
public class MyContainer2<T> {
	List<T> lists = new ArrayList<T>();
	public static final int MAX = 10;
	
	private Lock lock = new ReentrantLock();
	private Condition producer = lock.newCondition();
	private Condition consumer = lock.newCondition();
	
	
	public T get() {
		T t = null;
		try {
			lock.lock();
			while(lists.size() == 0) {
				consumer.await();
			}
			t = lists.remove(0);
			producer.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return t;
	}
	
	public synchronized void put(T t) {
		try {
			lock.lock();
			while(lists.size() == MAX) {
				producer.await();
			}
			lists.add(t);
			System.out.println(Thread.currentThread().getName() + " put a element");
			consumer.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public synchronized int getCount() {
		return lists.size();
	}
	public static void main(String[] args) {
		MyContainer2<Integer> container = new MyContainer2<>();
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
