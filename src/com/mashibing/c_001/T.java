package com.mashibing.c_001;

public class T implements Runnable {
	private int count = 10;
		
	public static void main(String[] args) {
		T t = new T();
		
		for (int i = 0; i < 5; i++) {
			new Thread(t, "Thread_" + i).start();
		}
	}

	@Override
	public synchronized void run() {// synchronized 标记的方法体的执行是原子性的，不可分，保证count值
		this.count --;              // 在并发环境下的正确性。
		System.out.println(Thread.currentThread().getName() + " count = " + this.count);
	}

}
