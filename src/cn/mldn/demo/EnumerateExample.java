package cn.mldn.demo;

enum Sex {
	MALE("男"), FEMALE("女");
	private String content;
	private Sex(String content) {this.content = content;} 
	public String toString() {return this.content;}
}

class Person{
	private String name;
	private int age;
	private Sex sex;
	public Person(String name, int age, Sex sex) {
		this.age = age;
		this.name = name ;
		this.sex = sex;
	}
	public String toString() {
		return this.name + ", " + this.age + ", " + this.sex;
	}
}

public class EnumerateExample {

	public static void main(String[] args) {
		System.out.println(new Person("yihang", 23, Sex.MALE));
	}

}
