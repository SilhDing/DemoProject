package cn.mldn.demo;

class Message<T> {
	private T msg;
	public void setMsg(T msg) {
		this.msg = msg;
	}
	public T getMsg() {
		return msg;
	}
}

public class WildcardDemo {
	public static void main(String[] args) {
		Message<Integer> ml = new Message<Integer>();
		ml.setMsg(100);
		fun(ml);
	}
	
	public static void fun(Message<?> temp) {
		System.out.println(temp.getMsg());
	}
}
