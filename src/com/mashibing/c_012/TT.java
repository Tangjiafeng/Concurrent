package com.mashibing.c_012;

import java.util.ArrayList;
import java.util.List;

import com.ecnu.timecost.TimeUtil;
/**
 * volatile 无法保证原子性的实例 m()
 * 当多个线程都要访问 count 变量时，即便加了 volatile 修饰符，每个线程对 count 的自增操作无法保持原子性
 * 故十个线程，每个线程自增一万次，总和达不到十万次，解决方法之一就是同步方法，每次加完再进行下一次
 * @author A
 *
 */
public class TT {
	volatile int count = 0;
	
	synchronized void m() {
		for (int i = 0; i < 100000; i++) {
			count ++;
		}
	}

	public static void main(String[] args) {
		List<Thread> threads = new ArrayList<Thread>();
		TT tt = new TT();
		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(tt::m, i + "_thread"));
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
		System.out.println(tt.count);
	}

}
