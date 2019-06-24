package com.mashibing.c_024;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 模拟：十个窗口卖票程序
 * 非线程安全的容器，判断操作，删除操作分离，且不具有原子性，故程序最后报错，且处理不到十万程序，存在卖重，超量销售
 * 
 * 改用 Vector，虽然方法都已经加锁，但仍需解决判断操作，删除操作分离的问题，中间的延迟（ 为了测试出影响可睡眠）会使程序报错
 * 
 * 解决上面的问题，使判断和删除集合到一次原子性操作，需要对两个方法一起加锁，此时一般的容器也能满足要求，但效率不高，每
 * 卖一次票需要锁定整个票容器，没有并发性
 * @author A
 *
 */
public class TicketSeller3 {
	volatile static List<String> tickets = new LinkedList<String>();
	
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
				synchronized (tickets) {
					while(true) {
						if(tickets.size() <= 0) break;
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}						
						System.out.println("sold " + tickets.remove(0));
					}
				}
				/*
				while(tickets.size() > 0) {
					
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					System.out.println("sold " + tickets.remove(0));
				}
				*/
			}, "t_" + i));
		}
		
		ths.forEach(o -> {
			o.start();
		});
		
		System.out.println("Finished!");
	}
}
