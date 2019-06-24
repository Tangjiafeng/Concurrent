package com.mashibing.c_024;

import java.util.ArrayList;
import java.util.List;

/**
 * ģ�⣺ʮ��������Ʊ����
 * ���̰߳�ȫ���������жϲ�����ɾ���������룬������ԭ���ԣ��ʳ�����󱨴��Ҵ�����ʮ�����
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
