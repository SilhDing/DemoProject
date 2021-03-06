package cn.mldn.util;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/7/27 23:18
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/7/27 23:18
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private Node root;

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        Key key;
        Value val;
        Node left, right;
        int N;
        boolean color;

        public Node(Key key, Value val, int n, boolean color) {
            this.key = key;
            this.val = val;
            N = n;
            this.color = color;
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
            for (int i = 0; i < level; i++) s.append("| ");
            if (h.color == RED) {
                s.setLength(s.length() - 1);
                s.append("-");
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

    private boolean isRed(Node h) {
        if (h == null) return false;
        return h.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null) return new Node(key, val, 1, RED);

        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        h.N = 1 + size(h.left) + size(h.right);
        return h;
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

    // Deletion

    private Node balance(Node h) {
        // you may regard this method as a standard way to fix up the 2-3 tree with the rules specified
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        // OR if (isRed(h.right)) is enough. But it is redundant
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) {
            // you do not have to rotate h.left since the structure is alread what we need (red link are always at the left)
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    public void deleteMin() {
        root = deleteMin(root);
        if (root != null) root.color = BLACK;
    }

    private Node deleteMin(Node h) {
        if (h.left == null) return null;
        if (!isRed(h.left) && !isRed(h.left.left)) {
            // if we neet 2-node here (h.left) we have to move red link o the left to eliminate the 2-node
            h = moveRedLeft(h);
        }
        h.left = deleteMin(h.left);
        return balance(h); // balance is used to adjust the stucture
    }

    public void deleteMax() {
        root = deleteMax(root);
        if (root != null) root.color = BLACK;
    }

    private Node deleteMax(Node h) {
        if (isRed(h.left)) {
            // deleteMax()这里为什么会有这么一个，而deleteMin()没有？因为h.right不可能是red啊
            // 删除最右边元素的时候，左边还有可能有red的（为什么一定是red？去看书上的解释）
            // 删除最左边的时候，右边会有东西吗？
            // 不会。因为首先，右边有东西，那就相当于是1.插入的，但是插入的话，只可能red插入，会放去左边
            // 2. 左边被删除过，但是删除的话，就得在3-node中删除，删完了还会修正，不可能在右边留东西的
            h = rotateRight(h);
        }
        if (h.right == null) return null;
        if (!isRed(h.right) && !isRed(h.right.left)) h = moveRedRight(h);
        h.right = deleteMax(h.right);
        return balance(h);
    }

    public void delete(Key key) {
        root = delete(root, key);
        if (root != null) root.color = BLACK;
    }

    private Node delete(Node h, Key key) {
        if (key.compareTo(h.key) < 0) {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        } else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                h.val = get(h.right, min(h.right).key);
                h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }

        return balance(h);
    }

    public static void main(String[] args) {
        RedBlackBST<Character, Integer> map = new RedBlackBST<>();
        map.put('S', 0);
        map.put('E', 0);
        map.put('A', 0);
        map.put('R', 0);
        map.put('C', 0);
        map.put('H', 0);
        map.put('X', 0);
        map.show();
        map.put('B', 0);
        map.show();
        map.delete('E');
        map.show();
    }
}
