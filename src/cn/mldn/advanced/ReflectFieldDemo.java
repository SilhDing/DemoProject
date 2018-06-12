package cn.mldn.advanced;

import java.lang.reflect.Field;

public class ReflectFieldDemo {

	public static void main(String[] args) throws Exception {
		Class<?> cls = Class.forName("cn.mldn.advanced.reflectHelp.Book3");
		Object obj = cls.newInstance();
		Field titleField = cls.getDeclaredField("title");
		titleField.setAccessible(true); // 这个必须有
		titleField.set(obj, "Java Dep"); 
		System.out.println(titleField.get(obj));
	}

}
