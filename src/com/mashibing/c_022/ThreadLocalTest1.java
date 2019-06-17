package com.mashibing.c_022;

import java.util.concurrent.TimeUnit;

public class ThreadLocalTest1 {
//	volatile Person p = new Person();
	static ThreadLocal<Person> tlp = new ThreadLocal<Person>();
	
	public static void main(String[] args) {
		
		new Thread(() -> {			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			System.out.println(p.name);
			System.out.println(tlp.get());
		}).start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(() -> {
			tlp.set(new Person());
//			System.out.println(p.name);
			System.out.println(tlp.get().name);
		}).start();
	}

}

class Person {
	String name = "zhangsan";
}
