package com.mashibing.c_012;

import java.util.ArrayList;
import java.util.List;

import com.ecnu.timecost.TimeUtil;
/**
 * volatile �޷���֤ԭ���Ե�ʵ�� m()
 * ������̶߳�Ҫ���� count ����ʱ��������� volatile ���η���ÿ���̶߳� count �����������޷�����ԭ����
 * ��ʮ���̣߳�ÿ���߳�����һ��Σ��ܺʹﲻ��ʮ��Σ��������֮һ����ͬ��������ÿ�μ����ٽ�����һ��
 * @author A
 *
 */
public class TT {
	volatile int count = 0;
	
	synchronized void m() {
		for (int i = 0; i < 100000; i++) {
			count ++;
		}
	}

	public static void main(String[] args) {
		List<Thread> threads = new ArrayList<Thread>();
		TT tt = new TT();
		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(tt::m, i + "_thread"));
		}
		long start = System.currentTimeMillis();
		threads.forEach(o -> o.start());
		
		threads.forEach(o -> {
			try {
				o.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		long end = System.currentTimeMillis();
		TimeUtil.timeCost(start, end);
		System.out.println(tt.count);
	}

}
