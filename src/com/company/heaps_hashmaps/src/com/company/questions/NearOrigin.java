package com.company.questions;

import java.util.Collections;
import java.util.PriorityQueue;

public class NearOrigin {

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int[] arr : points) {
            int i = arr[0];
            int j = arr[1];
            pq.add(new Pair(i, j, distance(i, j)));
            if (pq.size() > k) {
                pq.remove();
            }
        }
        int[][] ans = new int[pq.size()][2];
        for (int i = 0; i < ans.length; i++) {
            Pair p = pq.remove();
            ans[i][0] = p.i;
            ans[i][1] = p.j;
        }
        return ans;
    }

    static class Pair implements Comparable<Pair> {
        int i;
        int j;
        int dis;

        public Pair(int i, int j, int dis) {
            this.i = i;
            this.j = j;
            this.dis = dis;
        }

        @Override
        public int compareTo(Pair o) {
            return this.dis - o.dis;
        }
    }

    public int distance(int i, int j) {
        return  (i * i + j * j);
    }

}
