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
		// 正常关闭，会等待任务执行结束
		service.shutdown();
		/*
		 * 立即关闭，任务将被打断，企图立即停止，事实上不一定：跟 shutdown() 一样，先停止接收外部提交的任务，忽略队列里等待的任务，
		 * 尝试将正在跑的任务 interrupt 中断，返回未执行的任务列表，它试图终止线程的方法是通过调用 Thread.interrupt() 方法
		 * 来实现的，但是大家知道，这种方法的作用有限，如果线程中没有 sleep、wait、Condition、定时锁等应用, interrupt()
		 * 方法是无法中断当前的线程的。所以，ShutdownNow() 并不代表线程池就一定立即就能退出，它也可能必须要等待所有正在执行的任务
		 * 都执行完成了才能退出。但是大多数时候是能立即退出的。		
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
