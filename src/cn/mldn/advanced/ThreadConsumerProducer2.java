// 已经解决了数据错误的问题，但是重复写和重复读的问题还是没有解决

package cn.mldn.advanced;

class Info2 {
	private String title;
	private String content;
	public synchronized void set(String title, String content) {
		this.title = title;
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.content = content;
	}
	public synchronized void get() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this.title + " - " + this.content);
	}
}

class Productor2 implements Runnable {
	private Info2 info;
	public Productor2(Info2 info) {
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

class Customer2 implements Runnable {
	private Info2 info;
	public Customer2(Info2 info) {
		this.info = info;
	}
	@Override
	public void run() {
		for (int x = 0; x < 100; x ++) {
			this.info.get();
		}
	}
}

public class ThreadConsumerProducer2 {

	public static void main(String[] args) {
		Info2 info = new Info2();
		new Thread(new Productor2(info)).start();
		new Thread(new Customer2(info)).start();
	}

}
