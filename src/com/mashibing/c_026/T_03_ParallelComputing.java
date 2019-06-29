package com.mashibing.c_026;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.ecnu.timecost.TimeUtil;

public class T_03_ParallelComputing {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		List<Integer> res = getPrime(1, 200000);
		System.out.println(res.size());
		long end = System.currentTimeMillis();
		TimeUtil.timeCost(start, end);
		// 发挥多核的优势，一般线程池的大小设置为 CPU 核数的倍数。
		ExecutorService service = Executors.newFixedThreadPool(4);
		start = System.currentTimeMillis();
		Future<List<Integer>> future1 = service.submit(new MyTask(1, 150000));
		Future<List<Integer>> future2 = service.submit(new MyTask(150000, 200000));		
		try {
			System.out.println(future1.get().size() + future2.get().size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		end = System.currentTimeMillis();
		service.shutdown();
		TimeUtil.timeCost(start, end);
	}
	
	static class MyTask implements Callable<List<Integer>> {
		int startPos, endPos;
		MyTask(int s, int e) {
			this.startPos = s;
			this.endPos = e;
		}
		@Override
		public List<Integer> call() throws Exception {
			List<Integer> r = new ArrayList<>();
			r = getPrime(startPos, endPos);
			return r;
		}
		
	}
	
	static boolean isPrime(int num) {
		for(int i = 2; i <= num/2; i ++) {
			if(num % i == 0) return false;
		}
		return true;
	}
	
	static List<Integer> getPrime(int start, int end) {
		List<Integer> nums = new ArrayList<>();
		
		for (int i = start; i <= end; i++) {
			if(isPrime(i)) {
				nums.add(i);
			}
		}
		
		return nums;
	}
}
