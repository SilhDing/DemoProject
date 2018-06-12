package cn.mldn.advanced;

import java.util.Date;

public class ReflectDemo {

	public static void main(String[] args) throws Exception {
		
		// Class类对象实例方法
		
		// 调用Object中的getClass()方法
		Date date = new Date();
		Class<?> cls = date.getClass();
		System.out.println(cls);
		
		// 类.class
		Class<?> cls2 = Date.class;
		System.out.println(cls2);
		
		// Class本身的一个方法
		Class<?> cls3 = Class.forName("java.util.Date");
		System.out.println(cls3);
		
	}

}
