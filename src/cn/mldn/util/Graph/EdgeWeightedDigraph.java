package cn.mldn.util.Graph;

import cn.mldn.util.Bag;

/**
 * @Description: class for edge-weighted digraphs
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.Graph
 * @Author: Yihang Ding
 * @CreateDate: 2019/1/6 17:28
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2019/1/6 17:28
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class EdgeWeightedDigraph {

    private final int V;
    private int E;
    private Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }

    public void addEdge(DirectedEdge e) {
        adj[e.from()].add(e);
        E ++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> bag = new Bag<>();
        for (int v = 0; v < this.V; v++) {
            for (DirectedEdge e: adj[v])
                bag.add(e);
        }
        return bag;
    }

}
