package cn.mldn.util.Graph;

import cn.mldn.util.Queue;

import java.util.PriorityQueue;

/**
 * @Description: lazy version of prim's algorithm
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.Graph
 * @Author: Yihang Ding
 * @CreateDate: 2019/1/4 17:25
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2019/1/4 17:25
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class LazyPrimMST {

    private boolean[] marked;
    private Queue<Edge> mst;
    private PriorityQueue<Edge> pq; // pq will poll the smaller ones first
    private double weight;

    public LazyPrimMST(EdgeWeightedGraph G) {
        marked = new boolean[G.V()];
        mst = new Queue<>();
        pq = new PriorityQueue<>();
        weight = 0;

        visit(G, 0); // add the first one
        while(pq.size() > 0) {
            Edge e = pq.poll();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) {
                // this edge is ineligible
                continue;
            }
            mst.enqueue(e);
            weight += e.weight();

            // in fact only v OR w is not marked
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        // mark v and add to pq all edges from v to unmarked vertices
        marked[v] = true;
        for (Edge e: G.adj(v)) {
            if (!marked[e.other(v)]) pq.offer(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return weight;
    }

    public static void main(String[] args) {

        // a test

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
        for (Edge e: mst.mst) {
            System.out.println(e);
        }
        System.out.println(mst.weight());

    }
}
