package com.mashibing.c_024;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 模拟：十个窗口卖票程序
 * 非线程安全的容器，判断操作，删除操作分离，且不具有原子性，故程序最后报错，且处理不到十万程序，存在卖重，超量销售
 * 
 * 改用 Vector，虽然方法都已经加锁，但仍需解决判断操作，删除操作分离的问题，中间的延迟（ 为了测试出影响可睡眠）会使程序报错
 * 
 * 
 * @author A
 *
 */
public class TicketSeller2 {
	volatile static Vector<String> tickets = new Vector<String>();
	
	static {
		for (int i = 0; i < 1000; i++) {
			tickets.add("T_" + i);
		}
	}
	
	public static void main(String[] args) {
		List<Thread> ths = new ArrayList<Thread>();
		for (int i = 0; i < 10; i++) {
			ths.add(new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + " start");
				while(tickets.size() > 0) {
					/*
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					*/
					System.out.println("sold " + tickets.remove(0));
				}
			}, "t_" + i));
		}
		
		ths.forEach(o -> {
			o.start();
		});
		
		System.out.println("Finished!");
	}
}
