package cn.mldn.advanced;

import java.util.Arrays;

public class ArraysDemo {

	public static void main(String[] args) {
		int[] data = new int[] {1,45,23,6,2,7,3,9,32};
		java.util.Arrays.sort(data);
		System.out.println(Arrays.toString(data));
		System.out.println(Arrays.binarySearch(data, 9));
		
		int[] data2 = new int[] {1,3,43};
		System.out.println(Arrays.equals(data, data2));
	}

}
