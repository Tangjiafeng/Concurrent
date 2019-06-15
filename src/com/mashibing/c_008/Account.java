package com.mashibing.c_008;

import java.util.concurrent.TimeUnit;
/**
 * 一个对象同步方法执行过程中，可以执行非同步方法
 * 银行账户的例子中，如果同步方法 set() 的过程中，执行了非同步的 get() 方法，则会出现脏读现象
 * 解决脏读可以对 get() 加锁
 * @author zhouy
 *
 */
public class Account {
	private String name;	
	double banlance;
	
	public synchronized void set(String name, double banlance) {
		System.out.println("start");
		this.name = name;
		System.out.println("end");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.banlance = banlance;
	}
	public synchronized String getName() {
		return name;
	}
	public synchronized double getBanlance() {
		return banlance;
	}
	public static void main(String[] args) {
		Account a = new Account();
		new Thread(()->a.set("xumengjin", 100.0)).start();// 第二个拿到锁
		
		System.out.println("name:" + a.getName()); // 第一个拿到锁
		System.out.println(a.getBanlance());// 第三个拿到锁
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(a.getName());
		System.out.println(a.getBanlance());
	}

}
