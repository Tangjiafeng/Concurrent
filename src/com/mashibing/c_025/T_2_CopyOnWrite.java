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
 * 写时复制，新加入元素，则复制一份并加入新元素，将引用指向新的对象
 * 写少读多的场景，考虑使用 CopyOnWriteArrayList
 * @author A
 *
 */
public class T_2_CopyOnWrite {
	public static void main(String[] args) {
		List<String> list = 
//				new ArrayList<>();// 并发写会出问题
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
