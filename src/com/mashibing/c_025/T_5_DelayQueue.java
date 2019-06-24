package com.mashibing.c_025;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 定时执行任务
 * @author A
 *
 */
public class T_5_DelayQueue {
	static BlockingQueue<MyTask> tasks = new DelayQueue<>();
	static class MyTask implements Delayed {
		long runningTime;
		MyTask(long rt) {
			this.runningTime = rt;
		}
		@Override
		public int compareTo(Delayed o) {
			if(this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
				return -1;
			} else if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS) ) {
				return 1;
			} else {
				return 0;
			}			
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		}
		@Override
		public String toString() {
			return "" + runningTime;
		}
	}
	public static void main(String[] args) {
		long now = System.currentTimeMillis();
		MyTask t1 = new MyTask(now + 1000);// 一秒钟后执行，按照 delay 时间到期的顺序决定被 take 的顺序
		MyTask t2 = new MyTask(now + 2000);
		MyTask t3 = new MyTask(now + 3000);
		MyTask t4 = new MyTask(now + 4000);
		try {
			tasks.put(t1);
			tasks.put(t2);
			tasks.put(t3);
			tasks.put(t4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(tasks);
		for (int i = 0; i < 5; i++) {
			try {
				System.out.println(tasks.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
