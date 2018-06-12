package cn.mldn.advanced;

import java.lang.reflect.Method;

public class ReflectMethodDemo {

	public static void main(String[] args) throws Exception {
		String fieldName = "title";
		
		Class<?> cls = Class.forName("cn.mldn.advanced.reflectHelp.Book2");
		Object obj = cls.newInstance();
		Method setMet = cls.getMethod("set" + initCap(fieldName), String.class);
		Method getMet = cls.getMethod("get" + initCap(fieldName));
		
		setMet.invoke(obj, "java dep");
		System.out.println(getMet.invoke(obj));
		
	}
	
	public static String initCap(String str) {
		return str.substring(0,1).toUpperCase() + str.substring(1);
	}

}
