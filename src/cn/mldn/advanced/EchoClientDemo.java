package cn.mldn.advanced;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Description: 经典程序ECHO的客户端部分，和EchoServerDemo配合食用最佳
 * @ProjectName: DemoProject
 * @Package: cn.mldn.advanced
 * @Author: Yihang Ding
 * @CreateDate: 2018/6/23 01:45
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/6/23 01:45
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/
public class EchoClientDemo {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 9999);
        Scanner input = new Scanner(System.in);
        Scanner scan = new Scanner(client.getInputStream());
        PrintStream out = new PrintStream(client.getOutputStream());

        input.useDelimiter("\n");
        scan.useDelimiter("\n");
        boolean flag = true;

        while (flag) {
            System.out.println("Please type the data to be delivered: ");
            if (input.hasNext()) {
                String str = input.next().trim();
                out.println(str);
                if (str.equalsIgnoreCase("byebye")) {
                    flag = false;
                }
                if (scan.hasNext()) {
                    System.out.println(scan.next());
                }
            }
        }

        input.close();
        scan.close();
        out.close();
        client.close();

    }
}
