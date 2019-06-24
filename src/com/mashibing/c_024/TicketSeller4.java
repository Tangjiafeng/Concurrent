package com.mashibing.c_024;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ģ�⣺ʮ��������Ʊ����
 * ���̰߳�ȫ���������жϲ�����ɾ���������룬�Ҳ�����ԭ���ԣ��ʳ�����󱨴��Ҵ�����ʮ����򣬴������أ���������
 * 
 * ���� Vector����Ȼ�������Ѿ����������������жϲ�����ɾ��������������⣬�м���ӳ٣� Ϊ�˲��Գ�Ӱ���˯�ߣ���ʹ���򱨴�
 * 
 * �����������⣬ʹ�жϺ�ɾ�����ϵ�һ��ԭ���Բ�������Ҫ����������һ���������ʱһ�������Ҳ������Ҫ�󣬵�Ч�ʲ��ߣ�ÿ
 * ��һ��Ʊ��Ҫ��������Ʊ������û�в�����
 * 
 * ���ò�������
 * java 1.5�Ժ� Queue:ConcurrentLinkedQueue
 * 
 * Map:��ֵ��
 * 
 * Set:��ֵ
 * @author A
 *
 */
public class TicketSeller4 {
	volatile static Queue<String> tickets = new ConcurrentLinkedQueue<String>();
	
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
				
				while(true) {
					String s = tickets.poll();// ��ȡ���жϣ�poll() ��ԭ���Եģ����Ǽ�����ʵ�֣��� compare and set��Ч�ʸ�
					if(s == null) break;
					else System.out.println("sold " + s);
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
