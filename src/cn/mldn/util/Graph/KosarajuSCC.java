package cn.mldn.util.Graph;

/**
 * @Description: Kosalaju's algorithm to find strongly connected components in a directed graph
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.Graph
 * @Author: Yihang Ding
 * @CreateDate: 2019/1/1 17:34
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2019/1/1 17:34
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class KosarajuSCC {

    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarajuSCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for (int s: order.reversePost()) {
            if (!marked[s]) {
                dfs(G, s);
                count ++;
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w: G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }
}