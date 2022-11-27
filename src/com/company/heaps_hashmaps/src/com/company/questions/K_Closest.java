package com.company.questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class K_Closest {
    public static void main(String[] args) {
        // question
        int[] arr = {1, 2, 3, 4, 5}; // 2,1,0,1,2
        int k = 4;
        int x = 3;

        // answer
        // efficient ans is binary search, this works but not efficient as this is NlogN due to sorting
        // but binary search is just logN
        List<Integer> list = new ArrayList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int j : arr) {
            Pair p = new Pair(Math.abs(j - x), j);
            pq.add(p);
            if (pq.size() > k) {
                pq.remove();
            }
        }
        while (pq.size() > 0) {
            list.add(pq.remove().val);
        }
        Collections.sort(list);
//        return list;
        System.out.println(list);
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
//            {1, 2, 3, 4, 5}; // 2,1,0,1,2
            // if we don't add if condition, ans is 2,3,4,5 but main ans should be in asc order ie 1,2,3,4
            // so how to decide which to include 1 or 5 as we want only 4 closest and both are equally close
            // so we decide according to their value
            if (this.diff - o.diff == 0) return o.val - this.val; // most imp part of q
            return o.diff - this.diff;
        }
    }
}
