package cn.mldn.util;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/8/2 10:26
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/8/2 10:26
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class LinearProbingHashST<Key, Value> {

    private int N;
    private int M = 16;
    private Key[] keys;
    private Value[] vals;

    public LinearProbingHashST() {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    public LinearProbingHashST(int Cap) {
        M = Cap;
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    public int size() {return N;}

    public boolean contains(Key key) {
        return get(key) != null;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int cap) {
        LinearProbingHashST<Key, Value> t;
        t = new LinearProbingHashST<>(cap);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null)
                t.put(keys[i], vals[i]);
            keys = t.keys;
            vals = t.vals;
            M = t.M;
        }
    }

    public void put(Key key, Value val) {
        if (N >= M/2) resize(2*M);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key)) {vals[i] = val; return ;}

        keys[i] = key;
        vals[i] = val;
        N ++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }

    public void delete(Key key) {
        if (!contains(key)) return ;
        int i = hash(key);
        while (!key.equals(keys[i]))
            i = (i + 1) % M;

        keys[i] = null;
        vals[i] = null;
        i = (i + 1) % M;
        while (keys[i] != null) {
            Key keyToDo = keys[i];
            Value valToDo = vals[i];
            keys[i] = null;
            vals[i]= null;
            N --;
            put(keyToDo, valToDo);
            i = (i + 1) % M;
        }
        N --;
        if (N > 0 && N == M/8) resize(M/2);
    }
}
