package com.mashibing.c_018;

import java.util.ArrayList;
import java.util.List;

/**
 * һ�������⣬����һ�������࣬���������������Ԫ�غͷ�������Ԫ�ظ���
 * ���������̣߳�һ���߳�������д10��Ԫ�أ�һ���߳�������Ԫ�شﵽ5����ʱ�������ʾ
 * ��Ա���� lists ������ volatile ����
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
