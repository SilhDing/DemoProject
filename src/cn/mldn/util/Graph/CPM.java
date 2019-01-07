package cn.mldn.util.Graph;

import cn.mldn.util.Bag;

/**
 * @Description: critical path method
 * @ProjectName: DemoProject
 * @Package: cn.mldn.util.Graph
 * @Author: Yihang Ding
 * @CreateDate: 2019/1/7 17:31
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 2019/1/7 17:31
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class CPM {

    class Job {
        int id;
        double duration;
        Bag<Integer> precedence;

        public Job(int id, double duration) {
            this.id = id;
            this.duration = duration;
            this.precedence = new Bag<>();

        }
    }
}
