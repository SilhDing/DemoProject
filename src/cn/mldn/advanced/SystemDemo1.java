package cn.mldn.advanced;

class Member {
	public Member() {
		System.out.println("A new instance is created!");
	}
	
	@Override
	protected void finalize() throws Throwable { // 不管是有error还是exception，都会执行
		System.out.println("this one is dead");
		throw new Exception("this one is still not dead");
	}
}

public class SystemDemo1 {

	public static void main(String[] args) {
		
		// 获取系统当前的时间
		long start = System.currentTimeMillis();
		String str = "";
		for (int x = 0; x < 3000; x++) {
			str += x;
		}
		long end = System.currentTimeMillis();
		System.out.println("Time used: " + (end - start));
		
		
		// finalize的方法
		Member mem = new Member();
		mem = null;
		System.gc();
		
	}

}
