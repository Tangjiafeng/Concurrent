package com.mashibing.c_011;

public class T {
	int count = 0;
	synchronized void m() {
		for (int i = 0; i < 1000; i++) {
			count ++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(count);
			if(count == 10) {
				int n = 1/0;// 同步方法产生异常会释放锁
			}
		}
	}
	public static void main(String[] args) {
		T t = new T();
		new Thread(() -> t.m(), "t1").start();
		
		new Thread(() -> t.m(), "t2").start();
	}

}
