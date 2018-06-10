package cn.mldn.advanced;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateAndCalendarDemo {

	public static void main(String[] args) {
		
		// 世界实例化可以获取当前的时间
		Date date = new Date();
		System.out.println(date);
		System.out.println("----------------------------");  
		
		// 也可以使用long作为输入实例化
		long cur = System.currentTimeMillis();
		System.out.println(cur);
		Date date2 = new Date(cur);
		System.out.println(date2);
		System.out.println(date2.getTime());
		System.out.println("----------------------------");  
		
		// SimpleDateFormat
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");  // 
		String str = sdf.format(date); // 用来定制日期的格式
		System.out.println(str); 
		System.out.println("----------------------------");  
		
		// calendar类
		Calendar cal = Calendar.getInstance(); // 是抽象类，是不能直接实例化的。
		StringBuffer buf = new StringBuffer();
		buf.append(cal.get(Calendar.YEAR)).append('-');
		buf.append(cal.get(Calendar.MONTH) + 1).append('-');  // 这里的月份从0开始
		buf.append(cal.get(Calendar.DAY_OF_MONTH)).append(' ');
		buf.append(cal.get(Calendar.HOUR_OF_DAY)).append(':');
		buf.append(cal.get(Calendar.MINUTE)).append(':');
		buf.append(cal.get(Calendar.SECOND));
		System.out.println(buf);
	}

}
