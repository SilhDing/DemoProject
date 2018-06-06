package cn.mldn.advanced;

class Phone implements Cloneable {
	private String title;
	private double price;
	
	public Phone(String title, double price) {
		super();
		this.title = title;
		this.price = price;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return this.title + ", " + this.price;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}

public class CloneObjectDemo {

	public static void main(String[] args) throws CloneNotSupportedException {
		
		Phone A = new Phone("Apple", 6000);
		Phone B = (Phone) A.clone();
		A.setTitle("Samsung");
		System.out.println(A);
		System.out.println(B);
		
		
	}

}
