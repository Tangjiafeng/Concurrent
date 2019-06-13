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
		this.name = name;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.banlance = banlance;
	}
	public String getName() {
		return name;
	}
	public /*synchronized*/ double getBanlance() {
		return this.banlance;
	}
	public static void main(String[] args) {
		Account a = new Account();
		new Thread(()->a.set("xumengjin", 100.0)).start();
		System.out.println(a.getName());
		System.out.println(a.getBanlance());
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(a.getName());
		System.out.println(a.getBanlance());
	}

}
