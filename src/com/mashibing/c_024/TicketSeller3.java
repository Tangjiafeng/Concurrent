package com.mashibing.c_024;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * ģ�⣺ʮ��������Ʊ����
 * ���̰߳�ȫ���������жϲ�����ɾ���������룬�Ҳ�����ԭ���ԣ��ʳ�����󱨴��Ҵ�����ʮ����򣬴������أ���������
 * 
 * ���� Vector����Ȼ�������Ѿ����������������жϲ�����ɾ��������������⣬�м���ӳ٣� Ϊ�˲��Գ�Ӱ���˯�ߣ���ʹ���򱨴�
 * 
 * �����������⣬ʹ�жϺ�ɾ�����ϵ�һ��ԭ���Բ�������Ҫ����������һ���������ʱһ�������Ҳ������Ҫ�󣬵�Ч�ʲ��ߣ�ÿ
 * ��һ��Ʊ��Ҫ��������Ʊ������û�в�����
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
