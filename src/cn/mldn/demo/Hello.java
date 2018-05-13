package cn.mldn.demo;

class book {
	
	private double price = 100.9;
	private String author = "yihang";
	
	public book() {
		super();
	}
	public book(double price, String author) {
		super();
		this.price = price;
		this.author = author;
	}

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public String toString() {
		return "book [price=" + price + ", author=" + author + "]";
	}
	
}

public class Hello {

	public static void main(String[] args) {
		System.out.println("Hello World");
		int a = 10;
		int b = 100;
		System.out.println(a + "  " + b);
	}
	
	

}
