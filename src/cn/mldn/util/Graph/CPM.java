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

    AcyclicLP lp;
    int s; // start vertex
    int t; // end vertex

    public CPM(Job[] jobs) {
        int N = jobs.length; // the number of all jobs
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(2*N + 2);

        s = 2*N;
        t = 2*N + 1;
        for (int i = 0; i < N; i++) {
            // visit every job

            double duration = jobs[i].duration;

            // edge from start vertex to end vertex with weight of duration
            G.addEdge(new DirectedEdge(i, i+N, duration));

            // edge from s to every start vertex
            G.addEdge(new DirectedEdge(s, i, 0.0));

            // edge from every end vertex to t
            G.addEdge(new DirectedEdge(i+N, t, 0.0));

            // add edge determined by precedence constraints
            for (int j: jobs[i].precedence) {
                G.addEdge(new DirectedEdge(i+N, j, 0.0));
            }

        }
        lp = new AcyclicLP(G, s); // find lp from s to t
    }

    public double startTime(int jobID) {
        return lp.distTo(jobID);
    }

    public double longestTime() {
        return lp.distTo(t);
    }

    public static void main(String[] args) {
        Job[] jobs = new Job[10];
        jobs[0] = new Job(0, 41, 1, 7, 9);
        jobs[1] = new Job(1, 51, 2);
        jobs[2] = new Job(2, 50);
        jobs[3] = new Job(3, 36);
        jobs[4] = new Job(4, 38);
        jobs[5] = new Job(5, 45);
        jobs[6] = new Job(6, 21, 3, 8);
        jobs[7] = new Job(7, 32, 3, 8);
        jobs[8] = new Job(8, 32, 2);
        jobs[9] = new Job(9, 29, 4, 6);

        CPM cpm = new CPM(jobs);

        System.out.println("Start time:");
        for (int i = 0; i < 10; i++) {
            System.out.println(i + ": " + cpm.startTime(i));
        }
        System.out.println("Finish time: " + cpm.longestTime());
    }
}

class Job {
    int id;
    double duration;
    Bag<Integer> precedence;

    public Job(int id, double duration, int ... data) {
        this.id = id;
        this.duration = duration;
        this.precedence = new Bag<>();

        for (int i = 0; i < data.length; i++) {
            this.precedence.add(data[i]);
        }
    }
}
