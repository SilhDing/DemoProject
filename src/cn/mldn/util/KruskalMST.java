package cn.mldn.util;

import java.util.PriorityQueue;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/8/24 16:02
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/8/24 16:02
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class KruskalMST {

    private Queue<Edge> queue;
    private double weights;
    private UF uf;

    public KruskalMST(EdgeWeightedGraph G) {
        queue = new Queue<>();
        weights = 0;
        uf = new UF(G.V());
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (Edge e: G.edge()) pq.add(e);

        while (!pq.isEmpty() && queue.size() < G.V() - 1) {
            Edge e  = pq.poll();
            int v = e.either(), w = e.other(v);
            if (uf.connected(v,w)) continue;
            uf.union(v,w);
            weights += e.weight();
            queue.enqueue(e);
        }
    }

    public Iterable<Edge> edges() {
        return queue;
    }

    public double weights() {
        return weights;
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
        KruskalMST mst = new KruskalMST(G);
        for (Edge e: mst.edges()) {
            System.out.println(e);
        }
        System.out.println(mst.weights());
    }
}
