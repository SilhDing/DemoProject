package cn.mldn.util;

import java.util.*;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util
 * @Author: Yihang Ding
 * @CreateDate: 9/6/19 7:05 PM
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 9/6/19 7:05 PM
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class AmazonSolution {
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
  }

  // search in matrix
  public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix.length == 0 || matrix[0].length == 0) return false;
    int row = matrix.length, col = matrix[0].length;
    int x = row - 1, y = 0;
    while (x >= 0 && y < col) {
      if (matrix[x][y] == target) return true;
      else if (matrix[x][y] < target) {
        y ++;
      } else {
        x --;
      }
    }
    return false;
  }

  // is subtree
  public boolean isSubtree(TreeNode s, TreeNode t) {
    if (isSameTree(s, t)) return true;
    if (s.left != null) {
      if (isSubtree(s.left, t)) return true;
    }
    if (s.right != null) {
      if (isSubtree(s.right, t)) return true;
    }
    return false;
  }

  public boolean isSameTree(TreeNode s, TreeNode t) {
    if (s == null && t == null) return true;
    else if (s == null || t == null) return false;
    else {
      if (s.val != t.val) return false;
      return isSameTree(s.left, t.left) == false?false: isSameTree(s.right, t.right);
    }
  }

  // users' favorite movie
  public Map<String, List<String>> favouriteMovie(
      Map<String, List<String>> user2Movie, Map<String, List<String>> genreToMovie) {
    Map<String, String> movie2Genre = new HashMap<>();
    for (String genre: genreToMovie.keySet()) {
      for (String movie: genreToMovie.get(genre)) {
        movie2Genre.put(movie, genre);
      }
    }

    Map<String, List<String>> user2Genre = new HashMap<>();
    for (String user: user2Movie.keySet()) {
      Map<String, Integer> map = new HashMap<>();
      for (String movie: user2Movie.get(user)) {
        String genre = movie2Genre.get(movie);
        map.put(genre, map.getOrDefault(genre, 0) + 1);
      }
      user2Genre.put(user, getGenre(map));
    }

    return user2Genre;
  }

  private List<String> getGenre(Map<String, Integer> map){
    List<String> list = new LinkedList<>();
    // TODO
    PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        return map.get(o2) - map.get(o1);
      }
    });
    String maxOne = pq.poll();
    int maxNum = map.get(maxOne);
    list.add(maxOne);
    while (pq.size() > 0) {
      String cur = pq.poll();
      int curNum = map.get(cur);
      if (curNum == maxNum) {
        list.add(cur);
      } else {
        break;
      }
    }
    return list;
  }


  class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
      val = _val;
      next = _next;
      random = _random;
    }
  }

  public Node copyRandomList(Node head) {
    Node dummy = new Node();
    Map<Node, Node> map = new HashMap<>();

    // at first, construct the list without random pointer
    Node node1 = head;
    Node node2 = dummy;
    while (node1 != null) {
      node2.next = new Node(node1.val, null, null);
      node2 = node2.next;
      map.put(node1, node2);
      node1 = node1.next;

    }

    // now construct the randome pointer
    node1 = head;
    node2 = dummy.next;
    while (node1 != null) {
      if (node1.random != null) {
        node2.random = map.get(node1.random);
      }
      node1 = node1.next;
      node2 = node2.next;
    }
    return dummy.next;
  }
}
