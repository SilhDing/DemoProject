package cn.mldn.util.Graph;

import cn.mldn.util.Queue;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Description: eager versio of prim's algorithm to find MST
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.Graph
 * @Author: Yihang Ding
 * @CreateDate: 2019/1/4 20:08
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2019/1/4 20:08
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class PrimMST {
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private PriorityQueue<Edge> pq;
    private double weight;

    public PrimMST(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        pq = new PriorityQueue<>();
        weight = 0.0;
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);

        distTo[0] = 0.0;
        // this is not necessary
        // edgeTo[0] = null;

        visit(G, 0);
        while (pq.size() > 0) {
            Edge e = pq.poll();
            // e must be MST edges
            weight += e.weight();

            int v = e.either(), w = e.other(v);

            // only v OR w is marked
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);

            // visit(G, marked[v]? w: v);
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        // add v to the tree and update data structures
        marked[v] = true;

        for (Edge e: G.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue; // already on the tree; ineligible
            if (e.weight() < distTo[w]) {
                // need update
                if (edgeTo[w] != null && pq.contains(edgeTo[w])){
                    // in fact, we may remove without the judgment
                    // as remove will not report error if edgeTo[w] does not exist in pq or is null
                    pq.remove(edgeTo[w]);
                }
                pq.add(e);

                distTo[w] = e.weight();
                edgeTo[w] = e;
            }
        }
    }

    public Iterable<Edge> edges() {
        // simply scan the edgeTo[] to get all MST edges
        Queue<Edge> queue = new Queue<>();
        for (Edge e: edgeTo) {
            if (e != null) queue.enqueue(e);
        }
        return queue;
    }

    public double weight() {
        return weight;
    }

    public static void main(String[] args) {

        EdgeWeightedGraph G = new EdgeWeightedGraph(8);

        G.addEdge(new Edge(4, 5, 0.35));
        G.addEdge(new Edge(4, 7, 0.37));
        G.addEdge(new Edge(5, 7, 0.28));
        G.addEdge(new Edge(0, 7, 0.16));
        G.addEdge(new Edge(1, 5, 0.32));
        G.addEdge(new Edge(0, 4, 0.38));
        G.addEdge(new Edge(2, 3, 0.17));
        G.addEdge(new Edge(1, 7, 0.19));
        G.addEdge(new Edge(0, 2, 0.26));
        G.addEdge(new Edge(1, 2, 0.36));
        G.addEdge(new Edge(1, 3, 0.29));
        G.addEdge(new Edge(2, 7, 0.34));
        G.addEdge(new Edge(6, 2, 0.40));
        G.addEdge(new Edge(3, 6, 0.52));
        G.addEdge(new Edge(6, 0, 0.58));
        G.addEdge(new Edge(6, 4, 0.93));

        PrimMST mst = new PrimMST(G);
        for (Edge e : mst.edges()) {
            System.out.println(e);
        }
        System.out.println(mst.weight());

    }
}
