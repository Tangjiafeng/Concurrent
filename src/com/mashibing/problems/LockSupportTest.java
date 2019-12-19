package com.mashibing.problems;

import java.util.concurrent.locks.LockSupport;

/**
 * 题目描述：一个线程打印1-9数字，另一个线程打印A-I字母；使两个线程交替打印出1A2B3C...9I。
 *
 * 解法一：LockSupport
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
