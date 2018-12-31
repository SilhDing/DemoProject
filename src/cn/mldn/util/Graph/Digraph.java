package cn.mldn.util.Graph;

import cn.mldn.util.Bag;

/**
 * @Description: class of directed graph
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.Graph
 * @Author: Yihang Ding
 * @CreateDate: 2018/12/30 15:00
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/12/30 15:00
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Digraph {

    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Digraph (int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[])new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w); // v -> w
        this.E ++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w: adj(v)) {
                R.addEdge(w, v);
            }
        }
        return R;
    }
}
