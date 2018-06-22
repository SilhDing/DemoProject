package cn.mldn.advanced;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

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
public class ServerSocketDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);
        System.out.println("Wait for client");
        Socket client = server.accept();

        PrintStream out = new PrintStream(client.getOutputStream());
        out.println("Hello!");
        out.close();
        client.close();
        server.close();
    }
}
