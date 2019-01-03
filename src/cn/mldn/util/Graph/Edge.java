package cn.mldn.util.Graph;

import org.jetbrains.annotations.NotNull;

/**
 * @Description: class for weighted edge
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.Graph
 * @Author: Yihang Ding
 * @CreateDate: 2019/1/2 01:17
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2019/1/2 01:17
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Edge implements Comparable<Edge> {

    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int either() {
        return v;
    }

    public int other(int vertex) {
        if (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new RuntimeException("Inconsistent edge");
    }

    @Override
    public int compareTo(@NotNull Edge that) {
        if (this.weight() < that.weight()) return -1;
        else if (this.weight > that.weight()) return 1;
        else return 0;
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.2f", v, w, weight);
    }
}
