package cn.mldn.util;

import java.util.*;
import java.util.Queue;

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
        // does not contains this movie, then ignore
        if (!movie2Genre.containsKey(movie)) continue;
        String genre = movie2Genre.get(movie);
        map.put(genre, map.getOrDefault(genre, 0) + 1);
      }
      user2Genre.put(user, getGenre(map));
    }

    return user2Genre;
  }

  private List<String> getGenre(Map<String, Integer> map){
    List<String> list = new LinkedList<>();
    if (map.size() == 0) return list;
    PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        return map.get(o2) - map.get(o1);
      }
    });
    for (String key: map.keySet()) pq.offer(key);
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

  // copy random linked list
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

  // reorder the logs
  public String[] reorderLog(String[] logs) {
    Comparator<String> comparator = new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        int index1 = o1.indexOf(" ");
        int index2 = o2.indexOf(" ");

        char c1 = o1.charAt(index1+1);
        char c2 = o2.charAt(index2+1);

        if (Character.isDigit(c1)) {
          // the first one is a digit log
          if (Character.isDigit(c2)) {
            // the second one is also a digit log
            return 0;
          } else {
            return 1;
          }
        } else {
          // the first one is a letter log
          if (Character.isDigit(c2)) {
            return -1;
          } else {
            int compare =  o1.substring(index1+1).compareTo(o2.substring(index2+1));
            if (compare == 0) {
              return o1.substring(0, index1).compareTo(o2.substring(0, index2));
            } else {
              return compare;
            }
          }
        }
      }
    };
    Arrays.sort(logs, comparator);
    return logs;
  }

  // k frequent word
  public List<String> topKFrequent(String[] words, int k) {
    // pq?
    Map<String, Integer> map = new HashMap<>();
    for (String word: words) {
      map.put(word, map.getOrDefault(word, 0) + 1);
    }

    PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>(){
      public int compare(String str1, String str2) {
        int compare = map.get(str2) - map.get(str1);
        if (compare != 0) return compare;
        else return str1.compareTo(str2);
      }
    });
    for (String word: map.keySet()) pq.offer(word);

    List<String> list = new LinkedList<>();
    while (pq.size() > 0 && k > 0) {
      list.add(pq.poll());
      k --;
    }
    return list;
  }


  // construct tree
  int preIndex;
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    preIndex = 0;
    return helper(preorder, inorder, 0, inorder.length - 1);
  }

  private TreeNode helper(int[] preorder, int[] inorder, int left, int right) {
    if (left > right) return null;
    int rootValue = preorder[preIndex++];
    TreeNode curRoot = new TreeNode(rootValue);
    int rootIndex  = -1;
    for (int i = left; i <= right; i++) {
      if (inorder[i] == rootValue) {
        rootIndex = i;
        break;
      }
    }
    curRoot.left = helper(preorder, inorder, left, rootIndex - 1);
    curRoot.right = helper(preorder, inorder, rootIndex+1, right);
    return curRoot;
  }

  class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {val = x;}
  }
  // merge lisr
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null && l2 == null) return null;
    else if (l1 == null) return l2;
    else if (l2 == null) return l1;
    else {
      if (l1.val < l2.val) {
        l1.next = mergeTwoLists(l1.next, l2);
        return l1;
      } else {
        l2.next = mergeTwoLists(l1, l2.next);
        return l2;
      }
    }
  }

  // two sum closest
  private List<int[]> getPairs(List<int[]> a, List<int[]> b, int target) {
    Collections.sort(a, (i,j) -> i[1] - j[1]);
    Collections.sort(b, (i,j) -> i[1] - j[1]);
    List<int[]> result = new ArrayList<>();
    int max = Integer.MIN_VALUE;
    int m = a.size();
    int n = b.size();
    int i =0;
    int j =n-1;
    while(i<m && j >= 0) {
      int sum = a.get(i)[1] + b.get(j)[1];
      if(sum > target) {
        --j;
      } else {
        if(max <= sum) {
          if(max < sum) {
            max = sum;
            result.clear();
          }
          result.add(new int[]{a.get(i)[0], b.get(j)[0]});
          int index = j-1;
          while(index >=0 && b.get(index)[1] == b.get(index+1)[1]) {
            result.add(new int[]{a.get(i)[0], b.get(index--)[0]});
          }
        }
        ++i;
      }
    }
    return result;
  }

  // merger ropes
  public static int mergeRopes(int[] ropes) {
    PriorityQueue<Integer> pQueue = new PriorityQueue<>();
    for(int rope: ropes) pQueue.add(rope);
    int cost = 0;
    while(pQueue.size() !=1) {
      //System.out.println(Arrays.toString(pQueue.toArray()));
      int temp = pQueue.poll() + pQueue.poll();
      cost += temp;
      pQueue.add(temp);
    }
    return cost;
  }

  //  Treasure Island
  public int treasureIsland(char[][] island) {
    if (island == null || island.length == 0 || island[0].length == 0) return 0;

    int res = 0, row = island.length, col = island[0].length;
    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visited = new boolean[row][col];
    visited[0][0] = true;
    queue.offer(new int[]{0, 0});
    int[][] dirs = {{0,1}, {0, -1}, {-1, 0}, {1,0}};
    while (queue.size() > 0) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int[] cur = queue.poll();
        if (island[cur[0]][cur[1]] == 'X') return res;
        for (int[] dir: dirs) {
          int x = cur[0] + dir[0], y = cur[1] + dir[1];
          if (x < 0 || x >= row || y < 0 || y >= col || visited[x][y] ||
              island[x][y] == 'D') continue;
          queue.add(new int[]{x, y});
          visited[x][y] = true;
        }
      }
      res ++;
    }
    return -1;
  }

  public static void main(String[] args) {
    AmazonSolution s = new AmazonSolution();
    Map<String, List<String>> user2Movie = new HashMap<>();
    List<String> list1 = new LinkedList<>();
    list1.add("a");
    list1.add("b");
    list1.add("e");
    list1.add("o");
    user2Movie.put("Tom", list1);

    List<String> list2 = new LinkedList<>();
    list2.add("a");
    list2.add("c");
    list2.add("d");
    list2.add("z");
    user2Movie.put("Jack", list2);

    Map<String, List<String>> genre2Movie = new HashMap<>();
    genre2Movie.put("happy", new LinkedList<>(Arrays.asList("a", "d", "l")));
    genre2Movie.put("sad", new LinkedList<>(Arrays.asList("b", "c")));

    System.out.println(s.favouriteMovie(user2Movie, genre2Movie));
    // map1 is null
    System.out.println(s.favouriteMovie(new HashMap<>(), genre2Movie));
    // map2 is null
    System.out.println(s.favouriteMovie(user2Movie, new HashMap<>()));
    // map1 has no value

    char[][] island = new char[][]{{'O', 'O', 'O', 'O'},
        {'D', 'O', 'D', 'O'},{'O', 'O', 'O', 'O'},{'X', 'D', 'D', 'O'}};
    System.out.println(s.treasureIsland(island));

  }
}
