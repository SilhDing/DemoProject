package cn.mldn.demo;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.Predicate;

class MyDemo {
	public void print(String str) {
		System.out.println(str);
	}
}


public class BuildInFunctionalInterfaceDemo {

	public static void main(String[] args) {
		Function<String, Boolean> fun = "###Hello"::startsWith;
		System.out.println(fun.apply("##"));
		
		Consumer<String> cons = new MyDemo()::print;
		cons.accept("Hello World");
		
		Consumer<String> pri = System.out::println;
		pri.accept("Hello World");
		
		Supplier<String> sup = "hello"::toUpperCase;
		System.out.println(sup.get());
		
		Predicate<String> predicate = "hello"::equalsIgnoreCase;
		System.out.println(predicate.test("heLLO"));
	}

}
