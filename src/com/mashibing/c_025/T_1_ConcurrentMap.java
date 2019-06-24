package com.mashibing.c_025;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

import com.ecnu.timecost.TimeUtil;

public class T_1_ConcurrentMap {
	public static void main(String[] args) {
//		Map<String, String> map = new Hashtable<>();
//		Map<String, String> map = new ConcurrentHashMap<>();
		Map<String, String> map = new ConcurrentSkipListMap<>();
//		Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
//		Map<String, String> map = Collections.synchronizedMap(new TreeMap<>());
		
		Random r = new Random();
		Thread[] ths = new Thread[100];
		
		CountDownLatch count = new CountDownLatch(ths.length);
		
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < ths.length; i++) {
			ths[i] = new Thread(() -> {
				for (int j = 0; j < 1000000; j++) {
					map.put("a" + r.nextInt(100000), "a" + r.nextInt(100000));
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
	}

}
