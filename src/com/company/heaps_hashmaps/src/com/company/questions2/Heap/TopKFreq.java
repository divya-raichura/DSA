package com.company.questions2.Heap;

import java.util.*;

public class TopKFreq {
    public static void main(String[] args) {
        int[] arr = {1,1,1,3,2,2,4};
        int k = 2;

        // k elements whose frequency is highest

        mapMethod(arr, k);
        pairMethod(arr, k);
    }

    static void mapMethod(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a.getValue(), b.getValue()));
        List<Integer> res = new ArrayList<>();

        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for(Map.Entry<Integer,Integer> entry: map.entrySet()){
            minHeap.add(entry);
            if (minHeap.size() > k) minHeap.poll();
        }

        while(!minHeap.isEmpty()){
            Map.Entry<Integer, Integer> entry = minHeap.poll();
            res.add(entry.getKey());
        }

        System.out.println(res);
    }

    static void pairMethod(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int i : map.keySet()) {
            Pair p = new Pair(i, map.get(i));
            pq.add(p);
            if (pq.size() > k) {
                pq.remove();
            }
        }
        int[] ans = new int[pq.size()];
        int n = pq.size();
        for (int i = 0; i < n; i++) {
            ans[i] = pq.remove().k;
        }

        System.out.println(Arrays.toString(ans));
    }

    static class Pair implements Comparable<Pair> {

        int k;
        int v;

        public Pair(int k, int v) {
            this.k = k;
            this.v = v;
        }

        @Override
        public int compareTo(Pair o) {
            return this.v - o.v;
        }
    }
}
