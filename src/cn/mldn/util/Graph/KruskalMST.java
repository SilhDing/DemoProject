package cn.mldn.util.Graph;

import cn.mldn.util.Queue;
import cn.mldn.util.UF;

import java.util.PriorityQueue;

/**
 * @Description: Kruskal's algorithm to find MST
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.Graph
 * @Author: Yihang Ding
 * @CreateDate: 2019/1/4 21:59
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2019/1/4 21:59
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class KruskalMST {

    private Queue<Edge> mst;
    private double weight;

    public KruskalMST(EdgeWeightedGraph G) {
        mst = new Queue<>();
        weight = 0.0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (Edge e: G.edges()) pq.offer(e);

        UF uf = new UF(G.V());

        while (pq.size() > 0 && mst.size() < G.V() - 1) {
            // the max size of queue is V-1;
            Edge e = pq.poll();
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) continue; // e forms a cycle
            uf.union(v, w);
            mst.enqueue(e);
            weight += e.weight();
        }
    }

    public Iterable<Edge> edges() {
        return mst;
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

        KruskalMST mst = new KruskalMST(G);
        for (Edge e : mst.edges()) {
            System.out.println(e);
        }
        System.out.println(mst.weight());

    }
}
