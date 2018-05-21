package cn.mldn.demo;

/**
 * 
 * 实现方法的引用接口
 *
 * @param <P>
 * @param <R>
 */
@FunctionalInterface
interface IConvert<P,R> {
	public R zhuanhuan(P p);
}

/**
 * 
 * 实现方法的引用借口，
 *
 * @param <R>
 */
@FunctionalInterface
interface IUpper<R> {
	public R upper();
}

@FunctionalInterface
interface ICompare<P>{
	public int compare(P p1, P p2);
}

@FunctionalInterface
interface ICreate<C>{
	public C create (String t, double p);
	
}

class Book2{
	private String title;
	private double price;
	public Book2(String title, double price) {
		this.price = price;
		this.title = title;
	}
	
	@Override
	public String toString() {
		return "title: " + this.title + "\nprice: " + this.price;
	}
}

public class MethodRefDemo {

	public static void main(String[] args) {
		
		//类中静态方法的调用
		IConvert<Integer, String> conv = String::valueOf;
		// conv中用valueOf来覆写一个方法
		String str = conv.zhuanhuan(1000);
		System.out.println(str.replaceAll("0", "9"));
		
		//普通方法的调用
		IUpper<String> msg = "Hello"::toUpperCase;
		String str2 = msg.upper();
		System.out.println(str2);
		
		//引用特定类的方法
		ICompare<String> str3 = String :: compareTo;
		System.out.println(str3.compare("A", "B"));
		
		// 引用构造方法
		ICreate <Book2> cre = Book2::new;
		Book2 book = cre.create("Java development", 20.2);
		System.out.println(book);
	}

}
