package com.mashibing.c_025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

import com.ecnu.timecost.TimeUtil;

/**
 * дʱ���ƣ��¼���Ԫ�أ�����һ�ݲ�������Ԫ�أ�������ָ���µĶ���
 * д�ٶ���ĳ���������ʹ�� CopyOnWriteArrayList
 * @author A
 *
 */
public class T_2_CopyOnWrite {
	public static void main(String[] args) {
		List<String> list = 
//				new ArrayList<>();// ����д�������
//				new Vector<>();
				new CopyOnWriteArrayList<>();
		Random r = new Random();
		Thread[] ths = new Thread[100];
		
		CountDownLatch count = new CountDownLatch(ths.length);
		
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < ths.length; i++) {
			ths[i] = new Thread(() -> {
				for (int j = 0; j < 1000; j++) {
					list.add("a" + r.nextInt(100000));
				}
				count.countDown();
			}, "t_" + i);
		}
		Arrays.asList(ths).forEach(o -> o.start());
		try {
			count.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		TimeUtil.timeCost(start, end);
		System.out.println(list.size());
	}

}
