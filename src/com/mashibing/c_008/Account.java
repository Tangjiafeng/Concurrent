package com.mashibing.c_008;

import java.util.concurrent.TimeUnit;
/**
 * һ������ͬ������ִ�й����У�����ִ�з�ͬ������
 * �����˻��������У����ͬ������ set() �Ĺ����У�ִ���˷�ͬ���� get() �������������������
 * ���������Զ� get() ����
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
