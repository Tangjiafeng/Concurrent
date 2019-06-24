package com.mashibing.c_024;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * ģ�⣺ʮ��������Ʊ����
 * ���̰߳�ȫ���������жϲ�����ɾ���������룬�Ҳ�����ԭ���ԣ��ʳ�����󱨴��Ҵ�����ʮ����򣬴������أ���������
 * 
 * ���� Vector����Ȼ�������Ѿ����������������жϲ�����ɾ��������������⣬�м���ӳ٣� Ϊ�˲��Գ�Ӱ���˯�ߣ���ʹ���򱨴�
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
