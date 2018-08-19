package cn.mldn.util;

import java.util.Arrays;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/8/19 16:42
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/8/19 16:42
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class TransitiveClosure {

    private boolean[][] hasPath;

    public TransitiveClosure(Digraph G) {
        hasPath = new boolean[G.V()][G.V()];
        for (int i = 0; i < G.V(); i++) {
            boolean[] marked = new boolean[G.V()];
            dfs(G, i, i, marked);
        }
    }

    private void dfs(Digraph G, int v, int row, boolean[] marked) {
        marked[v] = true;
        hasPath[row][v] = true;
        for (int w: G.adj(v)) {
            if (!marked[w]) dfs(G, w, row, marked);
        }
    }

    public boolean reachable(int v, int w) {
        return hasPath[v][w];
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(5);
        G.addEdge(0, 2);
        G.addEdge(0, 4);
        G.addEdge(1, 4);
        G.addEdge(4, 2);
        G.addEdge(4, 3);
        G.addEdge(2, 3);
        G.addEdge(2, 1);

        TransitiveClosure closure = new TransitiveClosure(G);
        for (boolean[] row: closure.hasPath) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println(closure.reachable(3,2));
    }
}
