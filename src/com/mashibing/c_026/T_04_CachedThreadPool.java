package com.mashibing.c_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T_04_CachedThreadPool {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		System.out.println(service);
		for (int i = 0; i < 2; i++) {
			service.execute(() -> {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " end.");
			});
		}
		
		System.out.println(service);
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(service);
		service.shutdown();
	}
	
}
