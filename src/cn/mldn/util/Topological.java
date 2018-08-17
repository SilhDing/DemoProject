package cn.mldn.util;

/**
 * @Description: to get the topological order of a digraph, if passible
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/8/16 20:10
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/8/16 20:10
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Topological {

    private Iterable<Integer> order;

    public Topological(Digraph G) {
        DirectedCycle cyclefinder = new DirectedCycle(G);
        if (!cyclefinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        // if there is a cycle there order is not initialized
        return order != null;
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(5);
        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(0, 4);
        G.addEdge(1, 4);
        G.addEdge(4, 2);
        G.addEdge(4, 3);
        G.addEdge(2, 3);

        System.out.println(G);

        Topological sort = new Topological(G);

        if (sort.isDAG()) {
            System.out.println("It is a DAG and a topological sort is found:");
            for(int v: sort.order()) {
                System.out.print(v + " ");
            }
        }
    }
}
