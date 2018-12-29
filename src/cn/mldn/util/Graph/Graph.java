package cn.mldn.util.Graph;

import cn.mldn.util.Bag;

/**
 * @Description: This is the basix definition of undirected graph
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.Graph
 * @Author: Yihang Ding
 * @CreateDate: 2018/12/28 17:03
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/12/28 17:03
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Graph {

    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }

    public void addEdge(int v, int w) {
        // there is no delete method in Bag structure
        adj[v].add(w);
        adj[w].add(v);
        E ++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    @Override
    public String toString() {
        String s = V + " vertices, " + E + " edges\n";
        for (int v = 0; v < this.V(); v++) {
            s += v + ": ";
            for (int w : this.adj(v))
                s += w +" ";
            s += "\n";
        }
        return s;
    }

    // some static methods
    // compute the degree of v in graph G
    public static int degree(Graph G, int v) {
        int degree = 0;
        for (int w: G.adj(v)) degree ++;
        return degree;
    }

    // return the max degree of all vertex in the graph G
    public static int maxDegree(Graph G) {

        int max = 0;
        for (int v = 0; v < G.V(); v++) {
            max = Math.max(max, degree(G, v));
        }
        return max;
    }

    // return the average degree of graph G
    public static double avgDegree(Graph G) {
        return 2.0 * G.E() / G.V();
    }

    // count self-loops
    public static int numberOfSelfLoops(Graph G) {
        int count = 0;
        for (int v = 0; v < G.V(); v++) {
            for (int w: G.adj(v)) {
                if (v == w) count ++;
            }
        }

        return count / 2;
    }

    public static void main(String[] args) {

        // a small test
        Graph G = new Graph(5);
        G.addEdge(0, 1);
        G.addEdge(2, 1);
        G.addEdge(3, 4);
        G.addEdge(3, 2);
        System.out.println(G);
    }

}
