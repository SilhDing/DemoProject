package cn.mldn.advanced;

public class RuntimeThread {
	public static void main(String args[]) {
		Runtime run = Runtime.getRuntime();
		System.out.println("1, Max: " + run.maxMemory());
		System.out.println("1, Total: " + run.totalMemory());
		System.out.println("1, Free: " + run.freeMemory());
		
		String str = "";
		
		for (int x = 0; x < 3000; x++) {
			str += x; // 产生大量的垃圾，要是用StringBuffer就不会
		}
		
		System.out.println("2, Max: " + run.maxMemory());
		System.out.println("2, Total: " + run.totalMemory());
		System.out.println("2, Free: " + run.freeMemory());
		
		run.gc();
		System.out.println("3, Max: " + run.maxMemory());
		System.out.println("3, Total: " + run.totalMemory());
		System.out.println("3, Free: " + run.freeMemory());
		
		
	}
}
