package cn.mldn.advanced;

import java.util.*;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.advanced
 * @Author: Yihang Ding
 * @CreateDate: 2018/6/25 21:46
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/6/25 21:46
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/
public class IterationCollectionDemo {
    public static void main(String[] args) {

        // Iterator
        Set<String> set_1 = new HashSet<>();
        set_1.add("ewew");
        set_1.add("yihang");
        set_1.add("yihang");
        Iterator<String>  iter = set_1.iterator();
        while (iter.hasNext()) {
            String str = iter.next();
            System.out.println(str);
        }

        // ListIterator
        List<String> list_1 = new ArrayList<>();
        list_1.add("yihang");
        list_1.add("hwo are");
        list_1.add("you");
        ListIterator<String> liter = list_1.listIterator();
        System.out.println("from start to the end");
        while (liter.hasNext()) {
            String str = liter.next();
            System.out.print(str + ", ");
        }

        System.out.println("from end to the start");
        while (liter.hasPrevious()) {
            System.out.print(liter.previous() + ", ");
        }

        // for each
        for (String str: list_1) {
            System.out.println(str);
        }

        // Enumeration
        Vector<String> vec = new Vector<>();
        vec.add("yihang");
        vec.add("loves");
        vec.add("WSF");
        Enumeration<String> enu = vec.elements();
        while (enu.hasMoreElements()) {
            String str = enu.nextElement();
            System.out.print(str + " ");
        }
    }
}
