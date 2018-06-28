package cn.mldn.advanced;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.advanced
 * @Author: Yihang Ding
 * @CreateDate: 2018/6/27 00:01
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/6/27 00:01
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class StreamDemo {
    public static void main(String[] args) {
        List<String> all = new ArrayList<>();
        all.add("Hello");
        all.add("yihang");
        all.add("yeah");
        all.add("yihang");
        all.forEach(System.out::println);

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
    }
}
