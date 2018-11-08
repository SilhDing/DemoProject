package cn.mldn.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.test
 * @Author: Yihang Ding
 * @CreateDate: 2018/10/27 00:01
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/10/27 00:01
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Practice {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String filename = scan.nextLine();
        readAndCount(filename);
    }

    private static void readAndCount(String filename) {
        HashMap<String, Integer> map = new HashMap<>();
        try {
            // scanner for reading and writer for writing
            Scanner reader = new Scanner(new File(filename));
            Writer writer = new FileWriter(new File("records_" + filename), true);

            while (reader.hasNextLine()) {
                String record = reader.nextLine();
                String[] infos = record.split(" - - ");
                map.put(infos[0], map.getOrDefault(infos[0], 0) + 1);
            }

            for (String str: map.keySet()) {
                writer.write(str + " " + map.get(str) + "\n");
            }

            reader.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String[] findMiss(String s, String t) {
        String[] sa = s.trim().split("\\s+");
        String[] ta = t.trim().split("\\s+");
        String[] res = new String[sa.length - ta.length];

        int pa = 0, pt = 0, resp = 0;
        while (pa < sa.length) {
            if (pt == ta.length) {
                while (pa < sa.length) res[resp ++] = sa[pa++];
                break;
            }
            if (!sa[pa].equals(ta[pt])) {
                res[resp++] = sa[pa];
                pa ++;
            } else {
                pa ++;
                pt ++;
            }
        }

        return res;
    }
}
