package cn.mldn.util.String;

import cn.mldn.util.Queue;

/**
 * @Description: Ternary seach tries (TSTs)
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.String
 * @Author: Yihang Ding
 * @CreateDate: 2019/1/13 13:42
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2019/1/13 13:42
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class TST<Value> {

    private Node root;

    private class Node {
        char c;
        Node left, mid, right;
        Value val;
    }

    public Value get(String key) {

        Node node = get(root, key, 0);
        if (node == null) return null;
        else return node.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        char c = key.charAt(d);
        if (c < x.c) return get(x.left, key, d);
        else if (c > x.c) return get(x.right, key, d);
        else if (d < key.length() - 1) return get(x.mid, key, d+1);
        else return x;
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }
        if (c < x.c) x.left = put(x.left, key, val, d);
        else if (c > x.c) x.right = put(x.right, key, val, d);
        else if (d < key.length() - 1) x.mid = put(x.mid, key, val, d+1);
        else x.val = val;

        return x;
    }

    public Iterable<String> keys() {
        // Note you cannot use jeysWithPrefix("") here!
        Queue<String> queue = new Queue<>();
        collect(root, "", queue);
        return queue;
    }

    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> queue = new Queue<>();
        Node node = get(root, pre, 0);
        if (node == null) return queue; // finish
        else {
            if (node.val != null) queue.enqueue(pre);  // pre itself is a key
            collect(get(root, pre, 0).mid, pre, queue); // keep finding
        }
        return queue;
    }

    public void collect(Node x, String pre, Queue<String> queue) {
        if (x == null) return ;
        if (x.val != null) queue.enqueue(pre+x.c);

        collect(x.left, pre, queue);
        collect(x.mid, pre+x.c, queue);
        collect(x.right, pre, queue);
    }

    public Iterable<String> keysThatMatch(String pat) {
        Queue<String> queue = new Queue<>();
        collect(root, "", pat, queue);
        return queue;
    }

    private void collect(Node x, String pre, String pat, Queue<String> queue) {
        if (x == null) return ;

        int d = pre.length();

        // consider left and right
        char cur = pat.charAt(d); // the current char to be matched
        if (cur == '.') {
            collect(x.left, pre, pat, queue);
            collect(x.right, pre, pat, queue);
        } else {
            if (cur < x.c) collect(x.left, pre, pat, queue);
            else if (cur > x.c) collect(x.right, pre, pat, queue);
        }

        // consider the mid one
        if (cur == '.' || cur == x.c) {
            if (d == pat.length() - 1) {
                // finish
                if (x.val != null) queue.enqueue(pre+x.c);
                return ;
            } else collect(x.mid, pre+x.c, pat, queue);
        }
    }

    public String longestPrefixOf(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length+1);
    }

    private int search(Node x, String s, int d, int length) {
        if (x == null) return length;

        char c = s.charAt(d);
        if (c < x.c) return search(x.left, s, d, length);
        else if (c > x.c) return search(x.right, s, d, length);
        else {
            if (x.val != null) length = d;
            if (d == s.length() - 1) return length;
            else return search(x.mid, s, d+1, length);
        }
    }


    public static void main(String[] args) {
        TST<Integer> tst = new TST<>();
        tst.put("se", 1);
        tst.put("sell", 2);
        tst.put("season", 3);
        tst.put("ssaa", 7);
        tst.put("sela", 4);
        tst.put("apple", 5);
        tst.put("the", 6);

        // test prefix ouput
        for (String str: tst.keys()) {
            System.out.print(str+" ");
        }
        System.out.println();

        // test wildcard match
        for (String str: tst.keysThatMatch("s..ade")) {
            System.out.print(str+" ");
        }
        System.out.println();

        System.out.println(tst.longestPrefixOf("selade"));
    }
}
