package cn.mldn.advanced;

class MyThread extends Thread {
	private String name;
	public MyThread(String name) {
		super();
		this.name = name;
	}
	@Override
	public void run() {
		for (int x = 0; x < 200; x ++) {
			System.out.println(this.name + "->" + x);
		}
	}
}

class MyThread2 implements Runnable{
	private String name;
	public MyThread2(String name) {
		super();
		this.name = name;
	}
	@Override
	public void run() {
		for (int x = 0; x < 200; x ++) {
			System.out.println(this.name + "->" + x);
		}
	}
}

public class ThreadDemo {
	public static void main(String[] args) {
		MyThread mt1 = new MyThread("线程A");
		MyThread mt2 = new MyThread("线程B");
		MyThread mt3 = new MyThread("线程C");
		mt1.start();
		mt2.start();
		mt3.start();
		// 如果上面用的是run()的话，则没有多线程实现
		
		MyThread2 mt4 = new MyThread2("线程D");
		MyThread2 mt5 = new MyThread2("线程E");
		MyThread2 mt6 = new MyThread2("线程F");
		new Thread(mt4).start();
		new Thread(mt5).start();
		new Thread(mt6).start();
	}
}
