package cn.mldn.util;

import java.util.*;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 8/21/19 4:17 PM
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 8/21/19 4:17 PM
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class VMwareSolution {

    // Question 1

    public static String[] usernameSystem(String[] names) {
        Map<String, Integer> map = new HashMap<>();
        String[] res = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            if (!map.containsKey(name)) {
                map.put(name, 1);
                res[i] = name;
            } else {
                res[i] = name + map.get(name);
                map.put(name, map.get(name) + 1);
            }
        }
        return res;
    }

    // Question 2

    public static List<String> buildSeq(String str) {
        List<String> list = new LinkedList<>();
        visitChar(str, 0, "", list);
        Collections.sort(list);
        return list;
    }

    private static void visitChar(String str, int index, String curString, List<String> list) {
        if (index == str.length()) {
            if (curString.length() > 0) list.add(curString);
            return ;
        }

        char c = str.charAt(index);
        visitChar(str, index+1, curString, list);
        visitChar(str, index+1, curString + c, list);
    }

    // Question 3
    public static int evenSubarray(List<Integer> numbers, int k) {
        // Write your code here
        HashSet<String> set = new HashSet<>();
        // int[] arr = new int[numbers.size()];
        // for (int i = 0; i < arr.length; i++) arr[i] = numbers.get(i);
        int size = numbers.size();
        int[] count = new int[size];
        int running = 0;
        for (int i = 0; i < size; i++) {
            if (isOdd(numbers.get(i))) running ++;
            count[i] = running;
        }

        for (int i = 0; i < size; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < size; j++) {
                sb.append(numbers.get(j)).append(" ");
                int lastPart = i == 0? 0:count[i-1];
                if (count[j] - lastPart > k) break;
                set.add(sb.toString());
            }
        }
        return set.size();
    }

    private static boolean isOdd(int i) {
        return i % 2 == 1;
    }

    // Question 4 perfect team
    public static int differenceTeams(String str) {
        HashMap<Character, Integer>  map = new HashMap<>();
        map.put('p', 0);
        map.put('c', 0);
        map.put('m', 0);
        map.put('b', 0);
        map.put('z', 0);
        for (char c: str.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0)+1);
        }

        int res = Integer.MAX_VALUE;
        for (int value: map.values()) res = Math.min(res, value);
        return res;
    }

    // Question 5 shift string
    public static String getShiftesString(String str, int leftShifts, int rightShifts) {
        int length = str.length();
        int leftS = leftShifts % length - rightShifts % length;
        if (leftS < 0) leftS = length + leftS;
        return str.substring(leftS) + str.substring(0, leftS);
    }

    // Question 6 maximal suqare
    public static int largestMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int row = matrix.length, col = matrix[0].length;
        int[][] dp = new int[row+1][col+1];
        int res = 0;

        // If i or j is 0, the result is always 0
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (matrix[i-1][j-1] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }

    // Question 7 group anagram

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> list = new LinkedList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str: strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String code = new String(array);

            if (!map.containsKey(code)) {
                map.put(code, new LinkedList<>());
            }

            map.get(code).add(str);
        }

        for (List<String> l: map.values()){
            list.add(l);
        }

        return list;
    }


    // Question 8 Team formation 2
    public static int countTeams(int[] skills, int k, int l, int r) {
        int num = 0;
        for (int skill: skills) {
            if (skill >= l && skill <= r) num ++;
        }
        if (num < k) return 0;

        int[] factorial = new int[num+1];
        factorial[0] = 1;
        for (int i = 1; i <= num; i++) factorial[i] = factorial[i-1] * i;

        int res = 0;
        for (int i = k; i <= num; i++) res += factorial[num]/(factorial[i] * factorial[num-i]);
        return res;
    }

    // Question 9 scores of string
    public static String score(String s1, String s2) {
        HashMap<Character, Integer> score = new HashMap<>();
        score.put('E', 1);
        score.put('M', 3);
        score.put('H', 5);
        int score1 = getScore(s1, score);
        int score2 = getScore(s2, score);

        if (score1 > score2) return s1;
        else if (score1 < score2) return s2;
        else return "TIE";
    }

    // Question 10
    public static List<String> filter(String[] strs) {
        HashSet<String> set = new HashSet<>();
        List<String> list = new LinkedList<>();

        for (String str: strs) {
            String code = getCode(str);
            if (!set.contains(code)) {
                set.add(code);
                list.add(str);
            }
        }
        return list;
    }

    private static String getCode(String str) {
        int[] chars = new int[26];
        for (char c: str.toCharArray()) {
            chars[c-'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (chars[i] > 0) {
                sb.append((char)('a'+i)).append(chars[i]);
            }
        }
        return sb.toString();
    }

    // Question 11 list collison
    public static int collide(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (int num: arr) pq.offer(num);
        while (pq.size() > 1) {
            int num1 = pq.poll();
            int num2 = pq.poll();
            if (num1 != num2) pq.offer(num1-num2);
        }

        return pq.size() > 0? pq.peek(): 0;
    }

    private static int getScore(String str, HashMap<Character, Integer> score) {
        int res = 0;
        for (char c: str.toCharArray()) res += score.getOrDefault(c, 0);
        return 0;
    }

    // Question 12 Intelligent Substring
    public static int getSpecialSubstring(String s, int k, String charValue) {
        // sliding window
        int res = 0;

        // remember: at most k normal chars
        int left = 0, right = 0;
        int count = 0;
        while (right < s.length()) {
            char cur = s.charAt(right);
            if (isNormal(cur, charValue)) {
                count += 1;
            }
            right ++;
            // make sure at most k normal chars
            while (count > k) {
                if (isNormal(s.charAt(left), charValue)) count --;
                left ++;
            }

            res = Math.max(res, right - left);
        }
        return res;
    }

    public static List<int[]> getThreads(String[] strs) {
        List<int[]> list = new LinkedList<>();
        Map<String, int[]> str2Thread = new HashMap<>();
        int threadId = 1;
        for (String str: strs) {
            String[] array = str.split(",");
            String sender = array[0];
            String receiver = array[1];
            String[] contents = array[2].split("---");
            String newStr = sender + " " + receiver + " " + Arrays.toString(contents);
            System.out.println(newStr);

            if (contents.length == 1) {
                // the start of a new thread
                int[] thread = new int[]{threadId++, 1};
                list.add(thread);
                str2Thread.put(newStr, thread);
            } else {
                String[] lastContent = new String[contents.length - 1];
                for (int i = 0; i < lastContent.length; i++) {
                    lastContent[i] = contents[i+1];
                }
                String lastContentStr = receiver + " " + sender + " " + Arrays.toString(lastContent);
                System.out.println(lastContentStr);
                int[] lastThread = str2Thread.get(lastContentStr);
                int[] newThread = new int[]{lastThread[0], lastThread[1]+1};
                list.add(newThread);
                str2Thread.put(newStr, newThread);
            }
        }
        return list;
    }

    private static boolean isNormal(char c, String charValue) {
        return charValue.charAt(c-'a') == '0';
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(solution.usernameSystem(new String[]{"bob", "alice", "bob", "alice",
//                "bob"})));
//        System.out.println(solution.buildSeq("ba"));
//        System.out.println(VMwareSolution.differenceTeams("pcmbpcmbp"));
//        System.out.println(solution.getShiftesString("abcde", 0, 2));
//        System.out.println(solution.largestMatrix(new int[][]{{1,1,1,1,1},
//                                                              {1,1,0,0,0}}));
//        System.out.println(VMwareSolution.countTeams(new int[]{12,4,6,13,5,10}, 3, 4, 10));
//        System.out.println(VMwareSolution.getSpecialSubstring("abcde", 2, "10101111111111111111111111"));
//        List<int[]> res = VMwareSolution.getThreads(
//                new String[]{"a@gmail.com,b@gmail.com,how are you?",
//                             "b@gmail.com,a@gmail.com,I am good---how are you?",
//                             "a@gmail.com,c@gmail.com,Hi",
//                             "a@gmail.com,b@gmail.com,what's your name?",
//                             "a@gmail.com,b@gmail.com,Me too!---I am good---how are you?"}
//        );
//        for (int[] arr: res) System.out.println(Arrays.toString(arr));
    }
}
