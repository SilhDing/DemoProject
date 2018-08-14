package cn.mldn.util;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/8/13 21:19
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/8/13 21:19
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Digraph {

    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj= (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        // note the direction: v->w
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Digraph reverse() {
        // reverse the digraph
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w: adj(v)){
                R.addEdge(w, v);
            }
        }
        return R;
    }

    @Override
    public String toString() {
        String s = V + " vertices, " + E + " edges\n";
        for (int v = 0; v < V; v++) {
            s += v +": ";
            for (int w: this.adj(v)) {
                s += w + " ";
            }
            s += "\n";
        }
        return s;
    }
}
