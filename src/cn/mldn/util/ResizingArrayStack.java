package cn.mldn.util;

import java.util.Iterator;

/**
 * @Description: Pushdown (LIFO) Stack (resizing array implementation)
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/7/5 16:22
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/7/5 16:22
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */

public class ResizingArrayStack<Item> implements Iterable<Item> {

    private Item[] a = (Item[]) new Object[1];  // stack items
    private int N = 0;

    public boolean isEmpty() {
        return N == 0;
    }

    public int siez() {
        return N;
    }

    private void resize(int max) {
        // Move stack to a new array of size max
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++)  temp[i] = a[i];
        a = temp;
    }

    public void push(Item item) {
        if (N == a.length) resize(2*a.length);
        a[N++] = item;
    }

    public Item pop() {
        // Remove item for top of stack
        Item item = a[--N];
        a[N] = null;  // avoid loitering
        if (N > 0 && N == a.length/4) resize(a.length/2);
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {

        //support LIFO iteration

        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }

        @Override
        public void remove() {}
    }
}
