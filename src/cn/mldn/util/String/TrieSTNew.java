package cn.mldn.util.String;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.String
 * @Author: Yihang Ding
 * @CreateDate: 4/19/20 6:54 PM
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 4/19/20 6:54 PM
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class TrieSTNew<Value> {

    private static int R = 256;
    private Node root;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    // When finding the node, we use recursion rather than iteration.
    public Value get(String key) {
        Node node = get(root, key, 0);
        if (node == null) return null;
        return (Value) node.val;
    }

    private Node get(Node x, String key, int d) {
        // Return value associated with key in the subtrie rooted at x.
        if (x == null) return null;
        // Remember, right now we are at the node corresponding to char at index d-1
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    public void put(String key, Value value) {
        root = put(root, key, value, 0);
    }

    public Node put(Node x, String key, Value value, int d) {
        if (x == null) x = new Node();
        // Remember, right now we are at the node corresponding to char at index d-1
        if (d == key.length()) {
            x.val = value;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, value, d + 1);
        return x;
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> queue = new LinkedList<>();
        collect(this.get(root, pre, 0), pre, queue);
        return queue;
    }

    public void collect(Node x, String pre, Queue<String> q) {
        // Starting with Node x, find all keys
        if (x == null) return ;
        if (x.val != null) q.add(pre);
        for (char c = 0; c < R; c++) {
            collect(x.next[c], pre + c, q);
        }
    }

    public Iterable<String> keysThatMatch(String pat) {
        Queue<String> queue = new LinkedList<>();
        collect(root, "", pat, queue);
        return queue;
    }

    private void collect(Node x, String pre, String pat, Queue<String> queue) {
        if (x == null) return ;
        int len = pre.length();

        if (len == pat.length()) {
            if (x.val != null) queue.add(pre);
            return ;
        }

        char next = pat.charAt(len);
        for (char c = 0; c < R; c ++) {
            if (c == next || c == '.')
                collect(x.next[c], pre + c, pat, queue);
        }
    }

    public String longestPrefixOf(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    private int search(Node x, String s, int d, int length) {
        if (x == null) return length;
        if (x.val != null) length = d;
        if (d == s.length()) return length;
        char c = s.charAt(d);
        return search(x.next[c], s, d + 1, length);
    }

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    public Node delete(Node x, String key, int d) {
        if (x == null) return null;

        if (d == key.length()) {
            // Find the node representing the key
            x.val = null;
        } else {
            // Keep gping down and find the node.
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }

        if (x.val != null) return x;

        for (char c = 0; c < R; c ++) {
            // Check if x have a non-null link
            if (x.next[c] != null) return x;
        }
        return null;
    }


    public static void main(String[] args) {
        TrieSTNew<Integer> trie = new TrieSTNew<>();
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
