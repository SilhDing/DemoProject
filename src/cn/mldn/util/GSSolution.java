package cn.mldn.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class GSSolution {

  public static String[] strangeSorting(int[] m, String[] nums){
    int[] map = new int[10];
    for (int i = 0; i < m.length; i++) {
      map[m[i]] = i;
    }
    Arrays.sort(nums, new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        int num1 = 0, num2 = 0;
        for (char c: o1.toCharArray()) {
          num1 = 10 * num1 + map[c-'0'];
        }
        for (char c: o2.toCharArray()) {
          num2 = 10 * num2 + map[c-'0'];
        }

        if (num1 != num2) return num1 - num2;
        else return 0;
      }
    });
    return nums;
  }

  public static int MatrixGame(int[][] matrix) {
    int row = matrix.length, col = matrix[0].length;
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int j = 0; j < col; j++) {
      int max = Integer.MIN_VALUE;
      for (int i = 0; i < row; i++) {
        max = Math.max(max, matrix[i][j]);
      }
      pq.offer(-max);
    }
    int tom = 0, jerry = 0;
    boolean toTom = true;
    while (pq.size() > 0) {
      if (toTom) tom += -pq.poll();
      else jerry += -pq.poll();
      toTom = !toTom;
    }
    return tom - jerry;
  }

  public static long sharePurchases(String s) {
    int[] map = new int[3];
    int count = 0;
    int res = 0;
    int l = 0, r = 0;
    while (l < s.length() && r < s.length()) {
      char c = s.charAt(r++);
      if (isABC(c)) {
        if (map[c-'A']++ == 0) count ++;
        while (count == 3) {
//          System.out.println(s.substring(l,r));
          res += s.length() - r + 1;
          char lastChar = s.charAt(l++);
          if (isABC(lastChar)) {
            if (map[lastChar-'A']-- == 1) count --;
          }
        }
      }
    }
    return res;
  }

  private static boolean isABC(char c) {
    return c == 'A' || c == 'B' || c == 'C';
  }

  public static int get60Pair(int[] time) {
    int[] reminders = new int[60];
    int res = 0;
    for (int t: time) {
      int r = t % 60;
      res += reminders[(60 - r)%60];
      reminders[r] ++;
    }
    return res;
  }

  public static void main(String[] args) {
    // 1. strange sorting
    int[] m = {2,1,4,8,6,3,0,9,7,5};
    String[] nums = {"12", "02", "4", "023", "65", "83", "224", "50"};
    System.out.println(Arrays.toString(strangeSorting(m, nums)));

    // 2. matrix game
    int[][] matrix = {{3,7,5,3,4,5},{4,5,2,6,5,4},{7,4,9,7,8,3}};
    System.out.println(MatrixGame(matrix));

    // 3.
    System.out.println(sharePurchases("ABBC"));
  }
}
