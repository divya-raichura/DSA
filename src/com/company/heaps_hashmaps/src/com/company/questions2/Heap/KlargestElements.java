package com.company.questions2.Heap;

import java.util.PriorityQueue;

public class KlargestElements {
    public static void main(String[] args) {
        // print 3 largest
        int[] arr = {2,10,5,17,7,18,6,4};
        int n = 3;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

//        for (int i : arr) {
//            if (pq.size() >= n) {
//                if (pq.peek() < i) {
//                    pq.poll();
//                    pq.add(i);
//                }
//            } else {
//                pq.add(i);
//            }
//        }

        for (int i : arr) {
            pq.add(i);
            if (pq.size() > n) {
                pq.remove();
            }
        }

        System.out.println(pq);
    }
}
