package cn.mldn.advanced;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


public class FileIODemo {

	public static void main(String[] args) throws Exception {
		
		String base_path = "/Users/MacbookofSilhouette/Coding/java/eclipse/DemoProject";
		
		// 目录不存在怎么办？？
		File file2 = new File(base_path + File.separator + 
								"test_file" + File.separator + 
								"my.txt");
		if (!file2.getParentFile().exists()) {
			file2.mkdirs();  // 请不要用mkdir 没啥意义
		}
		System.out.println(file2.exists());
		if (!file2.exists()) {
			file2.createNewFile();
		}
		
		File file3 = new File(base_path + File.separator + "test_file" + File.separator + "005.jpg");
		if (file3.exists()) {
			System.out.println("is a file? " + file3.isFile());
			System.out.println("is a directory? " + file3.isDirectory());
			System.out.println("size of file: " + new BigDecimal((double)file3.length()/1024).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP) + " KB");
			System.out.println("last modified: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(file3.lastModified())));
		}
		
		File file4 = new File(base_path);
		if (file4.isDirectory()) {
			System.out.println(Arrays.toString(file4.list()));
			System.out.println(Arrays.toString(file4.listFiles()));  // 这个会包含路径
		}
		print(file4);
	}
	
	public static void print(File file) {
		if (file.isDirectory()) {
			File[] result = file.listFiles();
			if (result != null) {
				for (int i = 0; i < result.length; i++) {
					print(result[i]);
				}
			}
		}
		System.out.println(file);
	}

}
