package com.mashibing.problems;

import java.util.concurrent.locks.LockSupport;

/**
 * ��Ŀ������һ���̴߳�ӡ1-9���֣���һ���̴߳�ӡA-I��ĸ��ʹ�����߳̽����ӡ��1A2B3C...9I��
 *
 * �ⷨһ��LockSupport
 */
public class LockSupportTest {
    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        String aC = "ABCDEFGHI";
        String aI = "123456789";

        t1 = new Thread(() -> {
            for (char c : aI.toCharArray()) {
                System.out.print(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "T1");

        t2 = new Thread(() -> {
            for (char c : aC.toCharArray()) {
                LockSupport.park();
                System.out.print(c);
                LockSupport.unpark(t1);
            }
        }, "T2");

        t1.start();
        t2.start();
    }
}
