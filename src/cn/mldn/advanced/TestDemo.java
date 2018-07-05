package cn.mldn.advanced;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;






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
