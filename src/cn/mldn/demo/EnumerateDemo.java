package cn.mldn.demo;

enum Color {
	RED("红色"), GREEN("绿色"), BLUE("蓝色");
	
	private String title;

	private Color(String title) {
		this.title = title;
	}
	public String toString() {
		return this.title;
	}
}

enum Color2 {
	RED2, GREEN2, BLUE2;
}

// 枚举还可以继承一个借口，并且可以在每一个对象后面使用匿名内部类实现抽象方法，相当于定制化
interface IMessage2 {
	public String getTitle();
}

enum Color3 implements IMessage2 {
	
	RED3("红色") {
		public String getTitle() {
			return "嘻嘻红色" + this;
		}
	}, GREEN3("绿色") {
		public String getTitle() {
			return "沉静绿色" + this;
		}
	}, BLUE3("蓝色") {
		public String getTitle() {
			return "忧郁蓝色" + this;
		}
	};
	
	private String title;
	private Color3(String title) {
		this.title = title;
	}
	public String toString() {
		return this.title;
	}	
}

// 枚举里面可以直接定义一个抽象方法，然后每一个枚举的对象都要覆写这个方法
enum Color4 {
	
	RED4("红色") {
		public String getTitle() {
			return "嘻嘻红色" + this;
		}
	}, GREEN4("绿色") {
		public String getTitle() {
			return "沉静绿色" + this;
		}
	}, BLUE4("蓝色") {
		public String getTitle() {
			return "忧郁蓝色" + this;
		}
	};
	
	private String title;
	private Color4(String title) {
		this.title = title;
	}
	public String toString() {
		return this.title;
	}	
	public abstract String getTitle();
}

public class EnumerateDemo {

	public static void main(String[] args) {
		Color2 red = Color2.RED2;
		System.out.println(red);
		
		for(Color2 c: Color2.values()) {
			System.out.println(c.ordinal() + " - " + c.name());
		}
		for(Color c: Color.values()) {
			System.out.println(c);
		}

	}

}
