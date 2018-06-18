package cn.mldn.advanced;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.Consumer;

import javax.swing.plaf.InputMapUIResource;

import sun.awt.image.BufferedImageGraphicsConfig;

public class SystemIODemo {

	public static void main(String[] args) throws Exception {
		try{
			Integer.parseInt("abc");
		} catch (Exception e) {
			System.out.println(e);
			System.err.println(e);
		}
		
		OutputStream out = System.out;
		out.write("Hello World".getBytes());
		
		Consumer<String>  con = System.out::println;
		con.accept("Hello Yihang");
		
		// input something from the screen
		InputStream input = System.in;
		System.out.print("Please type something here:");
		int temp = 0;
		StringBuffer buf = new StringBuffer();
		while ((temp = input.read()) != -1) {
			if (temp == '\n') break;
			buf.append((char)temp);
		}
		System.out.println("You just typed: " + buf);
		
	}

}
