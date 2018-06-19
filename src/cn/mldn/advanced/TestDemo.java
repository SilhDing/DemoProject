package cn.mldn.advanced;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @Description: A test file for some extra coding
 * @ProjectName: DemoProject
 * @Package: cn.mldn.advanced
 * @Author: Yihang Ding
 * @CreateDate: 2018/6/19 16:43
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/6/19 16:43
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/

class Test {
    private String name ;
    private int id;

    public Test(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}

public class TestDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("test file!!!That ");
        Test test = new Test("yihang", 33);
        System.out.println(test.toString());

        File file = new File("/Users/MacbookofSilhouette/Coding/java/eclipse/DemoProject/test_file/my.txt");
        OutputStream output = new FileOutputStream(file, true);
        output.write("得的很多和我的和电荷".getBytes("UTF-8")); //不适用utf-8的话，可能就有乱码！！
        output.close();

    }
}
