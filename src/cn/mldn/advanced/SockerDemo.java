package cn.mldn.advanced;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.advanced
 * @Author: Yihang Ding
 * @CreateDate: 2018/6/22 22:58
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/6/22 22:58
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/
public class SockerDemo {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost",9999);
        Scanner scan = new Scanner(client.getInputStream());

        scan.useDelimiter("\n");
        if (scan.hasNext()) {
            System.out.println("Response data: " + scan.next());
        }

        scan.close();
        client.close();

    }
}
