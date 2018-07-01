package cn.mldn.advanced;

import java.lang.reflect.Method;

public class ReflectMethodDemo {

	/**
	 * @Description:    Description
	 * @Package:        cn.mldn.advanced
	 * @ClassName:      ReflectMethodDemo
	 * @Author:         Yihang Ding
	 * @Date:           2018/6/19 21:48
	 **/
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
		/**
		 * @descroption     Description
		 * @method          initCap
		 * @author          Yihang Ding
		 *
		 * @param str
		 * @return          java.lang.String
		 * @date            2018/7/1 16:38
		 */
		return str.substring(0,1).toUpperCase() + str.substring(1);
	}

}
