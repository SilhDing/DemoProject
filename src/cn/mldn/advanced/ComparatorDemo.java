package cn.mldn.advanced;

import java.util.Arrays;
import java.util.Comparator;

class Ticket {
	private String title;
	private double price;
	public Ticket() {}
	public Ticket(String title, double price) {
		this.title = title;
		this.price = price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public String getTitle() {
		return title;
	}
	
	@Override
	public String toString() {
		return this.title + ", " + this.price + "\n";
	}
}

class TicketComparator implements Comparator<Ticket> {
	
	@Override
	public int compare(Ticket o1, Ticket o2) {
		if (o1.getPrice() > o2.getPrice()) return 1;
		else if (o1.getPrice() <  o2.getPrice()) return -1;
		else return 0;
	}
}

public class ComparatorDemo  {
	public static void main(String[] args) {
		Ticket[] tickets = new Ticket[] {
				new Ticket("movie A", 43.4),
				new Ticket("movie B", 32.9),
				new Ticket("movie C", 32.1),
				new Ticket("movie D", 56.0)
		};
		Arrays.sort(tickets, new TicketComparator());
		System.out.println(Arrays.toString(tickets));
	}

}
