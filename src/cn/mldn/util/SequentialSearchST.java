package cn.mldn.util;

/**
 * @Description: 单链表构成的ST，每次更新和查找都需要线性查找
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/7/26 16:51
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/7/26 16:51
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class SequentialSearchST<Key, Value> {
    private Node first;
    
    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
    
    public Value get(Key key) {
        // search for key, return associated value
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) return x.val; // search hit
        }
        return null;  // search miss
    }
    
    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                // search hit, and update the value
                x.val = val;
                return ;
            }
        }
        // search miss, and add a new node at the first
        first = new Node(key, val, first);
        
    }
}
