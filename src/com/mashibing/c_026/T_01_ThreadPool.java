package com.mashibing.c_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T_01_ThreadPool {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(5);
		
		for (int i = 0; i < 6; i++) {
			service.execute(() -> {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println(Thread.currentThread().getName());
			});
		}
		
		System.out.println(service);
		// �����رգ���ȴ�����ִ�н���
		service.shutdown();
		/*
		 * �����رգ����񽫱���ϣ���ͼ����ֹͣ����ʵ�ϲ�һ������ shutdown() һ������ֹͣ�����ⲿ�ύ�����񣬺��Զ�����ȴ�������
		 * ���Խ������ܵ����� interrupt �жϣ�����δִ�е������б�����ͼ��ֹ�̵߳ķ�����ͨ������ Thread.interrupt() ����
		 * ��ʵ�ֵģ����Ǵ��֪�������ַ������������ޣ�����߳���û�� sleep��wait��Condition����ʱ����Ӧ��, interrupt()
		 * �������޷��жϵ�ǰ���̵߳ġ����ԣ�ShutdownNow() ���������̳߳ؾ�һ�����������˳�����Ҳ���ܱ���Ҫ�ȴ���������ִ�е�����
		 * ��ִ������˲����˳������Ǵ����ʱ�����������˳��ġ�		
		*/
//		service.shutdownNow();
		System.out.println(service);// shutting down
		System.out.println(service.isShutdown());// true: shutting down		
		System.out.println(service.isTerminated());// false
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(service);
		System.out.println(service.isShutdown());
		System.out.println(service.isTerminated());
	}

}
