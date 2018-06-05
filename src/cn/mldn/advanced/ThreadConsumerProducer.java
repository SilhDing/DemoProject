// 这是一个没有同步的模型，会有错误

package cn.mldn.advanced;

class Info {
	private String title;
	private String content;
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
}

class Productor implements Runnable {
	private Info info;
	public Productor(Info info) {
		this.info = info;
	}
	@Override
	public void run() {
		for (int x = 0; x < 100; x++) {
			if (x % 2 == 0) {
				this.info.setTitle("Title is A");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.info.setContent("content is A");
			} else {
				this.info.setTitle("Title if B");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.info.setContent("content is B");
			}
		}
	}
}

class Customer implements Runnable {
	private Info info;
	public Customer(Info info) {
		this.info = info;
	}
	@Override
	public void run() {
		for (int x = 0; x < 100; x++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(this.info.getTitle() + " - " + this.info.getContent());
		}
	}
}

public class ThreadConsumerProducer {

	public static void main(String[] args) {
		Info info = new Info();
		new Thread(new Productor(info)).start();
		new Thread(new Customer(info)).start();
	}

}
