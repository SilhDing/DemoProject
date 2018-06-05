// 已经解决了数据错误的问题，但是重复写和重复读的问题还是没有解决

package cn.mldn.advanced;

class Info3 {
	private String title;
	private String content;
	private boolean flag;
	// flag = true: 可以生产还是还不能消费
	// flag = false: 可以消费还是还不能生产
	public synchronized void set(String title, String content) {
		if (this.flag == false) {
			try {
				super.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.title = title;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.content = content;
		this.flag = false;
		super.notify();
	}
	public synchronized void get() {
		if (this.flag == true) {
			try {
				super.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this.title + " - " + this.content);
		this.flag = true;
		super.notify();
	}
}

class Productor3 implements Runnable {
	private Info3 info;
	public Productor3(Info3 info) {
		this.info = info;
	}
	@Override
	public void run() {
		for (int x = 0; x < 100; x ++) {
			if (x % 2 == 0) {
				this.info.set("title is A", "content is A");
			} else {
				this.info.set("title is B", "content is B");
			}
		}
	}
}

class Customer3 implements Runnable {
	private Info3 info;
	public Customer3(Info3 info) {
		this.info = info;
	}
	@Override
	public void run() {
		for (int x = 0; x < 100; x ++) {
			this.info.get();
		}
	}
}

public class ThreadConsumerProducer3 {

	public static void main(String[] args) {
		Info3 info = new Info3();
		new Thread(new Productor3(info)).start();
		new Thread(new Customer3(info)).start();
	}

}
