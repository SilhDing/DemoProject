package cn.mldn.util;

/**
 * @Description: FIFO queue based on linked-list
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/7/5 19:38
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/7/5 19:38
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Queue<Item> {
    private Node first;
    private Node last;
    private int N;

    private class Node {
        // nested class to define nodes
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void enqueue(Item item) {
         // add item to the end of the list
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = oldlast;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        N ++;
    }

    public Item dequeue() {
        // remove item from the beginning of the list
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        N -- ;
        return item;
    }
}