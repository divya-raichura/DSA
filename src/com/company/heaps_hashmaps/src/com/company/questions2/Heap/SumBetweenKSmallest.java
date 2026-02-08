package com.company.questions2.Heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class SumBetweenKSmallest {
    public static void main(String[] args) {
        int[] arr = {1,3,12,5,15,11};
        int k1 = 3;
        int k2 = 6;

        // find sum of elements between k1th and k2th smallest numbers
        int firstSmallest = kthSmallest(arr, k1);
        int secondSmallest = kthSmallest(arr, k2);

        int sum = 0;

        for (int i : arr) {
            if (i > firstSmallest && i < secondSmallest) {
                sum += i;
            }
        }

        System.out.println(sum);
    }

    static int kthSmallest(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i: arr) {
            pq.add(i);
            if (pq.size() > k) {
                pq.remove();
            }
        }

        return pq.remove();
    }
}
