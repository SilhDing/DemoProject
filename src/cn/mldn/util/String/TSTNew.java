package cn.mldn.util.String;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.String
 * @Author: Yihang Ding
 * @CreateDate: 4/20/20 10:02 PM
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 4/20/20 10:02 PM
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class TSTNew<Value> {
    private Node root;

    private class Node {
        char c;
        Node left, mid, right;
        Value value;
    }

    public Value get(String key) {
        Node node = get(root, key, 0);
        if (node == null) return null;
        // Note: value could be null
        return node.value;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;

        char c = key.charAt(d);
        if (c < x.c) {
            // Go to the left side.
            return get(x.left, key, d);
        } else if (c > x.c) {
            // Go to the right side.
            return get(x.right, key, d);
        } else if (d < key.length() - 1) {
            // Have not reached the end of key, need to go down.
            return get(x.mid, key, d + 1);
        } else {
            // Have reached the end of the key.
            return x;
        }
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

        if (c < x.c) {
            // Go to the left side.
            x.left = put(x.left, key, val, d);
        } else if (c > x.c) {
            // Go to the rigth side.
            x.right = put(x.right, key, val, d);
        } else if (d < key.length() - 1) {
            // Have not reached the end of the key.
            x.mid = put(x.mid, key, val, d + 1);
        } else {
            // Have reached the end of the key.
            x.value = val;
        }

        return x;
    }
}
