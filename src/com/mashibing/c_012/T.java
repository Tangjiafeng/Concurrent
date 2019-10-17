package com.mashibing.c_012;

import java.util.concurrent.TimeUnit;
/**
 * volatile 修饰的变量，可以在不同的线程保持可见性
 * 主线程修改 running 为 false，如果不加 volatile 关键字，则 CPU 一直从分线程的缓存中读取 running 的值，会陷入死循环
 * volatile 会让主线程的修改，更新到分线程的内存中，从而停止循环
 * 当然如果 while 循环中有其它的语句执行，CPU 有可能会去主内存（主线程）更新自己的内存，此时不加 volatile 程序也有可能停止
 * 
 * volatile 使变量保持可见性，但不保持原子性
 * @author A
 *
 */
public class T {
	volatile boolean running = true;
	
	void m() {
		System.out.println("m start.");
		while(running) {
			/*try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
		System.out.println("m end.");
	}
	
	public static void main(String[] args) {
		T t = new T();
		Runnable r = new Runnable() {

			@Override
			public void run() {
				t.m();
			}
		};
		
		new Thread(r, "t").start();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t.running = false;
		System.out.println("finished");
	}
}
