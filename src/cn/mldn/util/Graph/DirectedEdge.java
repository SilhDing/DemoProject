package cn.mldn.util.Graph;

/**
 * @Description: class for sirected edge
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.Graph
 * @Author: Yihang Ding
 * @CreateDate: 2019/1/6 17:24
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2019/1/6 17:24
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class DirectedEdge {

    private final int v;
    private final int w;
    private final double weight;

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return this.weight;
    }

    public int from() {
        return this.v;
    }

    public int to() {
        return this.w;
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.2f", v, w, weight);
    }
}
