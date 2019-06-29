package com.mashibing.c_026;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class T_06_ScheduledThreadPool {

	public static void main(String[] args) {
		// �̳߳��е��߳̿�����ִ������������ٴ�ִ���µ�����
		ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
		
		service.scheduleWithFixedDelay(() -> {
			try {
				Thread.sleep(new Random().nextInt(5) * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " end.");
		}, 1, 2, TimeUnit.SECONDS);// ����������ָ��һ�������������һ������ʼ�ӳ�Ϊ2�롣
		
		service.scheduleWithFixedDelay(() -> {
			try {
				Thread.sleep(new Random().nextInt(5) * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " end.");
		}, 0, 4, TimeUnit.SECONDS);
		
	}

}
