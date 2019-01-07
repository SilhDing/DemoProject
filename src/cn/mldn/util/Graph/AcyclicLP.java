package cn.mldn.util.Graph;

import cn.mldn.util.Stack;

/**
 * @Description: LP algorithm for Edge-weighted DAG
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.Graph
 * @Author: Yihang Ding
 * @CreateDate: 2019/1/7 17:04
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2019/1/7 17:04
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class AcyclicLP {

    private double[] distTo;
    private DirectedEdge[] edgeTo;

    public AcyclicLP(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        getTopologicalSort(G);

        for (int v: topological) {
            relax(G, v);
        }
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e: G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] - e.weight()) {
                // switch the sense here
                // it equals to: -distTo[w] > -distTo[v] - e.weight()
                distTo[w] = distTo[v] - e.weight();
                edgeTo[w] = e;
            }
        }
    }

    public double distTo(int v) {
        return -distTo[v];
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

    Stack<Integer> topological;
    boolean[] marked;

    private void getTopologicalSort(EdgeWeightedDigraph G) {
        // since the constructor of Topological does not accept EdgeWeightedDigraph
        // we here write a independent function to get that order
        // a simple dfs

        topological = new Stack<>();
        marked = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if(!marked[v]) dfs(G, v);
        }
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        marked[v] = true;
        for (DirectedEdge e: G.adj(v)) {
            int w = e.to();
            if (!marked[w]) dfs(G, w);
        }

        topological.push(v);
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(8);
        G.addEdge(new DirectedEdge(5, 4, 0.35));
        G.addEdge(new DirectedEdge(4, 7, 0.37));
        G.addEdge(new DirectedEdge(5, 7, 0.28));
        G.addEdge(new DirectedEdge(5, 1, 0.32));
        G.addEdge(new DirectedEdge(4, 0, 0.38));
        G.addEdge(new DirectedEdge(0, 2, 0.26));
        G.addEdge(new DirectedEdge(3, 7, 0.39));
        G.addEdge(new DirectedEdge(1, 3, 0.29));
        G.addEdge(new DirectedEdge(7, 2, 0.34));
        G.addEdge(new DirectedEdge(6, 2, 0.40));
        G.addEdge(new DirectedEdge(3, 6, 0.52));
        G.addEdge(new DirectedEdge(6, 0, 0.58));
        G.addEdge(new DirectedEdge(6, 4, 0.93));

        int s = 5;
        AcyclicLP sp = new AcyclicLP(G, s);
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
