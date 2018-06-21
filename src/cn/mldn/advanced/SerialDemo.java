package cn.mldn.advanced;

/**
 * @Description: 序列化操作
 * @ProjectName: DemoProject
 * @Package: cn.mldn.advanced
 * @Author: Yihang Ding
 * @CreateDate: 2018/6/22 00:52
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/6/22 00:52
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/


import java.io.*;

/**
 * @Description:    Description
 * @Package:        cn.mldn.advanced
 * @ClassName:      A
 * @Author:         Yihang Ding
 * @Date:           2018/6/22 00:56
 **/
class A implements Serializable{
    private String title;
    private double price;
    private transient String content;

    public A(String title, double price, String content) {
        this.title = title;
        this.price = price;
        this.content = content;
    }

    @Override
    public String toString() {
        return "A{" +
                "title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}


public class SerialDemo {
    public static void main(String[] args) throws Exception {
        ser();
        dser();
    }

    public static void ser() throws Exception {
        /**
         * @descroption     Description
         * @method          ser
         * @author          Yihang Ding
         *
         * @parameters      []
         * @return          void
         * @date            2018/6/22 00:59
         **/
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("/Users/MacbookofSilhouette/Coding/java/eclipse/DemoProject/test_file/A.ser")));
        oos.writeObject(new A("java dev",32, "?????"));
        //oos.writeObject("yihang ding");
        oos.close();
    }

    public static void dser() throws Exception {
        /**
         * @descroption     Description
         * @method          dser
         * @author          Yihang Ding
         *
         * @parameters      []
         * @return          void
         * @date            2018/6/22 01:02
         **/
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("/Users/MacbookofSilhouette/Coding/java/eclipse/DemoProject/test_file/A.ser")));
        Object obj = ois.readObject();
        A a = (A) obj;
        System.out.println(a);
        ois.close();
    }
}
