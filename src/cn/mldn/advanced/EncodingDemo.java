package cn.mldn.advanced;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class EncodingDemo {

	public static void main(String[] args) throws Exception {
		System.getProperties().list(System.out);
		
		File file = new File("/Users/MacbookofSilhouette/Coding/java/eclipse/DemoProject/test_file/my.txt");
		OutputStream output = new FileOutputStream(file, true);
		output.write("得的很多和我的和电荷".getBytes("UTF-8")); //不适用utf-8的话，可能就有乱码！！
		output.close();
	}

}
