package cn.mldn.util;

import org.jetbrains.annotations.NotNull;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/8/23 15:43
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/8/23 15:43
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Edge implements Comparable<Edge> {

    // v and w are two vertices that are connected by this edge
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
        // return a vertex
        return v;
    }

    public int other(int vertex) {
        // return another vertex
        if (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new RuntimeException("Inconsistent edge");
    }

    @Override
    public int compareTo(@NotNull Edge that) {
        if (this.weight() < that.weight()) return -1;
        else if (this.weight() > that.weight()) return 1;
        else return 0;
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.2f", v, w, weight);
    }
}
