package com.mashibing.c_025;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ConcurrentLinkedQueue��offer() ��β����ӣ�poll() ����ͷ��Ԫ�ز�ɾ��
 * peek() ����ͷ��Ԫ�أ�����ɾ����element()���� peek() ��ͬ���Ƕ���Ϊ�����׳��쳣
 * @author A
 */
public class T_3_ConcurrentQueue {

	public static void main(String[] args) {
		Queue<String> strs = 
				new ConcurrentLinkedQueue<>();
//				new ConcurrentLinkedDeque<>();// ˫�˶��У���ɾԪ�ط������ḻ��
		
		
		for (int i = 0; i < 10; i++) {
			strs.offer("a_" + i);// ���Ԫ�أ��������Ƿ���ӳɹ�����ͨ�� add ������������ʱ�ᱨ��
		}
		
		System.out.println(strs);
		
		System.out.println(strs.poll());
		System.out.println(strs.peek());
		System.out.println(strs.element());
		System.out.println(strs.size());
	}
}
