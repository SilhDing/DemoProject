package cn.mldn.advanced;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;


public class MemoryStreamDemo {

	public static void main(String[] args) throws Exception {
		String str = "hello * world!!!";
		
		InputStream input = new ByteArrayInputStream(str.getBytes());
		OutputStream output = new ByteArrayOutputStream();
		
		int temp = 0;
		while((temp = input.read())!=-1) {
			output.write(Character.toUpperCase(temp));
		}
		
		System.out.println(output);
		input.close();
		output.close();
		
		
		// 实现文件的合并读取
		File fileA = new File("/Users/MacbookofSilhouette/Coding/java/eclipse/DemoProject/test_file/my.txt");
		File fileB = new File("/Users/MacbookofSilhouette/Coding/java/eclipse/DemoProject/my.txt");
		
		InputStream inputA = new FileInputStream(fileA);
		InputStream inputB = new FileInputStream(fileB);
		
		ByteArrayOutputStream output2 = new ByteArrayOutputStream();
		while((temp = inputA.read()) != -1) {
			output2.write(temp);
		}
		while((temp = inputB.read()) != -1) {
			output2.write(temp);
		}
		
		byte[] data = output2.toByteArray();
		output2.close();
		inputA.close();
		inputB.close();
		System.out.println(new String(data));
		
		
	}

}
