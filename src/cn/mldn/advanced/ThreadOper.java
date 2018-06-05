package cn.mldn.advanced;

class MyThread3 implements Runnable{
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}
	
}

class MyThread4 implements Runnable {
	@Override
	public void run() {
		for (int x = 0; x < 20; x ++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + ", x = " + x);
		}
	}
	
}

public class ThreadOper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyThread3 mt = new MyThread3();
		new Thread(mt).start();
		new Thread(mt, "My thread 1").start();
		new Thread(mt).start();
		new Thread(mt, "My thread 2").start();
		new Thread(mt).start();
		mt.run();
		//  会自动命名
		//  主方法main就是一个线程，主方法上创建的线程实际上就是其子线程
		
		// 休眠
		MyThread4 mt4 = new MyThread4();
		//new Thread(mt4, "Private Thread A").start();
		
		// 优先级
		Thread t1 = new Thread(mt4, "Private Thread A");
		Thread t2 = new Thread(mt4, "Private Thread B");
		Thread t3 = new Thread(mt4, "Private Thread C");
		t1.setPriority(Thread.MAX_PRIORITY);
		t1.start();
		t2.start();
		t3.start();
		
	}
}
