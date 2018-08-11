package cn.mldn.util;

import java.util.Arrays;

/**
 * @Description: breadth-first search to find paths in a graph
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 2018/8/10 11:31
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/8/10 11:31
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    public void bfs(Graph G, int s) {
        Queue<Integer> queue = new Queue<>();
        marked[s] = true;
        queue.enqueue(s);

        while (queue.size() != 0) {
            int v = queue.dequeue();
            for (int w: G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;

        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    public String toString() {
        return "what you print is a graph";
    }

    public static void main(String[] args) {
        Graph g = new Graph(6);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 5);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 4);
        g.addEdge(3, 5);
        System.out.println(g);
        BreadthFirstPaths bfs = new BreadthFirstPaths(g, 0);
        System.out.println(Arrays.toString(bfs.edgeTo));
        System.out.println(bfs.hasPathTo(3));
        System.out.println(bfs.hasPathTo(0));
        System.out.println(bfs.hasPathTo(2));

        for (int v = 0; v < g.V(); v++) {
            if (bfs.hasPathTo(v))
                for (int x: bfs.pathTo(v))
                    if (x == 0) System.out.print(x);
                    else System.out.print("-" + x);
            System.out.println();
        }
    }


}