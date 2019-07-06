package com.ecnu.timecost;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Java 8 流的并行计算
 * @author A
 *
 */
public class Test_ParallelStream {

	public static void main(String[] args) {
		List<Integer> lists = new ArrayList<Integer>();
		Random r = new Random();
		
		for (int i = 0; i < 1000000; i++) {
			lists.add(r.nextInt(1000) + 1);
		}
		
		long start = System.currentTimeMillis();
		lists.forEach(v -> isPrime(v));
		long end = System.currentTimeMillis();
		System.out.print("传统：");
		TimeUtil.timeCost(start, end);
		
		start = System.currentTimeMillis();
		lists.parallelStream().forEach(Test_ParallelStream::isPrime);
		end = System.currentTimeMillis();
		System.out.print("并行：");
		TimeUtil.timeCost(start, end);
	}
	
	public static boolean isPrime(int num) {
		for (int i = 2; i < num / 2 + 1; i++) {
			if(num % i == 0) return false;
		}
		return true;
	}

}
