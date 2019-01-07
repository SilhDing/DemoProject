package cn.mldn.util.Graph;

/**
 * @Description: all-pairs shortest paths
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.Graph
 * @Author: Yihang Ding
 * @CreateDate: 2019/1/6 23:31
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2019/1/6 23:31
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class DijikstraAllPairsSP {

    private DijkstraSP[] all;

    public DijikstraAllPairsSP(EdgeWeightedDigraph G) {
        all = new DijkstraSP[G.V()];
        for (int v = 0; v < G.V(); v++) {
            all[v] = new DijkstraSP(G, v);
        }
    }

    public Iterable<DirectedEdge> path(int s, int t) {
        return all[s].pathTo(t);
    }

    public double distTo(int s, int t) {
        return all[s].distTo(t);
    }

}
