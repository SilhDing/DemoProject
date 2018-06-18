package cn.mldn.advanced;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class InputStreamDemo {

	public static void main(String[] args) throws Exception {
		
		String base_path = "/Users/MacbookofSilhouette/Coding/java/eclipse/DemoProject";

		// 向数组读取数据
		File file = new File(base_path +File.separator +  "test_file" + File.separator + "my.txt");
		if (file.exists()) {
			System.out.println("File exists!");
			InputStream input = new FileInputStream(file);
			byte [] data = new byte[1024];
			int len = input.read(data);
			System.out.println("[" + new String(data, 0 ,len) + "]");
			
			// 单字节读取
			int foot = 0;
			int temp = 0;
 			while ((temp = input.read()) != -1) data[foot++] = (byte) temp;
 			input.close();
 			System.out.println("[" + new String(data, 0 ,len) + "]");
		}
	}
}
