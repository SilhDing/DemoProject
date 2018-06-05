package cn.mldn.advanced;

public class StringBufferDemo {
	public static void main(String[] args) {
		StringBuffer buf = new StringBuffer();
		buf.append("Hello ").append("World ").append("!!");
		change(buf);
		System.out.println(buf);
		
		// StringBuffer和String之类的转换
		// String变成StringBuffer：构造器或者append
		StringBuffer sb = new StringBuffer("Hello ");
		sb.append("World");
		// StringBuffer变成String
		String str = sb.toString();
		System.out.println(str);
		
		// String有一个方法可以和StringBuffer比较
		System.out.println("hello".contentEquals(sb));
		
		//常用的方法 这些操作返回一个相当于this的StringBuffer，即sb本身也会改变
		// 字符串反转
		System.out.println(sb.reverse());
		
		// 指定位置增加数据 
		System.out.println(sb.insert(0, "MLDN"));
		
		// 删除部分数据
		System.out.println(sb.delete(5, 11));
		
	}
	public static void change(StringBuffer temp) {
		temp.append(" ").append("Hello MLDN.");
	}
}
