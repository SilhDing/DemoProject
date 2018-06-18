package cn.mldn.advanced;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFileDemo {

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		
		if (args.length != 2) {
			System.out.println("there should be 2 arguements!");
			System.exit(1);
		}
		
		File inFile = new File(args[0]); //source file
		if (!inFile.exists()) {
			System.out.println("The source file does not exist!");
			System.exit(1);
		}
		
		File outFile = new File(args[1]);
		if (!outFile.getParentFile().exists()) {
			outFile.getParentFile().mkdirs();
		} 
		
		InputStream input = new FileInputStream(inFile);
		OutputStream output = new FileOutputStream(outFile);
		
		int temp = 0;
		int count = 0;
		
		byte[] data = new byte[2048];
		while((temp = input.read(data)) != -1) {
			output.write(data, 0, temp);
			count ++;
		}
		
		input.close();
		output.close();
		
		long end = System.currentTimeMillis();
		System.out.println("Total time: " + (end - start));
		System.out.println(count);
		
	}

}
