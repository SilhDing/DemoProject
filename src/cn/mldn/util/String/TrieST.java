package cn.mldn.util.String;

import cn.mldn.util.Queue;

/**
 * @Description: Trie symbol table
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.String
 * @Author: Yihang Ding
 * @CreateDate: 2019/1/11 16:13
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2019/1/11 16:13
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class TrieST<Value> {

    private static int R = 256;
    private Node root;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        else return (Value) x.val;
    }

    private Node get(Node x, String key, int d) {
        // d represents "depth"
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> queue = new Queue<>();
        collect(get(root, pre, 0), pre, queue);
        return queue;

    }

    private void collect(Node x, String pre, Queue<String> queue) {
        // collect the string, if possible, in x and the following ones
        if (x == null) return ;
        if (x.val != null) queue.enqueue(pre);
        for (char c = 0; c < R; c++) {
            collect(x.next[c], pre+c, queue);
        }
    }

    public Iterable<String> keysThatMatch(String pat) {
        // pat means pattern
        // wildcard matching
        Queue<String> queue = new Queue<>();
        collect(root, "", pat, queue);
        return queue;
    }

    private void collect(Node x, String pre, String pat, Queue<String> queue) {
        if (x == null) return ;
        int d = pre.length();
        if (d == pat.length()) {
            if (x.val != null) queue.enqueue((String)x.val);
            return ;
        }

        char next = pat.charAt(d);
        for (char c = 0; c < R; c++) {
            if (next == '.' || c == next) {
                collect(x.next[c], pre + c, pat, queue);
            }
        }
    }

    public String longestPrefixOf(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    private int search(Node x, String s, int d, int length) {
        // Note: there are two cases to terminat the recursive call
        // 1. we reach a null node
        // 2. we reach the end of the string s

        if (x == null) return length;
        if (x.val == null) length = d; // once we find a existing prefix, update the result;
        if (d == s.length()) return length; // rearch is over

        char c = s.charAt(d);
        return search(x.next[c], s, d+1, length);

    }

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;

        if (d == key.length()) {
            // find one and delete it
            x.val = null;
        } else {
            // not find yet and keep going
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d+1);
        }

        // after deletion, check all links
        // 1. if there are other links, stop;
        // 2. if there is a value, stop;

        if (x.val != null) return x;
        for (char c = 0; c < R; c++) {
            if (x.next[c] != null) return x;
        }

        return null;
    }
    // my own implementation
    // but note: to initialize root first
    // which is not necessary for the methods above
    // but note that get and put above is more general:
    // they are good tools for implement other methods

    public Value get2(String key) {
        // my own way to implement this
        Node cur = root;
        for (char c: key.toCharArray()) {
            if (cur.next[c] == null) return null;
            cur = cur.next[c];
        }
        return (Value) cur.val;
    }

    public void put2(String key, Value val) {
        // my own way to implement this
        Node cur = root;
        for (char c: key.toCharArray()) {
            if (cur.next[c] == null) cur.next[c] = new Node();
            cur = cur.next[c];
        }
        cur.val = val;
    }

    public static void main(String[] args) {
        TrieST<Integer> trie = new TrieST<>();
        trie.put("dede", 0);
        trie.put("de", 0);
        trie.put("deeffe", 0);

        for (String str: trie.keys()) {
            System.out.print( str+" ");
        }
        System.out.println();
        System.out.println(trie.root);
    }

}
