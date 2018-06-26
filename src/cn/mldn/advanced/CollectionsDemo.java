package cn.mldn.advanced;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description: Collections是一个工具类
 * @ProjectName: DemoProject
 * @Package: cn.mldn.advanced
 * @Author: Yihang Ding
 * @CreateDate: 2018/6/26 21:35
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/6/26 21:35
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/
public class CollectionsDemo {
    public static void main(String[] args) {
        List<Integer> all = new ArrayList<>();
        Collections.addAll(all,1,4,2,5,7);
        System.out.println(all);
        Collections.reverse(all);
        System.out.println(all);
        Collections.sort(all, (a, b) -> b - a);
        System.out.println(all);
    }
}
