package cn.mldn.util.Graph;

import cn.mldn.util.Stack;

/**
 * @Description: find directed cycle in a directed graph
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.Graph
 * @Author: Yihang Ding
 * @CreateDate: 2018/12/30 22:39
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/12/30 22:39
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class DirectedCycle {

    private boolean[] marked;
    private int[] edgeTo;
    private boolean[] onStack;
    private Stack<Integer> cycle;

    public DirectedCycle(Digraph G) {
        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];
        this.onStack = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) dfs(G, v);
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        onStack[v] = true;
        for(int w: G.adj(v)) {
            if (this.hasCycle()) return ;
            else if (!marked[w]) { // is unmarked then not on stack for sure
                edgeTo[w] = v;
                dfs(G, w);
            } else if (onStack[w]) {
                // a cycle is detected then store it in a stack
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false; // restore stack status
    }

    public boolean hasCycle() {
        return this.cycle != null;
    }

    public Iterable<Integer> cycle() {
        return this.cycle;
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(6);
        G.addEdge(5, 0);
        G.addEdge(0, 1);
        G.addEdge(1, 2);
        G.addEdge(2, 3);
        G.addEdge(3, 4);
        G.addEdge(4, 1);

        DirectedCycle cf = new DirectedCycle(G);
        if (cf.hasCycle()) {
            for (int v: cf.cycle) {
                System.out.print(v + " ");
            }
        }
    }
}
