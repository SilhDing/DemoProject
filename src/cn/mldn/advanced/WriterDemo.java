package cn.mldn.advanced;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

public class WriterDemo {

	public static void main(String[] args) throws Exception {
		
		String base_path = "/Users/MacbookofSilhouette/Coding/java/eclipse/DemoProject";
		
		File file = new File(base_path + File.separator + "test_file" + File.separator + "my.txt");
		
		Writer out = new FileWriter(file, true);
		
		String str = "这是通过write写进来的内容\n";
		out.write(str);
		out.close();
	}

}
