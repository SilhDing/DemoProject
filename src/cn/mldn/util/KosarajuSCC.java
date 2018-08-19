package cn.mldn.util;

import java.util.Arrays;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/8/19 11:34
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/8/19 11:34
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class KosarajuSCC {

    private boolean[] marked; // reached vertics
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

    public boolean StronglyCOnnected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
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

        KosarajuSCC scc = new KosarajuSCC(G);
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for (int w: order.reversePost()){
            System.out.print(w+ " ");
        }
        System.out.println();
        System.out.println(scc.count());
        System.out.println(Arrays.toString(scc.id));

    }
}
