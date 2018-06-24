package cn.mldn.advanced;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.advanced
 * @Author: Yihang Ding
 * @CreateDate: 2018/6/25 00:37
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/6/25 00:37
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/
public class SetDemo {
    public static void main(String[] args) {
        Set<String> set_1 = new HashSet<>();
        set_1.add("nihao");
        set_1.add("def");
        set_1.add("hello");
        set_1.add("hello");
        System.out.println(set_1);

        Set<String> set_2 = new TreeSet<>();
        set_2.add("nihao");
        set_2.add("def");
        set_2.add("hello");
        set_2.add("hello");
        System.out.println(set_2);

        //
    }
}
