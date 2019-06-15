package com.mashibing.c_012;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.ecnu.timecost.TimeUtil;

/**
 * ����ԭ����ͬʱ���� synchronized Ч�ʵķ���ʹ�� Atomxxx ����ԭ�Ӽ��㣬�ײ��Ż�
 * @author A
 *
 */
public class TTT {
	volatile AtomicInteger count = new AtomicInteger(0);
	
	void m() {
		for (int i = 0; i < 100000; i++) {
			count.incrementAndGet();
		}
	}
	
	public static void main(String[] args) {
		TTT ttt = new TTT();
		List<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(ttt::m, i + "_thread"));
		}
		long start = System.currentTimeMillis();
		threads.forEach(o -> o.start());
		
		threads.forEach(o -> {
			try {
				o.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		long end = System.currentTimeMillis();
		TimeUtil.timeCost(start, end);
		System.out.println(ttt.count);
	}

}
