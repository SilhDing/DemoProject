package cn.mldn.advanced;


class MyThread5 implements Runnable{
	private int ticket = 100;
	
	@Override
	public void run() {
		for (int x = 0; x < 20; x++) {
				// 代码块实现同步
				synchronized (this) {
				if (this.ticket > 0) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+ " 卖票，ticket = " + this.ticket--);
				}
			}
		}
	}
}

class MyThread6 implements Runnable{
	private int ticket = 100;
	
	@Override
	public void run() {
		for (int x = 0; x < 20; x++) {
			this.sale();
		}
	}
	// 使用同步方法
	public synchronized void sale(){
		if (this.ticket > 0) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+ " 卖票，ticket = " + this.ticket--);
		}
	}
}

public class ThreadSyncLock {
	public static void main(String[] args) {
		MyThread5 mt = new MyThread5();
		new Thread(mt, "seller A").start();
		new Thread(mt, "seller B").start();
		new Thread(mt, "seller C").start();
		new Thread(mt, "seller D").start();
	}
}