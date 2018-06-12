package cn.mldn.advanced;

import java.lang.reflect.Constructor;

public class ReflectConstuctorDemo {

	public static void main(String[] args) throws Exception {
		Class<?> cls = Class.forName("cn.mldn.advanced.reflectHelp.Book");
		// Object obj = cls.newInstance();  // 没有无参构造的话，这个会有错误
		Constructor<?> con = cls.getConstructor(String.class, double.class);
		Object obj = con.newInstance("Java Dev", 34.1);
		System.out.println(obj);
		
	}

}
