package cn.mldn.util.Graph;

/**
 * @Description: topological order of a directed graph
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.Graph
 * @Author: Yihang Ding
 * @CreateDate: 2018/12/30 23:50
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/12/30 23:50
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Topological {

    private Iterable<Integer> order;

    public Topological(Digraph G) {
        DirectedCycle cycles = new DirectedCycle(G);
        if (!cycles.hasCycle()) {
            // then a topological order must exist
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }
}
