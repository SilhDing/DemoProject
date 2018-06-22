package cn.mldn.advanced;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Description: 经典程序ECHO的服务器部分，和EchoClientDemo配合食用最佳；支持多线程的访问
 * @ProjectName: DemoProject
 * @Package: cn.mldn.advanced
 * @Author: Yihang Ding
 * @CreateDate: 2018/6/23 00:17
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/6/23 00:17
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/

class EchoThread implements Runnable {
    private Socket client;

    public EchoThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            Scanner scan = new Scanner(client.getInputStream());
            PrintStream out = new PrintStream(client.getOutputStream());
            boolean flag = true;
            while (flag) {
                if (scan.hasNext()) {
                    String str  = scan.next().trim();
                    if (str.equalsIgnoreCase("byebye")) {
                        out.println("See you next time!");
                        flag = false;
                    } else {
                        out.println("ECHO : " + str);
                    }
                }
            }
            scan.close();
            out.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class EchoServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);
        boolean flag = true;
        while (flag) {
            Socket client = server.accept();
            new Thread(new EchoThread(client)).start();
        }
        server.close();
    }
}
