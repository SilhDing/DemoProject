package cn.mldn.util.Graph;

import cn.mldn.util.Stack;

/**
 * @Description: DFS to find a path (not the shorted one)
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.Graph
 * @Author: Yihang Ding
 * @CreateDate: 2018/12/29 16:24
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/12/29 16:24
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class DepthFirstPaths {

    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DepthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w: G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        // if this vertex is marked, then there must be path to this vextex
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;

        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    public static void main(String[] args) {
        Graph G = new Graph(5);
        G.addEdge(0, 1);
        G.addEdge(2, 1);
        G.addEdge(3, 4);
        G.addEdge(4, 2);
        int s = 0;

        // this is to demonstrate how to use this method
        DepthFirstPaths search = new DepthFirstPaths(G, s);
        for (int v = 0; v < G.V(); v++) {
            System.out.print(s + " to " + v + ": ");
            if (search.hasPathTo(v)) {
                for (int x: search.pathTo(v)) {
                    if (x == s) System.out.print(x);
                    else System.out.print("-" + x);
                }
            }
            System.out.println();
        }
    }
}
