package cn.mldn.demo;


class Movie {
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "this is a movie";
	}
	
	@Deprecated
	public void fun() {
		System.out.println("this is a dead function!");
	}
	
}

class Yihang<T> {
	private T title;
	
	public void setTitle(T title) {
		this.title = title;
	}
	public T getTitle() {
		return title;
	}
	
}

public class AnnotationDemo {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new Movie());
		new Movie().fun();		
		Yihang yi = new Yihang();
		yi.setTitle("hang");  // 直接用 cmd + 1 增加suppresswarning
	}

}
