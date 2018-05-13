package cn.mldn.demo;

class Point{
	private Object x;
	private Object y;
	public void setX(Object x) {
		this.x = x;
	}
	public void setY(Object y) {
		this.y = y;
	}
	public Object getX() {
		return x;
	}
	public Object getY() {
		return y;
	}
}

class GenericPoint<T>{
	private T x;
	private T y;
	public void setX(T x) {
		this.x = x;
	}
	public void setY(T y) {
		this.y = y;
	}
	public T getX() {
		return x;
	}
	public T getY() {
		return y;
	}
	
	
}

public class Generic {
	public static void main(String[] args) {
		Point p1 = new Point();
		p1.setX(10);
		p1.setY(20);
		int x = (Integer) p1.getX();
		int y = (Integer) p1.getY();
		System.out.println(x + ", " + y);
		
		
		// 这就是一个错误的使用例子
		/*
		Point p2 = new Point();
		p2.setX("dedede");
		p2.setY(10);
		String x2 = (String) p2.getX();
		String y2 = (String) p2.getY();
		System.out.println(x2 + ", " + y2);
		*/
		
		GenericPoint<String> p = new GenericPoint<String>();// 可以省略，后面不加String
		p.setX("aaa");
		p.setY("bbb");
		
		String x1 = p.getX();
		String y1 = p.getY();
		System.out.println(x1 + ", " + y1);
		
		
		String str = fun("Hello");
		System.out.println(str.length());
			
	}
	
	// 泛型还可以用在方法里面
	public static <T> T fun(T t) { // T的类型由传入的参数决定
		return t;
	}
}
