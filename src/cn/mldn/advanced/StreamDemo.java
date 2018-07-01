package cn.mldn.advanced;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: stream和mapreduce
 * @ProjectName: DemoProject
 * @Package: cn.mldn.advanced
 * @Author: Yihang Ding
 * @CreateDate: 2018/6/27 00:01
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/6/27 00:01
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */

class ShopCar{
    private String pname;
    private double price;
    private int amount;

    public ShopCar(String pname, double price, int amount) {

        this.pname = pname;
        this.price = price;
        this.amount = amount;
    }

    public String getPname() {
        return pname;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }
}

public class StreamDemo {
    public static void main(String[] args) {
        /**
         * @descroption     Description
         * @method          main
         * @author          Yihang Ding
         *
         * @param args
         * @return          void
         * @date            2018/7/1 16:37
         */
        List<String> all = new ArrayList<>();
        all.add("Hello");
        all.add("yihang");
        all.add("yeah");
        all.add("yihang");
        all.forEach(System.out::println);

        //一个stream只能操作一次

        Stream<String> stream = all.stream();
        // System.out.println(stream.count());
        List<String> newAll = stream.distinct().collect(Collectors.toList());
        System.out.println(newAll);

        Stream<String> stream_2 = all.stream();
        List<String> newAll_2 = stream_2.distinct().filter((x) -> x.contains("a")).collect(Collectors.toList());
        newAll_2.forEach(System.out::println);

        Stream<String> stream_3 = all.stream();
        List<String> newAll_3 = stream_3.map((x) -> x.toLowerCase()).distinct().filter((x) -> x.contains("h")).collect(Collectors.toList());
        newAll_3.forEach(System.out::println);

        Stream<String> stream_4 = all.stream();
        List<String> newAll_4 = stream_4.skip(2).limit(2).collect(Collectors.toList());
        newAll_4.forEach(System.out::println);

        Stream<String> stream_5 = all.stream();
        if (stream_5.anyMatch((x) -> x.contains("hang"))) {
            System.out.println("data exists!");
        }

        Stream<String> stream_6 = all.stream();
        Predicate<String> p1 = (x) -> x.contains("e");
        Predicate<String> p2 = (x) -> x.contains("a");
        if (stream_6.anyMatch(p1.and(p2))) {
            System.out.println("data exists!");
        }

        // mapreduce
        List<ShopCar> car = new ArrayList<>();
        car.add(new ShopCar("book", 12, 8));
        car.add(new ShopCar("paper", 0.4, 332));
        car.add(new ShopCar("tv", 3232.9, 2));
        car.add(new ShopCar("umbrella", 39, 10));
        double s = car.stream().map((x) -> x.getAmount() * x.getPrice()).reduce((sum, m)->sum + m).get();
        System.out.println("total cost: " + s);

        DoubleSummaryStatistics dss = car.stream().
                mapToDouble((sc)->(sc.getAmount()*sc.getPrice())).summaryStatistics();
        System.out.println("all amount: " + dss.getCount());
        System.out.println("all cost: " + dss.getSum());
        System.out.println("average cost: " + dss.getAverage());
        System.out.println("max cost: " + dss.getMax());
        System.out.println("min cost: " + dss.getMin());
    }
}
