package cn.mldn.util.Graph;

/**
 * @Description: Transitive closure
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.Graph
 * @Author: Yihang Ding
 * @CreateDate: 2019/1/2 00:58
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2019/1/2 00:58
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class TransitiveClosure {

    private DirectedDFS[] all;

    TransitiveClosure(Digraph G) {
        all = new DirectedDFS[G.V()];
        for (int v = 0; v < G.V(); v++) {
            all[v] = new DirectedDFS(G, v);
        }
    }

    boolean reachable(int v, int w) {
        // is w reachable from v?
        return all[v].marked(w);
    }
}
