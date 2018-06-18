package cn.mldn.advanced;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

class PrintUtil {
	private OutputStream output;
	
	public PrintUtil(OutputStream output) {
		this.output = output;
	}
	
	public void print(int x) {
		this.print(String.valueOf(x));
	}
	public void print(String x) {
		try {
			this.output.write(x.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void print(double x) {
		this.print(String.valueOf(x));
	}
	
	public void println(int x) {
		this.println(String.valueOf(x));
	}
	public void println(String x) {
		this.print(x.concat("\n"));
	}
	public void println(double x) {
		this.println(String.valueOf(x));
	}
	public void close() {
		try {
			this.output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

public class PrintStreamDemo {

	public static void main(String[] args) throws Exception {
		PrintUtil pu = new PrintUtil(new FileOutputStream(new File("/Users/MacbookofSilhouette/Coding/java/eclipse/DemoProject/test_File/my.txt"), true));
		pu.print("Hello ");
		pu.print("World ");
		pu.println(1+1);
		pu.println(34.9+2);
		pu.close();
		
		// the code above is a DIY class that supports printStream
		// Now we use the built-in class
		PrintStream ps = new PrintStream(new FileOutputStream(new File("/Users/MacbookofSilhouette/Coding/java/eclipse/DemoProject/test_File/my.txt"), true));
		ps.print("yihang");
		ps.print(" Ding ");
		ps.println(1+4);
		ps.println(34.2+10.3);
		
		
		// since JDK 1.5
		ps.printf("Name: %s, Age: %d, Grade: %5.2f", "Yihang", 23, 94.3);
		ps.close();
		
	}

}
