package cn.mldn.advanced;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleDemo {

	public static void main(String[] args) {
		// 直接读取公共资源文件
		ResourceBundle rb = ResourceBundle.getBundle("Messages");
		System.out.println(rb.getString("info"));
		
		// 直接读取公共资源文件，内容有占位符
		ResourceBundle rb2 = ResourceBundle.getBundle("Messages");
		String str = rb2.getString("wel.msg");
		System.out.println(MessageFormat.format(str, "yihang", new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
		
		// 读取特定语言的资源文件
		Locale loc = new Locale("zh", "CN");
		ResourceBundle rb3 = ResourceBundle.getBundle("Messages",loc);
		String str3 = rb3.getString("wel.msg");
		System.out.println(MessageFormat.format(str3, "yihang"));
		
		Locale loc2 = new Locale("en", "US");
		ResourceBundle rb4 = ResourceBundle.getBundle("Messages", loc2);
		String str4 = rb4.getString("wel.msg");
		System.out.println(MessageFormat.format(str4, "Yihang"));
		
	}

}
