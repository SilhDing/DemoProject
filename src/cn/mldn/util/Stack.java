package cn.mldn.util;

/**
 * @Description: Pushdown stack (linked-list implementation)
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/7/5 19:28
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/7/5 19:28
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Stack<Item> {
    private Node first;
    private int N;

    private class Node {
        // nested class to define nodes
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        // or: return N == 0;
        return first == null;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        // push item to top of stack
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N ++;
    }

    public Item pop() {
        // remove item from top of stack
        Item item = first.item;
        first = first.next;
        N --;
        return item;
    }
}
