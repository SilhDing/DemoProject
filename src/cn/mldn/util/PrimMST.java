package cn.mldn.util;

import java.util.PriorityQueue;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/8/24 14:25
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/8/24 14:25
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class PrimMST {

    private double[] distTo;
    private Edge[] edgeTo;
    private boolean[] marked;
    private PriorityQueue<Edge> pq;

    public PrimMST(EdgeWeightedGraph G) {

        distTo = new double[G.V()];
        edgeTo = new Edge[G.V()];
        marked = new boolean[G.V()];
        pq = new PriorityQueue<>();

        for (int v = 0; v < G.V(); v++) {
            // initialize the distTo to a very large value for every entry
            distTo[v] = Double.POSITIVE_INFINITY; // this value is 1.0/0.0
        }

        distTo[0] = 0;
        visit(G, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int v = e.either(), w = e.other(v);
            if (!marked[v]) visit(G,v);
            if (!marked[w]) visit(G,w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e: G.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue; // w is in the tree already and the edge is not eligible
            if (e.weight() < distTo[w]) {

                // now we find a shorted edge connecting w to the tree
                distTo[w] = e.weight();

                // the old edge shoule be removed from the pq,
                // if edgeTo[w] is not null or distTo[w] is not infinity then it is in the pq
                if (edgeTo[w] != null) pq.remove(edgeTo[w]);

                pq.add(e);
                edgeTo[w] = e;
            }
        }
    }

    public Iterable<Edge> edges() {
        // edgeTo is what we need
        Bag<Edge> it = new Bag<>();
        for (int i = 1; i < edgeTo.length; i++) {
            it.add(edgeTo[i]);
        }
        return it;
    }

    public double weights() {
        // return the weights of MST
        double res = 0.0;
        for (double weight: distTo) {
            res += weight;
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

        PrimMST mst = new PrimMST(G);
        for (Edge e: mst.edges()) {
            System.out.println(e);
        }

        System.out.println(mst.weights());
    }
}
