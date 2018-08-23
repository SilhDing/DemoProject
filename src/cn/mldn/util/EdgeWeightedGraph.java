package cn.mldn.util;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/8/23 15:49
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/8/23 15:49
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
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Edge>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(Edge e) {
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E ++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edge() {
        // return all edges in the graph
        Bag<Edge> b = new Bag<>();
        for ( int v = 0; v < V; v++) {
            for (Edge e: adj[v]) {
                if (e.other(v) > v) b.add(e);
            }
        }
        return b;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph graph = new EdgeWeightedGraph(4);
        graph.addEdge(new Edge(0,2, 0.5));
        graph.addEdge(new Edge(1,3, 2));
        graph.addEdge(new Edge(1,2, 0.53));
    }
}
