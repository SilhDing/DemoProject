package cn.mldn.util.Graph;

import cn.mldn.util.Stack;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description: Dijkstra's algorithm to find SP
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.Graph
 * @Author: Yihang Ding
 * @CreateDate: 2019/1/6 22:12
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2019/1/6 22:12
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class DijkstraSP {

    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private PriorityQueue<Integer> pq;

    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        this.edgeTo = new DirectedEdge[G.V()];
        this.distTo = new double[G.V()];
        pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(distTo[o1] < distTo[o2]) return -1;
                else if(distTo[o1] < distTo[o2]) return 1;
                else return 0;
            }
        });

        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0;

        pq.offer(s);
        while(pq.size() > 0) {
            relax(G, pq.poll());
        }

    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e: G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;

                if (pq.contains(w)) pq.remove(w);
                // in fact this checking is not necessary
                pq.offer(w);
            }
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(9);
        G.addEdge(new DirectedEdge(4, 5, 0.35));
        G.addEdge(new DirectedEdge(5, 4, 0.35));
        G.addEdge(new DirectedEdge(4, 7, 0.37));
        G.addEdge(new DirectedEdge(5, 7, 0.28));
        G.addEdge(new DirectedEdge(7, 5, 0.28));
        G.addEdge(new DirectedEdge(5, 1, 0.32));
        G.addEdge(new DirectedEdge(0, 4, 0.38));
        G.addEdge(new DirectedEdge(0, 2, 0.26));
        G.addEdge(new DirectedEdge(7, 3, 0.39));
        G.addEdge(new DirectedEdge(1, 3, 0.29));
        G.addEdge(new DirectedEdge(2, 7, 0.34));
        G.addEdge(new DirectedEdge(6, 2, 0.40));
        G.addEdge(new DirectedEdge(3, 6, 0.52));
        G.addEdge(new DirectedEdge(6, 0, 0.58));
        G.addEdge(new DirectedEdge(6, 4, 0.93));
        G.addEdge(new DirectedEdge(8, 3, 0.32));

        int s = 0;
        DijkstraSP sp = new DijkstraSP(G, s);
        for (int t = 0; t < G.V(); t++) {
            System.out.print(s + " to " + t);
            System.out.print(String.format(" (%4.2f): ",sp.distTo(t)));
            if (sp.hasPathTo(t))
                for (DirectedEdge e: sp.pathTo(t))
                    System.out.print(e + ", ");
            else System.out.print("NA");
            System.out.println();
        }

    }
}
