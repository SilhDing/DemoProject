package cn.mldn.util;

/**
 * @Description: check if the graph is acyclic
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/8/11 10:11
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/8/11 10:11
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Cycle {
   private boolean[] marked;
   private boolean hasCycle;

   public Cycle(Graph G) {
       marked = new boolean[G.V()];
       for (int s = 0; s < G.V(); s++) {
           if (!marked[s]) dfs(G, s, s);
       }
   }

   private void dfs(Graph G, int v, int u) {
       marked[v] = true;
       for (int w: G.adj(v)) {
           if(!marked[w]) dfs(G, w, v);
           // to save the paret node of the current node
           // this is to avoid parallel links
           // once we find marked[w] is true in dfs then the graph is absolutely cyclic
           else if (w != u) hasCycle = true;
       }
   }

   public boolean hasCycle() {
       return hasCycle;
   }

    public static void main(String[] args) {
        Graph g = new Graph(6);
        g.addEdge(0,1);
        g.addEdge(2,1);
        g.addEdge(2,3);
        g.addEdge(3,4);
        g.addEdge(3,5);
        g.addEdge(1,4);
        Cycle cc = new Cycle(g);
        System.out.println(cc.hasCycle());
    }
}
