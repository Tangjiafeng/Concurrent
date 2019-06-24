package com.mashibing.c_025;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TransferQueue;

public class T_6_TransferQueue {

	public static void main(String[] args) {
		TransferQueue<String> strs = 
				new LinkedTransferQueue<>();
				new SynchronousQueue<>();// ����� TransferQueue ������Ϊ�㣬��Ϣ����ʵʱ����
		
		new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		try {
			strs.transfer("a");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*
		new Thread(() -> {
			try {
				strs.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		*/
	}

}
