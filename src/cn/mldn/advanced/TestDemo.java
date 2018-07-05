package cn.mldn.advanced;

import java.util.*;

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
    /**
     * @Description:    Description
     * @Package:        cn.mldn.advanced
     * @ClassName:      Test
     * @Author:         Yihang Ding
     * @Date:           2018/7/1 17:11
     */
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

class TestComparator implements Comparator<Test> {


    @Override
    public int compare(Test o1, Test o2) {
        /**
         * @descroption     Description
         * @method          compare
         * @author          Yihang Ding
         * @date            2018/7/1 17:11
         *
         * @param    o1
         * @param    o2
         * @return   int
         */
        return o1.getId() - o2.getId();
    }
}

public class TestDemo {
    public static void main(String[] args) throws Exception {
        /**
         * @descroption     Description
         * @method          main
         * @author          Yihang Ding
         * @date            2018/7/1 17:11
         *
         * @param    args
         * @return   void
         */
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> (b[0] - a[0]));
        pq.add(new int[]{12,0});
        pq.add(new int[]{15,4});
        pq.add(new int[]{10,2});
        int n = pq.size();
        for (int i = 0; i < n; i++) {
            int[] arr = pq.poll();
            System.out.println(Arrays.toString(arr));
        }


    }
}
