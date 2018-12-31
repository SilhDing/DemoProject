package cn.mldn.util.Graph;

import cn.mldn.util.Queue;
import cn.mldn.util.Stack;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.Graph
 * @Author: Yihang Ding
 * @CreateDate: 2018/12/30 23:40
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2018/12/30 23:40
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class DepthFirstOrder {

    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph G) {
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();

        marked = new boolean[G.V()];

        for (int v = 0; v < G.V(); v ++) {
            if (!marked[v]) dfs(G, v);
        }
    }

    private void dfs(Digraph G, int v) {
        pre.enqueue(v);

        marked[v] = true;
        for (int w: G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }

        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre () { return pre; }

    public  Iterable<Integer> post() { return post; }

    public Iterable<Integer> reversePost() { return reversePost; }
}
