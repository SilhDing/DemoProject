package cn.mldn.demo;

import cn.mldn.util.MyMath;

public class InitParameters {
	public static void main(String[] args) {
		for (int x = 0; x < args.length; x++) {
			System.out.println(args[x]);
		}
		System.out.println("shortcuts");
		try {
			MyMath.div(10, 2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
