package com.mashibing.c_026;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class T_02_FutureTask {

	public static void main(String[] args) {
		FutureTask<Integer> task = new FutureTask<>(() -> {
			Thread.sleep(1000);
			return 1000;
		});
		
		new Thread(task).start();
		
		try {
			// get() ����������ֻ���������������ֵ���زŻ�ִ�С�
			System.out.println(task.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		ExecutorService service = Executors.newFixedThreadPool(5);
		
		Future<Integer> f = service.submit(() -> {
			Thread.sleep(1000);
			return 1;
		});
		
		System.out.println(f.isDone());
		try {
			System.out.println(f.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println(f.isDone());
		service.shutdown();
	}

}
