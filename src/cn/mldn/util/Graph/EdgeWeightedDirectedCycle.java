package cn.mldn.util.Graph;

import cn.mldn.util.Queue;

/**
 * @Description: It is highly similar to DirectedCycle class!
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.Graph
 * @Author: Yihang Ding
 * @CreateDate: 2019/1/9 00:01
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2019/1/9 00:01
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class EdgeWeightedDirectedCycle {

    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private boolean[] onStack;
    private Queue<DirectedEdge> cycle;

    public EdgeWeightedDirectedCycle(EdgeWeightedDigraph G) {
        this.marked = new boolean[G.V()];
        this.edgeTo = new DirectedEdge[G.V()];
        this.onStack = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) dfs(G, v);
        }
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (DirectedEdge e: G.adj(v)) {
            int w = e.to();
            if (this.hasCycle()) return ;
            else if (!marked[w]) {
                edgeTo[w] = e;
                dfs(G, w);
            } else if (onStack[w]) {
                // a cycle is detected

                cycle = new Queue<>();
                DirectedEdge edge = e;
                cycle.enqueue(edge);
                while (true) {
                    edge = edgeTo[edge.from()];
                    cycle.enqueue(edge);

                    if (edge.from() == e.to()) break;
                }

            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() { return this.cycle != null; }

    public Iterable<DirectedEdge> cycle() { return cycle; }

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(6);
        G.addEdge(new DirectedEdge(5, 0, 10));
        G.addEdge(new DirectedEdge(0, 1, 23));
        G.addEdge(new DirectedEdge(1, 2, 11));
        G.addEdge(new DirectedEdge(2, 3, 17));
        G.addEdge(new DirectedEdge(3, 4, 10));
        G.addEdge(new DirectedEdge(4, 1, 10));

        EdgeWeightedDirectedCycle cf = new EdgeWeightedDirectedCycle(G);
        if (cf.hasCycle()) {
            for (DirectedEdge e: cf.cycle) {
                System.out.println(e);
            }
        }
    }
}
