package com.mashibing.c_024;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟：十个窗口卖票程序
 * 非线程安全的容器，判断操作，删除操作分离，不具有原子性，故程序最后报错，且处理不到十万程序
 * @author A
 *
 */
public class TicketSeller1 {
	/*volatile */static List<String> tickets = new ArrayList<String>();
	
	static {
		for (int i = 0; i < 10000; i++) {
			tickets.add("T_" + i);
		}
	}
	
	public static void main(String[] args) {
		List<Thread> ths = new ArrayList<Thread>();
		for (int i = 0; i < 10; i++) {
			ths.add(new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + " start");
				while(tickets.size() > 0) {
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
