package cn.mldn.util;

import java.util.PriorityQueue;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/8/24 11:15
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/8/24 11:15
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class LazyPrimMST {

    private boolean[] marked;
    private PriorityQueue<Edge> pq;
    private Queue<Edge> queue;

    public LazyPrimMST(EdgeWeightedGraph G) {
        pq = new PriorityQueue<>();
        marked = new boolean[G.V()];
        queue = new Queue<>();

        visit(G, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue; //  this edge is not eligible anymore
            queue.enqueue(e);
            if (!marked[w]) visit(G, w);
            if (!marked[v]) visit(G, v);

        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e: G.adj(v)) {
            if (!marked[e.other(v)]) pq.add(e);
        }
    }

    public Iterable<Edge> edges() {
        return queue;
    }

    public double weights() {
        // return the total weights of the MST
        // we only calculate the total weight when needed so this is lazy version
        double res = 0;
        for (Edge e: queue) {
            res += e.weight();
        }
        return res;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph G = new EdgeWeightedGraph(8);
        G.addEdge(new Edge(4,5,0.35));
        G.addEdge(new Edge(4,7,0.37));
        G.addEdge(new Edge(5,7,0.28));
        G.addEdge(new Edge(0,7,0.16));
        G.addEdge(new Edge(1,5,0.32));
        G.addEdge(new Edge(0,4,0.38));
        G.addEdge(new Edge(2,3,0.17));
        G.addEdge(new Edge(1,7,0.19));
        G.addEdge(new Edge(0,2,0.26));
        G.addEdge(new Edge(1,2,0.36));
        G.addEdge(new Edge(1,3,0.29));
        G.addEdge(new Edge(2,7,0.34));
        G.addEdge(new Edge(6,2,0.40));
        G.addEdge(new Edge(3,6,0.52));
        G.addEdge(new Edge(6,0,0.58));
        G.addEdge(new Edge(6,4,0.93));

        LazyPrimMST mst = new LazyPrimMST(G);
        for (Edge e: mst.edges()) {
            System.out.println(e);
        }

        System.out.println(mst.weights());

    }
}
