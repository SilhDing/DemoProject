package cn.mldn.advanced;

import java.io.*;

/**
 * @Description: 缓冲输入流，解决System.in的中文乱码
 * @ProjectName: DemoProject
 * @Package: cn.mldn.advanced
 * @Author: Yihang Ding
 * @CreateDate: 2018/6/20 00:34
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/6/20 00:34
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/
public class BufferedStreamDemo {
    public static void main(String[] args) throws IOException {

        // BufferReader的构造方法可以接受Reader对象
        // InputStreamReader接受一个InputStream对象，转换成Reader对象
        // System.in本身是一个InputStream对象
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("pleas input data: ");
        String str = buf.readLine();
        System.out.println("you just input: " + str);


        // 判断输入内容
        BufferedReader buf2 = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = true;
        while (flag) {
            System.out.println("Please input your age: ");
            String str2 = buf2.readLine();
            if (str2.matches("\\d{1,3}")) {
                System.out.println("your age is: " + Integer.parseInt(str2));
                flag = false;
            } else {
                System.out.println("Please iput again!");
            }
        }

        // read a file!

        File file = new File("/Users/MacbookofSilhouette/Coding/java/eclipse/DemoProject/test_file/my.txt");
        BufferedReader buf3 = new BufferedReader(new FileReader(file));
        String str3;
        while((str3 = buf3.readLine()) != null) {
            System.out.println(str3 + "???");
        }
        buf.close();
        buf2.close();
        buf3.close();

    }
}
