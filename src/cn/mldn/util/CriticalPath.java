package cn.mldn.util;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 9/7/19 6:37 PM
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 9/7/19 6:37 PM
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class CriticalPath {
  static class PairInt {
    int first;
    int second;
    public PairInt(int first, int second) {
      this.first = first;
      this.second = second;
    }

    @Override
    public String toString() {
      return first + "->" + second;
    }
  }

  static int counter = 0;
  static List<PairInt> res;

  public static List<PairInt> getCriticlEdge(
      int numOfServers, int numOfConnections, int[][] connections) {
    res = new LinkedList<>();
    int V = numOfServers + 1;
    LinkedList<Integer>[] graph = buildGraph(V);
    addEdge(connections, graph);
    boolean[] visited = new boolean[V];
    int[] order = new int[V];
    int[] early = new int[V];
    int[] parent = new int[V];

    for (int i = 0; i < V; i++) {
      parent[i] = -1;
    }

    for (int i = 0; i < V; i++) {
      if (!visited[i]) dfs(graph, i, visited, order, early,parent);
    }
    return res;
  }


  private static LinkedList<Integer>[] buildGraph(int V) {
    LinkedList<Integer>[] adj = (LinkedList<Integer>[]) new LinkedList[V];
    for (int i = 0; i < V; i++) {
      adj[i] = new LinkedList<>();
    }
    return adj;
  }

  private static void addEdge(int[][] connections, LinkedList<Integer>[] graph) {
    for (int[] edge: connections) {
      if (edge.length != 2) continue;
      int u = edge[0], v = edge[1];
      graph[u].add(v);
      graph[v].add(u);
    }
  }

  private static void dfs(LinkedList<Integer>[] graph, int u, boolean[] visited,
                   int[] order, int[] early, int[] parent) {
    visited[u] = true;
    order[u] = early[u] = ++ counter;
    for (int v: graph[u]) {
      if (!visited[v]) {
        parent[v] = u;
        dfs(graph, v, visited, order, early, parent);
        early[u] = Math.min(early[u], early[v]);
        if (early[v] > order[u]) {
          res.add(new PairInt(Math.min(u,v), Math.max(u,v)));
        }
      } else if (v != parent[u]) {
        early[u] = Math.min(early[u], order[v]);
      }
    }
  }

  public static void main(String[] args) {
    int[][] connections1 = new int[][]{{0,1},{1,2},{2,0},{1,3},{1,4},{1,6},
        {3,5},{4,5}};
    System.out.println(getCriticlEdge(7, 8, connections1));

    int[][] connections2 = new int[][]{{0,1},{1,2},{2,3}};
    System.out.println(getCriticlEdge(4, 3,connections2));

    int[][] connections3 = new int[][]{{1,0},{0,2},{2,1},{0,3},{3,4}};
    System.out.println(getCriticlEdge(5, 5,connections3));

    int[][] connections4 = new int[][]{{1,2},{1,3},{3,4},{1,4},{4,5}};
    System.out.println(getCriticlEdge(5,5,connections4));

    int[][] connections5 = new int[][]{};
    System.out.println(getCriticlEdge(0,0,connections5));
  }

}
