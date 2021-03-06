package cn.mldn.util;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/7/26 19:07
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/7/26 19:07
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class BST<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;

        public Node(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            N = n;
        }
    }


    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    public void show() {
        System.out.println(show(root, 0));
    }

    private StringBuffer show(Node h, int level) {
        StringBuffer s = new StringBuffer();
        if (h != null) {
            s.append(show(h.right, level + 1));
            for (int i = 0; i < level; i++) {
                s.append("| ");
            }
            s.append(h.key).append("\n");
            s.append(show(h.left, level + 1));
        }
        return s;
    }


    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
        // or the iterative way
        /*
        if (x.left != null) x = x.left;
        return x;
         */
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }


    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        // 在某个tree里面删一个node，那么最后返回的还是这个node
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMin(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {

        // change key's value to val if key is in subtree rooted at x
        // otherwise, add new node to subtree associating key with val at the deepest level of the tree
        // only when insert is randomizes, the tree could be balanced

        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left,  key, val);
        else if(cmp > 0 ) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;

        // if key < x.key, then floor must be in the left subtree
        else if (cmp < 0) return floor(x.left, key);

        // if key > x.key, then floor could Node x and another node in the right subtree
        Node t = floor(x.right, key);
        if (t == null) return x;
        else return t;

    }

    public Key ceiling(Key key) {
        return ceiling(root, key).key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;

        // if key > x.key, then ceiling must be in the right subtree
        else if (cmp > 0) return ceiling(x.right, key);

        // if ley < x.key, then ceiling must be in the left subtree
        Node t = ceiling(x.left, key);
        if (t == null) return x;
        else return t;
    }

    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        // return Node containing key of rank k
        if (x == null) return null;
        int t = size(x.left);  // the index of x is t
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        // return number of keys less than x.key in the subtree rooted at x
        // you may regard it as index of key in an ordered array
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);

    }


    public void delete(Key key) {
        delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        // key is too small, delete that node in the left subtree
        if (cmp < 0) x.left = delete(x.left, key);
        // key is too large, delete that node in the right subtree
        else if (cmp > 0) x.right = delete(x.right, key);
        // the key is found
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x; // save the reference
            x = min(t.right); // x is the subcessor of t;
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return ;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);

        // some node in left subtree meets the requirements
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) keys(x.right, queue, lo, hi);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    public static void main(String[] args) {
        BST<Integer, Integer> map = new BST<>();
        map.put(3,0);
        map.put(1,0);
        map.put(6,5);
        map.put(5,5);
        map.show();
    }

}
