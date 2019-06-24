package com.mashibing.c_025;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ConcurrentLinkedQueue，offer() 在尾部添加，poll() 返回头部元素并删除
 * peek() 返回头部元素，但不删除，element()，与 peek() 不同的是队列为空则抛出异常
 * @author A
 */
public class T_3_ConcurrentQueue {

	public static void main(String[] args) {
		Queue<String> strs = 
				new ConcurrentLinkedQueue<>();
//				new ConcurrentLinkedDeque<>();// 双端队列，增删元素方法更丰富。
		
		
		for (int i = 0; i < 10; i++) {
			strs.offer("a_" + i);// 添加元素，并返回是否添加成功。普通的 add 方法当队列满时会报错
		}
		
		System.out.println(strs);
		
		System.out.println(strs.poll());
		System.out.println(strs.peek());
		System.out.println(strs.element());
		System.out.println(strs.size());
	}
}
