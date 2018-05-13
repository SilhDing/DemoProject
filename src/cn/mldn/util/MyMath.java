package cn.mldn.util;


public class MyMath {
	public static int div(int x, int y) throws Exception {
		return x/y;
	}
	
	public static int add(int ... data) {
		int sum = 0;
		for(int i : data) sum += i;
			return sum;
	}
}