package cn.mldn.advanced;

import java.util.*;

/**
 * @Description: map  只看关键的几个知识点
 * @ProjectName: DemoProject
 * @Package: cn.mldn.advanced
 * @Author: Yihang Ding
 * @CreateDate: 2018/6/26 00:16
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/6/26 00:16
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/

class Key {
    private String title;

    public Key(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Key{" +
                "title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Key key = (Key) o;

        return title != null ? title.equals(key.title) : key.title == null;
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }
}

public class MapDemo {
    public static void main(String[] args) {
        // 取得key
        Map<String, Integer> map = new HashMap<>();
        map.put("yihang", 23);
        map.put("tim", 32);
        map.put("shengfeng", 26);
        map.put(null, 0);
        Set<String> set = map.keySet();
        Iterator<String> iter = set.iterator();
        while(iter.hasNext()) System.out.println(iter.next());

        // entrySet
        Set<Map.Entry<String, Integer>> entry = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterEn = entry.iterator();
        while (iterEn.hasNext()) {
            Map.Entry<String, Integer> me = iterEn.next();
            System.out.println(me.getKey() + " = " + me.getValue());
        }

        // 对象作为key

        Map<Key, String> map_2 = new HashMap<>();
        map_2.put(new Key("yihang"), "3223");
        System.out.println(map_2.get(new Key("yihang")));

        Set<Key> keys = new HashSet<>();
        keys.add(new Key("yihang"));
        keys.add(new Key("loveyu"));
        keys.add(new Key("yihang"));
        System.out.println(keys);

    }
}
