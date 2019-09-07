package cn.mldn;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

/**
 * @Description: Despription
 * @ProjectName: DemoProject
 * @Package: cn.mldn
 * @Author: Yihang Ding
 * @CreateDate: 7/10/19 4:48 PM
 * @UpdateUser: Yihang Ding
 * @UpdateDate: 7/10/19 4:48 PM
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Intern {

    static class Row {
        int impressions;
        int revenue;
        public Row(int impressions, int revenue) {
            this.impressions = impressions;
            this.revenue = revenue;
        }
    }

    static class Pair {
        long id;
        long impressions;
        long revenue;
        double perf;
        public Pair(long id, long impressions, long revenue) {
            this.id = id;
            this.impressions = impressions;
            this.revenue = revenue;
            this.perf = (double) revenue / impressions;
        }
    }

    public static void main(String[] args) throws IOException {
        String file_path = args[0];
        File file = new File(file_path);
        RandomAccessFile raf = new RandomAccessFile(file, "r");

        String line;
        int index = 0;
        Map<Long, Set<Integer>> map = new HashMap<>();
        List<Row> row_list = new LinkedList<>();
        long all_impressions = 0;
        long all_revenue = 0;
        while ((line = raf.readLine()) != null) {
            int pos1 = line.indexOf(',');
            int pos2 = line.indexOf(',', pos1 + 1);
            int pos3 = line.indexOf(',', pos2 + 1);

            int impression = Integer.parseInt(line.substring(0, pos1));
            int revenue = Integer.parseInt(line.substring(pos1+1, pos2));
            String[] array_str = line.substring(pos3+3, line.length() - 2).split(",");
            for (String id: array_str) {
                long num_id = Long.parseLong(id);
                if (!map.containsKey(num_id)) map.put(num_id, new HashSet<>());
                map.get(num_id).add(index);
            }
            row_list.add(new Row(impression, revenue));
            all_impressions += impression;
            all_revenue += revenue;
            index ++;
        }

        Set<Integer> removed_row = new HashSet<>();
        LinkedList<Pair> ranking = new LinkedList<>();

        int loop = 0;

        while (loop ++ < 50) {
            for (long id: map.keySet()) {
                long tmp_all_impressions = all_impressions;
                long tmp_all_revenue = all_revenue;
                for (int row_index: map.get(id)) {
                    if (removed_row.contains(row_index)) continue;
                    Row row = row_list.get(row_index);
                    tmp_all_impressions -= row.impressions;
                    tmp_all_revenue -= row.revenue;
                }
                ranking.add(new Pair(id, tmp_all_impressions, tmp_all_revenue));
            }

            Collections.sort(ranking, new Comparator<Pair>() {
                @Override
                public int compare(Pair o1, Pair o2) {
                    return o1.perf != o2.perf ? Double.compare(o1.perf, o2.perf): Long.compare(o1.id, o2.id);
                }
            });

            long excluded_au_id = ranking.getFirst().id;
            all_impressions = ranking.getFirst().impressions;
            all_revenue = ranking.getFirst().revenue;
            for (int row_index: map.get(excluded_au_id)) {
                removed_row.add(row_index);
            }
            map.remove(excluded_au_id);
            ranking.clear();
        }
    }
}
