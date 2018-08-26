package cn.mldn.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/8/25 16:46
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/8/25 16:46
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class DijkstraSP {

    class Dist {
        // since we do not hava implementations of indexPQ, we define a class here then we may find a vertex by a dist
        // if you use the indexPQ then no need for this class

        // source -> V  = dist
        double dist;
        int V;

        public Dist(int v, double dist) {
            this.V = v;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return V + ": " + dist;
        }
    }

    private Dist[] distTo;
    private DirectedEdge[] edgeTo;
    private PriorityQueue<Dist> pq;

    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        distTo = new Dist[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        pq = new PriorityQueue<>(new Comparator<Dist>() {
            @Override
            public int compare(Dist o1, Dist o2) {
                if (o1.dist < o2.dist) return -1;
                else if (o1.dist == o2.dist) return 0;
                else return 1;
            }
        });

        // initialization
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = new Dist(v, Double.POSITIVE_INFINITY);
        }
        distTo[s].dist = 0;

        pq.add(distTo[s]);

        while (pq.size() > 0) {
            relax(G, pq.poll().V);
        }
    }

    public void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w].dist > distTo[v].dist + e.weight()) {
                // update the distTo[w]

                // delete the old one, if any
                pq.remove(distTo[w]);

                edgeTo[w] = e;
                distTo[w].dist = distTo[v].dist + e.weight();
                pq.add(distTo[w]);
            }
        }
    }

    public double distTo(int v) {
        return distTo[v].dist;
    }

    public boolean hasPathTo(int v) {
        return distTo[v].dist < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(9);
        G.addEdge(new DirectedEdge(4,5,0.35));
        G.addEdge(new DirectedEdge(5,4,0.35));
        G.addEdge(new DirectedEdge(4,7,0.37));
        G.addEdge(new DirectedEdge(5,7,0.28));
        G.addEdge(new DirectedEdge(7,5,0.28));
        G.addEdge(new DirectedEdge(5,1,0.32));
        G.addEdge(new DirectedEdge(0,4,0.38));
        G.addEdge(new DirectedEdge(0,2,0.26));
        G.addEdge(new DirectedEdge(7,3,0.37));
        G.addEdge(new DirectedEdge(1,3,0.29));
        G.addEdge(new DirectedEdge(1,3,0.29));
        G.addEdge(new DirectedEdge(2,7,0.34));
        G.addEdge(new DirectedEdge(6,2,0.40));
        G.addEdge(new DirectedEdge(3,6,0.52));
        G.addEdge(new DirectedEdge(6,0,0.58));
        G.addEdge(new DirectedEdge(6,4,0.93));

        DijkstraSP sp = new DijkstraSP(G, 0);
        System.out.println(Arrays.toString(sp.distTo));
        for (DirectedEdge e: sp.pathTo(6)) {
            System.out.println(e);
        }

    }
}