package cn.mldn.util;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 8/19/19 6:21 PM
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 8/19/19 6:21 PM
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Solution {

    static String encrypt(String message, int shift) {
        long running = 0;
        long curShift= shift;
        StringBuilder sb = new StringBuilder();
        boolean isLastCharLetter = false;

        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                // see letters
                sb.append(getEncoded(c, (int)(curShift + running)));
                isLastCharLetter = true;
            } else {
                // not a letter
                if (isLastCharLetter) {
                    // reset the parameters
                    curShift += running;
                    running = 0;
                }

                if (Character.isDigit(c)) running = 10 * running + c - '0';
                else {
                    curShift += running;
                    running = 0;
                }
                sb.append(c);
                isLastCharLetter = false;
            }
        }

        return sb.toString();

    }

    static String encrypt2(String message, int shift) {
        long running = 0;
        long curShift= shift;
        int sign = 1;
        StringBuilder sb = new StringBuilder();
        boolean isLastCharLetter = false;

        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                // see letters
                sb.append(getEncoded(c, (int)(curShift + sign * running)));
                isLastCharLetter = true;
            } else {
                // not a letter
                if (isLastCharLetter) {
                    // reset the parameters
                    curShift += sign * running;
                    running = 0;
                    sign = 1;
                }

                if (Character.isDigit(c)) running = 10 * running + c - '0';
                else {
                    if (c == '-') sign = -1;
                    curShift += running;
                    running = 0;
                }
                sb.append(c);
                isLastCharLetter = false;
            }
        }

        return sb.toString();

    }

    static char getEncoded(char c, int shift) {
        char[] array = {'a', 'b', 'c', 'd', 'e', 'f',
                        'g', 'h', 'i', 'j', 'k', 'l',
                        'm', 'n', 'o', 'p', 'q', 'r',
                        's', 't', 'u', 'v', 'w', 'x',
                        'y', 'z'};
        int newIndex = (c - 'a' + shift) % 26;
        if (newIndex < 0) newIndex += 26;
        return array[newIndex];
    }

    static String encrypt3(String message, int shift) {
        long running = 0;
        long curShift= shift;
        int sign = 1;
        boolean reverse = false;
        StringBuilder sb = new StringBuilder();
        boolean isLastCharLetter = false;

        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                // see letters
                sb.append(getEncodedReverse(c, (int)(curShift + sign * running), reverse));
                isLastCharLetter = true;
            } else {
                // not a letter
                if (isLastCharLetter) {
                    // reset the parameters
                    curShift += sign * running;
                    running = 0;
                    sign = 1;
                }

                if (Character.isDigit(c)) running = 10 * running + c - '0';
                else if (c == '-') sign = -1;
                else if (c == '!') reverse = !reverse;
                sb.append(c);
                isLastCharLetter = false;
            }
        }

        return sb.toString();
    }

    static String encrypt4(String message, int shift) {
        return helperEncrypt(message, shift, false).toString();
    }

    static StringBuilder helperEncrypt(String message, int shift, boolean reverse) {
        long running = 0;
        long curShift = shift;
        boolean isLastCharLetter = false;
        int sign = 1;
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < message.length()) {
            char c = message.charAt(i);
            if (Character.isLetter(c)) {
                // see letters
                sb.append(getEncodedReverse(c, (int)(curShift + sign * running), reverse));
                isLastCharLetter = true;
                i ++;
            } else {
                // not a letter
                if (isLastCharLetter) {
                    // reset the parameters
                    curShift += sign * running;
                    running = 0;
                    sign = 1;
                }

                sb.append(c);
                if (Character.isDigit(c)) {
                    running = 10 * running + c - '0';
                    i ++;
                } else if (c == '-') {
                    sign = -1;
                    i ++;
                } else if (c == '!') {
                    reverse = !reverse;
                    i ++;
                } else if (c == '(') {
                    int rightIndex = nextRightIndex(message, i);
                    sb.append(helperEncrypt(message.substring(i+1, rightIndex),
                            (int)(curShift + sign * running), reverse));
                    i = rightIndex;
                } else {
                    i ++;
                }
                isLastCharLetter = false;
            }
        }
        return sb;
    }

    static int nextRightIndex(String message, int start) {
        // TODO
        int count = 0;
        for (int i = start; i < message.length(); i++) {
            char c = message.charAt(i);
            if (c == '(') {
                count ++;
            } else if (c == ')') {
                count --;
                if (count == 0) return i;
            }
        }
        return message.length();
    }

    static char getEncodedReverse(char c, int shift, boolean reverse) {
        char[] array = {'a', 'b', 'c', 'd', 'e', 'f',
                'g', 'h', 'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x',
                'y', 'z'};
        int newIndex = (c - 'a' + shift) % 26;
        if (newIndex < 0) newIndex += 26;
        if (reverse) newIndex = 25 - newIndex;
        return array[newIndex];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.encrypt("he2l9lo wo1rld@", 3));
//        System.out.println(solution.encrypt("a1a1a1a1a1a1", 3));
//        System.out.println(solution.encrypt2("he12l9lo wo-1rld", 7));
//        System.out.println(solution.encrypt2("a-1a-1a-1a-1a-1", 7));
        System.out.println(solution.encrypt4("a-0!a", 30));
    }
}
