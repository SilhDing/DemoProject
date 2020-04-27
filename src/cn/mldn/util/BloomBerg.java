package cn.mldn.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BloomBerg {

}

class Runner {

  int id;
  int lastMilestone;
  Runner prev;
  Runner next;
  public Runner(int id) {
    this.id = id;
    this.lastMilestone = -1;
    this.prev = null;
    this.next = null;
  }

}

class RunnerTracker {
  private Map<Integer, Runner> runnersMap;
  private Map<Integer, Runner> lastRunner;

  Runner head, tail;

  public RunnerTracker() {
    runnersMap = new HashMap<>();
    lastRunner = new HashMap<>();
    head = new Runner(-1);
    tail = new Runner(-1);
    head.next = tail;
    tail.prev = head;
  }

  public void passMilestone(int runnerID) {
    if (!runnersMap.containsKey(runnerID)) {
      Runner runner = new Runner(runnerID);
      addAfter(tail.next, runner);
      runnersMap.put(runnerID, runner);
    }
    Runner runner = runnersMap.get(runnerID);
    int curMile = runner.lastMilestone + 1;
    runner.lastMilestone = curMile;
    delete(runner);
    if (!lastRunner.containsKey(curMile)) {
      addAfter(head, runner);
    } else {
      addAfter(lastRunner.get(curMile), runner);
    }

    lastRunner.put(curMile, runner);

  }

  public List<Integer> getRank() {
    List<Integer> list = new LinkedList<>();
    Runner runner = head.next;
    while (runner != tail) {
      list.add(runner.id);
    }
    return list;
  }

  private void addAfter(Runner runner, Runner toAdd) {
    Runner tmp = runner.next;
    runner.next = toAdd;
    toAdd.prev = runner;
    toAdd.next = tmp;
    tmp.prev = toAdd;
  }

  private void delete(Runner runner) {
    Runner prev = runner.prev;
    Runner next = runner.next;
    prev.next = next;
    next.prev = prev;
  }
}