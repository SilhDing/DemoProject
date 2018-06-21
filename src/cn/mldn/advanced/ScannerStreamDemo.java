package cn.mldn.advanced;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @Description: Scanner 扫描流
 * @ProjectName: DemoProject
 * @Package: cn.mldn.advanced
 * @Author: Yihang Ding
 * @CreateDate: 2018/6/22 00:38
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/6/22 00:38
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/
public class ScannerStreamDemo {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please type something here: ");
        if (scan.hasNext()) {
            System.out.println("you just typed: " + scan.next());
        }

        // 输入指定类型，并获取
        System.out.println("Please type a double number:");
        if (scan.hasNextDouble()) {
            double score = scan.nextDouble();
            System.out.println("Double you just typed: " + score);
        } else {
            System.out.println(" It is not a double number!!!");
        }

        // regex
        System.out.println("pleasr input date of birth: ");
        if (scan.hasNext("\\d{4}-\\d{2}-\\d{2}")) {
            String bir = scan.next();
            System.out.println(bir);
        } else {
            System.out.println("the format is not right!");
        }

        scan.close();

        // read a file
        Scanner scanf = new Scanner(new FileInputStream(new File("/Users/MacbookofSilhouette/Coding/java/eclipse/DemoProject/test_file/my.txt")));
        scanf.useDelimiter("\n");
        while (scanf.hasNext()) {
            System.out.println(scanf.next());
        }
        scanf.close();

    }
}
