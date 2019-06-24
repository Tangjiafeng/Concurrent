package com.mashibing.c_025;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * ��������ʵ��������������
 * @author A
 *
 */
public class T_4_LinkedBlockingQueue {
	static BlockingQueue<String> strs = new LinkedBlockingQueue<>();

	public static void main(String[] args) {
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				try {
					strs.put("a_" + i);// ���Ԫ��ʱ������ˣ��ͻ�ȴ�
					Thread.sleep(10 * new Random().nextInt(10));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " put " + "a_" + i);
			}
		}, "producer").start();
		
		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				while(true) {
					try {
						Thread.sleep(100 * new Random().nextInt(10));
						System.out.println(Thread.currentThread().getName() + " take " + strs.take());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "consumer_" + i).start();
		}
	}

}
