package cn.mldn.advanced;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @Description: 国际化的另一个方法
 * @ProjectName: DemoProject
 * @Package: cn.mldn.advanced
 * @Author: Yihang Ding
 * @CreateDate: 2018/6/26 21:25
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/6/26 21:25
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/
public class PropertiesDemo {
    public static void main(String[] args) throws IOException {
        Properties pro = new Properties();
        pro.setProperty("BJ", "北京");
        pro.setProperty("TJ", "天津");
        System.out.println(pro.getProperty("BJ"));
        System.out.println(pro.getProperty("AN"));
        System.out.println(pro.getProperty("AN", "dede"));

        pro.store(new FileOutputStream(new File("/Users/MacbookofSilhouette/Coding/java/eclipse/DemoProject/src"
                + File.separator + "area.properties")), "Area info");

        Properties mes = new Properties();
        mes.load(new FileInputStream(new File("/Users/MacbookofSilhouette/Coding/java/eclipse/DemoProject/src"
                + File.separator + "Messages.properties")));
        System.out.println(mes.getProperty("info"));
    }
}
