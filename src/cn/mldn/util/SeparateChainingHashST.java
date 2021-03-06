package cn.mldn.util;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/8/2 10:16
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/8/2 10:16
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class SeparateChainingHashST<Key, Value> {

    private int N;
    private int M;
    private SequentialSearchST<Key, Value>[] st;

    private SeparateChainingHashST() {
        this(997);
    }

    private SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    private int size() {
        return N;
    }

    private boolean contains(Key key) {
        return get(key) != null;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }

    public void put(Key key, Value val) {
        st[hash(key)].put(key, val);
    }


}
