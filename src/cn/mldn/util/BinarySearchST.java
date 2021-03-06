package cn.mldn.util;
/**
 * @Description: 基于ordered array实现的一个ST，主要是binary search来实现查找
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/7/26 16:59
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/7/26 16:59
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {

    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void deleteMin() {
        delete(keys[0]);
    }

    public void deleteMax() {
        delete(keys[N-1]);
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        // before return the value, we have to check whether the key exists since rank() cannot assert it
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        else return null;
    }

    public int rank(Key key) {
        // essentially it is a binary search in the ordered array
        // 事实上，rank函数不会保证元素能够被找到：输出结果是0~N
        // 要是输出为N，这个时候已经越界，说明key没找到，要插入的话就插到最后
        // 要是输出为0 ~ N-1，也不一定说明找到，返回可能是Key的位置，或者应该被插入的位置
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;  // 如果停在这里，说明找到
        }
        return lo; // 没有找到，输出一个应该被插入的位置
    }

    public void put(Key key, Value val) {
        int i = rank(key);
        // the node already exists
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return ;
        }

        // insert a new Node
        for (int j = N; j> i; i--) {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        N ++;
    }

    public void delete(Key key) {
        // similar to put()
        int i = rank(key);

        // the node in fact does not exist
        if (i >= N || keys[i].compareTo(key) != 0) return ;

        // find the node and delete it
        for (int j = i; j < N - 1; j++) {
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }
        keys[N-1] = null;
        vals[N-1] = null;
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N-1];
    }

    public Key select(int k) {
        // select the node with index k
        return keys[k];
    }

    public Key ceiling(Key key) {
        int i = rank(key);
        return keys[i];
    }

    public Key floor(Key key) {
        // 自己写的不一定对
        int i = rank(key);
        if (i == 0) return null;
        else return keys[i-1];
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        for (int i = rank(lo); i < rank(hi); i++) {
            queue.enqueue(keys[i]);
        }
        if (contains(hi)) {
            queue.enqueue(keys[rank(hi)]);
        }
        return queue;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }
}
