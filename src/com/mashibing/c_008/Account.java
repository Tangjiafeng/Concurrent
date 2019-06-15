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
		new Thread(()->a.set("xumengjin", 100.0)).start();// �ڶ����õ���
		
		System.out.println("name:" + a.getName()); // ��һ���õ���
		System.out.println(a.getBanlance());// �������õ���
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(a.getName());
		System.out.println(a.getBanlance());
	}

}
