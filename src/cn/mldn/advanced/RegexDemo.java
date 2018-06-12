package cn.mldn.advanced;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexDemo {

	public static void main(String[] args) {
		String str = "123";
		System.out.println(str.matches("\\d+"));
		
		// 使用正则替换
		String str2 = "heuf 79t4 rdeYUG y8yfe3D@Uy8000;'de";
		String regex = "[^a-z]";
		System.out.println(str2.replaceAll(regex, ""));
		
		// 使用正则分隔
		String[] res = str2.split("\\d+");
		System.out.println(Arrays.toString(res));
		
		// 判断ip地址
		String ip = "192.180.1.1";
		regex = "(\\d{1,3}\\.){3}\\d{1,3}";
		System.out.println(ip.matches(regex));
		
		// 电子邮件，最基本的：由字母数字和_组成
		str = "hello_w2@qq.com";
		regex = "\\w+@\\w+\\.\\w+";
		System.out.println(str.matches(regex));
		
		// java.util.regex
		str = "ab122cfefuenufeunu9u9r375878585b";
		regex =" \\d+";
		Pattern pattern = Pattern.compile(regex);
		Matcher mat = pattern.matcher(str);
		System.out.println(mat.matches());
		
	}

}
