package com.mashibing.c_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T_05_SingleThreadPool {

	public static void main(String[] args) {
		ExecutorService service = Executors.newSingleThreadExecutor();
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
		service.shutdown();
	}

}
