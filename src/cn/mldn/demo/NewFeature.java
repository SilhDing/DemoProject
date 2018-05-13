package cn.mldn.demo;
import static cn.mldn.util.MyMath.*;  // 静态导入

public class NewFeature {
	public static void main(String[] args) {
		oper();
	}
	
	// 多参数函数
	public static int add(int ... data) {
		int sum = 0;
		for (int x: data) sum += x;
		return sum;
	}
	
	public static void oper() {
		System.out.println(add(1,2,3,4,5));
		try {
			System.out.println(div(10, 2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
