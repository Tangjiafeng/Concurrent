package com.mashibing.c_026;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class T_08_ForkJoinPool {
	static int[] nums = new int[1000000];
	static final int MAXSUM = 50000;
	static Random r = new Random();
	static {
		for (int i = 0; i < nums.length; i++) {
			nums[i] = r.nextInt(100);			
		}
		
		System.out.println(Arrays.stream(nums).sum());
	}
	
	// RecursiveAction û�з���ֵ��RecursiveTask �з���ֵ��
	/*static class AddTask extends RecursiveAction {
		
		private static final long serialVersionUID = 1L;
		int start;
		int end;
		
		public AddTask(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		protected void compute() {
			if(end - start < MAXSUM) {
				long sum = 0L;
				for (int i = start; i < end; i++) {
					sum += nums[i];
				}
				System.out.println("from " + start + " to " + end + ": " + sum);
			} else {
				int middle = start + (end - start) / 2;
				AddTask t1 = new AddTask(start, middle);
				AddTask t2 = new AddTask(middle, end);
				t1.fork();
				t2.fork();
			}
		}
	}*/
	// RecursiveAction û�з���ֵ��RecursiveTask<T> �з���ֵ���Խ�������ܡ�
		static class AddTask extends RecursiveTask<Long> {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			int start;
			int end;
			
			public AddTask(int start, int end) {
				this.start = start;
				this.end = end;
			}
			
			@Override
			protected Long compute() {
				if(end - start < MAXSUM) {
					long sum = 0L;
					for (int i = start; i < end; i++) {
						sum += nums[i];
					}
					return sum;
				}
				int middle = start + (end - start) / 2;
				AddTask t1 = new AddTask(start, middle);
				AddTask t2 = new AddTask(middle, end);
				t1.fork();
				t2.fork();
				return t1.join() + t2.join();
			}
		}
	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		AddTask task = new AddTask(0, nums.length);
		pool.execute(task);
		long result = (long) task.join();// join() ������
		System.out.println(result);
		
		// ��̨�̣߳��������ܿ���ִ�н����
		/*try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
}
