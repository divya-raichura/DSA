package com.company.intro;

import java.util.Collections;
import java.util.PriorityQueue;

public class PQ {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int[] arr = {20, 51, 46, 1, 34, 5};

        for(int i : arr) pq.add(i);

        while (!pq.isEmpty()) {
            System.out.println(pq.remove());
        }

        // to make this default pq a max pq,
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());

    }
}
