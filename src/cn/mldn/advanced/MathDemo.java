package cn.mldn.advanced;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

class MyMath{
	/**
	 * 
	 * @param num
	 * @param scale
	 * @return
	 */
	public static double round(double num, int scale) {
		BigDecimal bigA = new BigDecimal(num);
		BigDecimal bigB = new BigDecimal(1);
		return bigA.divide(bigB, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}

public class MathDemo {

	public static void main(String[] args) {
		System.out.println(Math.round(15.5));
		System.out.println(Math.round(-15.5));
		System.out.println(Math.round(-15.51));
		
		Random rand = new Random();
		for (int x = 0; x < 10; x ++) {
			System.out.print(rand.nextInt(100) + ", ");
		}
		System.out.println();
		
		// 36选7
		int[] data  = new int[7];
		int foot = 0;
		while (foot < 7) {
			int t = rand.nextInt(37);
			if (!isRepeat(data, t)) {
				data[foot++] = t;
			}
		}
		java.util.Arrays.sort(data);
		for (int x = 0; x < data.length; x++) {
			System.out.print(data[x] + " ,");
		}
		System.out.println();
		
		// BigInteger
		BigInteger bigA = new BigInteger("222223232443432432425234343");
		BigInteger bigB = new BigInteger("9909080808");
		System.out.println("加法：" + (bigA.add(bigB)));
		System.out.println("减法：" + (bigA.subtract(bigB)));
		System.out.println("乘法：" + (bigA.multiply(bigB)));
		System.out.println("除法：" + (bigA.divide(bigB)));
		
		//BigDecimal
		//实现精准的四舍五入
		System.out.println(MyMath.round(19.3468936642364893, 2));
		System.out.println(MyMath.round(-15.5, 0));
		System.out.println(MyMath.round(15.5, 0));
		
	}
	/**
	 * 
	 * @param temp
	 * @param num
	 * @return
	 */
	public static boolean isRepeat(int[] temp, int num) {
		if (num == 0) {
			return true;
		}
		for (int x = 0; x < temp.length; x++) {
			if (temp[x] == num) {
				return true;
			}
		}
		return false;
	}

}
