package cn.mldn.advanced;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Description: List, ArratLisy, Vector
 * @ProjectName: DemoProject
 * @Package: cn.mldn.advanced
 * @Author: Yihang Ding
 * @CreateDate: 2018/6/25 00:08
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/6/25 00:08
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/

class TextBook {
    private String title;
    private double price;

    public TextBook(String title, double price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof TextBook)) return false;
        TextBook book = (TextBook) obj;
        return this.title.equals(book.title) && this.price == book.price;
    }

    @Override
    public String toString() {
        return "TextBook{" +
                "title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}


public class ListDemo {
    public static void main(String[] args) {
        List<String> all_1 = new ArrayList<>();
        System.out.println("length: " + all_1.size() + ", empty? " + all_1.isEmpty());
        all_1.add("Hello");
        all_1.add("Hello");
        all_1.add("world");
        System.out.println("length: " + all_1.size() + ", empty? " + all_1.isEmpty());
        for (int i = 0; i < all_1.size(); i++) {
            String str = all_1.get(i);
            System.out.println(str);
        }

        // 要是使用collection定义的，怎么办
        Collection<String> all_2  = new ArrayList<>();
        all_2.add("hello");
        all_2.add("hfe3f");
        all_2.add("fe3f");
        all_2.add("de3");
        Object[] obj = all_2.toArray();
        for (int i = 0; i < obj.length; i++) {
            System.out.println(obj[i]);

        }

        // 集合里面保存对象
        List<TextBook> all_3 = new ArrayList<>();
        all_3.add(new TextBook("java", 42.3));
        all_3.add(new TextBook("OS", 32.0));
        all_3.add(new TextBook("python", 32.1));
        all_3.remove(new TextBook("python", 32.1));
        System.out.println(all_3);

        // 旧的vector类几乎没区别



    }
}
