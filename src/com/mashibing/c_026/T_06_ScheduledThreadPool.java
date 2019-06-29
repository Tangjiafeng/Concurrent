package com.mashibing.c_026;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class T_06_ScheduledThreadPool {

	public static void main(String[] args) {
		// 线程池中的线程可以在执行任务结束后，再次执行新的任务
		ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
		
		service.scheduleWithFixedDelay(() -> {
			try {
				Thread.sleep(new Random().nextInt(5) * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " end.");
		}, 1, 2, TimeUnit.SECONDS);// 第三个参数指上一次任务结束到下一次任务开始延迟为2秒。
		
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
