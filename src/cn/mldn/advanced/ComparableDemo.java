package cn.mldn.advanced;

import java.util.Arrays;

class Book implements Comparable<Book>{
	private String title;
	private double price;
	public Book(String title, double price) {
		this.title = title;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return this.title + ", " + this.price + "\n";
	}
	
	@Override
	public int compareTo(Book o) {
		if(this.price > o.price) return 1;
		else if (this.price < o.price) return -1;
		else return 0;
	}
	
}

public class ComparableDemo {
	public static void main(String[] args) {
		Book[] books = new Book[] {
				new Book("Java", 74.9),
				new Book("JS", 43.5),
				new Book("iOS", 89.2),
				new Book("Python", 44.9)
		};
		Arrays.sort(books);
		System.out.println(Arrays.toString(books));
		
	}

}
