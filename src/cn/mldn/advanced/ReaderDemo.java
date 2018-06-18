package cn.mldn.advanced;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.Arrays;

public class ReaderDemo {

	public static void main(String[] args) throws Exception {
		
		String base_path = "/Users/MacbookofSilhouette/Coding/java/eclipse/DemoProject";
		File file = new File(base_path + File.separator + "test_file" + File.separator + "my.txt");
		
		Reader in = new FileReader(file);
		
		char[] data = new char[1024];
		int len = in.read(data);
		in.close();
		System.out.println(Arrays.toString(data));
	}



}
