package com.company.questions2.Heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class KClosest {
    public static void main(String[] args) {
        int[] arr = {5,6,7,8,9};
        int k = 3;
        int x = 7;

        // find k numbers that are closest to x

        PriorityQueue<Pair> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i : arr) {
            pq.add(new Pair(Math.abs(i - x), i));
            if (pq.size() > k) {
                pq.remove();
            }
        }

        while (!pq.isEmpty()) {
            System.out.print(pq.remove().val + " ");
        }
    }

    static class Pair implements Comparable<Pair> {
        int diff;
        int val;

        public Pair(int diff, int val) {
            this.diff = diff;
            this.val = val;
        }

        @Override
        public int compareTo(Pair o) {
            return this.diff - o.diff;
        }
    }
}
