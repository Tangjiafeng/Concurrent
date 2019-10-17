package com.mashibing.c_012;

import java.util.concurrent.TimeUnit;
/**
 * volatile ���εı����������ڲ�ͬ���̱߳��ֿɼ���
 * ���߳��޸� running Ϊ false��������� volatile �ؼ��֣��� CPU һֱ�ӷ��̵߳Ļ����ж�ȡ running ��ֵ����������ѭ��
 * volatile �������̵߳��޸ģ����µ����̵߳��ڴ��У��Ӷ�ֹͣѭ��
 * ��Ȼ��� while ѭ���������������ִ�У�CPU �п��ܻ�ȥ���ڴ棨���̣߳������Լ����ڴ棬��ʱ���� volatile ����Ҳ�п���ֹͣ
 * 
 * volatile ʹ�������ֿɼ��ԣ���������ԭ����
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
