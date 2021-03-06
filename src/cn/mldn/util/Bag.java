package cn.mldn.util;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * @Description: Bag based on liked-list (iteration is supported)
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/7/6 16:37
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/7/6 16:37
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Bag<Item> implements Iterable<Item> {
    private Node first;

    private class Node {
        Item item;
        Node next;
    }

    public void add(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    @NotNull
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Bag<Integer> b = new Bag<>();
        b.add(1);
        b.add(2);
        for(int i: b) System.out.println(i);
    }
}
