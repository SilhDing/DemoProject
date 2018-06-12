package cn.mldn.advanced.reflectHelp;

public class Book {
	private String title;
	private double price;
	
	public Book(String title, double price) {
		this.title = title;
		this.price = price;
	}

	@Override
	public String toString() {
		return this.title + ", " + this.price;
	}
}
