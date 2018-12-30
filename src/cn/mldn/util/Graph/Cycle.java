package cn.mldn.util.Graph;

/**
 * @Description: check whether a graph is cyclic
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.Graph
 * @Author: Yihang Ding
 * @CreateDate: 2018/12/29 20:30
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/12/29 20:30
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Cycle {

    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph G) {
        marked = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++){
            if (!marked[s]) dfs(G, s, s); // to eliminate the effect of parallel edges
        }
    }

    private void dfs(Graph G, int v, int u) {
        marked[v] = true;
        for (int w: G.adj(v)) {
            if (marked[w]) dfs(G, w, v); // record the last vertex visited
            else if (w != u) hasCycle = true;
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }
}
