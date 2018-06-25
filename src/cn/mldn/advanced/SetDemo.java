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

/**
 * @Description:    Description
 * @Package:        cn.mldn.advanced
 * @ClassName:      Magazine
 * @Author:         Yihang Ding
 * @Date:           2018/6/25 21:20
 **/
class Magazine implements Comparable<Magazine> {
    private String title;
    private double price;

    public Magazine(String title, double price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "title='" + title + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Magazine o) {
        if (this.price > o.price) return 1;
        else if (this.price < o.price) return -1;
        else return this.title.compareTo(o.title);
    }

    // equals和hashCode才是用来判断是不是同一个对此
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Magazine magazine = (Magazine) o;

        if (Double.compare(magazine.price, price) != 0) return false;
        return title != null ? title.equals(magazine.title) : magazine.title == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = title != null ? title.hashCode() : 0;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

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

        //对类操作

        Set<Magazine> set_3 = new TreeSet<>();  // TreeSet中的排序是通过compareTo实现的
        set_3.add(new Magazine("zhiyin", 32.0));
        set_3.add(new Magazine("eee", 21.0));
        set_3.add(new Magazine("gushihui",22));
        set_3.add(new Magazine("eee",21.0));
        System.out.println(set_3);
    }
}
