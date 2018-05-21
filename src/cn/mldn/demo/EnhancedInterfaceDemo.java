package cn.mldn.demo;

interface IMail {
	public void print();
	default void fun() {
		System.out.println("是不是很毁三观？");
	}
	static void get() {
		System.out.println("接口直接调用！！！");
	}
}

class Mail implements IMail {
	@Override
	public void print() {
		System.out.println("Hello world!");
	}
}

public class EnhancedInterfaceDemo {
	public static void main(String[] args) {
		IMail msg = new Mail();
		msg.fun();
		IMail.get(); // 只有接口可以调用
	}
}
