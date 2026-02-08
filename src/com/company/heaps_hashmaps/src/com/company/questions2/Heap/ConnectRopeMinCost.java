package com.company.questions2.Heap;

import java.util.PriorityQueue;

public class ConnectRopeMinCost {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i : arr) {
            minHeap.add(i);
        }

        int cost = 0;

        while (minHeap.size() > 1) {
            int i = minHeap.remove();
            int j = minHeap.remove();

            int connect = i + j;
            cost += connect;

            minHeap.add(connect);
        }

        System.out.println(cost);
    }
}
