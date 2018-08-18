package cn.mldn.util;

/**
 * @Description: reachability in digraphs
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/8/13 21:58
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/8/13 21:58
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class DirectedDFS {

    private boolean[] marked;
    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }
    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        // It is not hard here
        // if we want to find whether it is reachable from s to v, we may dfs(G, s) and
        // then see if marked[v] == true;
        marked = new boolean[G.V()];
        for (int s: sources) {
            if (!marked[s]) dfs(G, s);
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w: G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public static void main(String[] args) {
        // we may just check the marked[] to see if the vertice is reachable
    }
}
