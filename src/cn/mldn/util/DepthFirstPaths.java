package cn.mldn.util;

import java.util.Arrays;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/8/3 11:46
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/8/3 11:46
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
            if (!marked[w]){
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v]; // true if v is connected
    }

    public Iterable<Integer> pathTo(int v) {
        if (!marked[v]) return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0, 1);
        g.addEdge(0, 0);
        g.addEdge(2, 1);
        g.addEdge(0, 4);
        g.addEdge(3, 1);
        g.addEdge(2, 3);
        System.out.println(g);
        DepthFirstPaths dfs = new DepthFirstPaths(g, 1);
        System.out.println(Arrays.toString(dfs.edgeTo));
        System.out.println(dfs.hasPathTo(0));
        System.out.println(dfs.hasPathTo(2));
        System.out.println(dfs.hasPathTo(3));

        for (int v = 0; v < g.V(); v++) {
            if (dfs.hasPathTo(v))
                for (int x: dfs.pathTo(v))
                    if (x == 1) System.out.print(x);
                    else System.out.print("-" + x);
            System.out.println();
        }
    }
}
