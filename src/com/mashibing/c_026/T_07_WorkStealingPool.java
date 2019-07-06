package com.mashibing.c_026;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T_07_WorkStealingPool {

	public static void main(String[] args) throws IOException {
		// ��ӡ CPU ����
		System.out.println(Runtime.getRuntime().availableProcessors());
		ExecutorService service = Executors.newWorkStealingPool(); 
		
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(1000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));// CPU������ʱΪ 8���ʵھŸ������ȴ�ִ����������̣߳�Ȼ�󽻸���ִ�С�
		
		System.in.read();
	}

	static class R implements Runnable {
		int time;

		R(int t) {
			this.time = t;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(this.time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " " + this.time);
		}
		
	}
}
