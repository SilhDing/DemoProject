package cn.mldn.advanced;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class OutputStreamDemo {

	public static void main(String[] args) throws Exception {
		
		String base_path = "/Users/MacbookofSilhouette/Coding/java/eclipse/DemoProject";
		
		File file = new File(base_path + File.separator + "test_file" + File.separator + "my.txt");
		
		// 要是不想被覆盖原内容，构造方法用下面这个，没有true的话，会覆盖原来的文件内容
		OutputStream output = new FileOutputStream(file,true);
		
		String str = "这是一个将要写进文件的内容\r\n";
		
		byte[] data = str.getBytes();
		
		output.write(data);
		// 另外两种方法
		//output.write(data[2]);
		//output.write(data, 4, 3);
		output.close();
	}

}
