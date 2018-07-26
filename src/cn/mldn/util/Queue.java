package cn.mldn.util;

import java.util.Iterator;

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
public class Queue<Item> implements Iterable<Item> {
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

    public Iterator<Item> iterator() {
         return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {};

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
