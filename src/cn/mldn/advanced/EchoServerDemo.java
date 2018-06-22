package cn.mldn.advanced;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Description: 经典程序ECHO的服务器部分，和EchoClientDemo配合食用最佳
 * @ProjectName: DemoProject
 * @Package: cn.mldn.advanced
 * @Author: Yihang Ding
 * @CreateDate: 2018/6/23 00:17
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/6/23 00:17
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/
public class EchoServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);
        Socket client = server.accept();

        Scanner scan = new Scanner(client.getInputStream());
    }
}
