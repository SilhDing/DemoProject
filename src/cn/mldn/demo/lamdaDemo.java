package cn.mldn.demo;

/**
 * 
 * lamda 表达式
 *
 * @param <P>
 * @param <R>
 */
interface IText{
	public void print();
}

public class lamdaDemo {
	public static void main(String[] args) {
		fun(new IText() {
			@Override
			public void print() {
				System.out.println("Hello World");
			}
		});
		
		fun(()->System.out.println("print by lamda")); // 单行语句且不带参数
	}
	
	
	public static void fun(IText text) {
		text.print();
	}
}
