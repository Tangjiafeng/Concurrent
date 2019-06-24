package com.mashibing.c_023;

/**
 * 线程安全的单例模式
 * @author A
 *
 */
public class Singleton {

	public static Singleton getSingle() {
		return Inner.s;
	}
	
	public Singleton() {
		System.out.println("single");
	}

	private static class Inner {
		private static Singleton s = new Singleton();// 不用加锁，也能实现懒加载
	}
	
	public static void main(String[] args) {

	}

}
