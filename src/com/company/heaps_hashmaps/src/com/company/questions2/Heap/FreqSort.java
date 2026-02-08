package com.company.questions2.Heap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class FreqSort {
    public static void main(String[] args) {
        // sort array according to the freq of elements

        int[] arr = {1,1,3,1,2,4,2}; // max freq elements should come first

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        // since we want max freq element first, we use max-heap
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b.getValue(), a.getValue()));
        maxHeap.addAll(map.entrySet());
//        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//            maxHeap.add(entry);
//        }

        int[] ans = new int[arr.length];
        int ind = 0;
        while (!maxHeap.isEmpty()) {
            Map.Entry<Integer, Integer> entry = maxHeap.remove();
            for (int i = 0; i < entry.getValue(); i++) {
                ans[ind++] = entry.getKey();
            }
        }

        System.out.println(Arrays.toString(ans));
    }
}
