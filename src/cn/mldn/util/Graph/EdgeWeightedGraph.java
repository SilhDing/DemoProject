package cn.mldn.util.Graph;

import cn.mldn.util.Bag;

/**
 * @Description: class for edge-weighted undirected graph
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.Graph
 * @Author: Yihang Ding
 * @CreateDate: 2019/1/2 01:22
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2019/1/2 01:22
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class EdgeWeightedGraph {

    private final int V;
    private int E;
    private Bag<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public int V() { return V; }

    public int E() { return E; }

    public void addEdge(Edge e) {

        // get two vertices here
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        this.E ++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        Bag<Edge> bag = new Bag<>();
        for (int v = 0; v < this.V; v++) {
            for (Edge e: adj[v]) {
                if (e.other(v) > v) bag.add(e);
            }
        }
        return bag;
    }
}
