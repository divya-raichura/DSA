package com.company.questions2.Heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class KthSmallestElement {
    public static void main(String[] args) {
//        int[] arr = {20,15, 6,1,7,10,4,3};
        int[] arr = {7,10,4,3,20,15};
        int k = 3;

        // 3rd smallest element from array

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());


        // at any point, we have to keep only 3 elements in pq that are smallest
        // when we put 4th element, we pop the element, which removes the largest of all 4
        // so, we remain with 3 smallest after every iteration
        // this loop is more easy
        for (int i : arr) {
            pq.add(i);
            if (pq.size() > k) {
                pq.remove();
            }
        }

//        for (int i : arr) {
//            // in this approach, we don't put 4th element, when 4th element comes
//            // if it is smaller than largest element, then we put it in the pq
//            // so at any point, we don't let the pq go beyond k
//            // kinda complex
//            if (pq.size() >= k) {
//                if (i < pq.peek()) {
//                    pq.poll();
//                    pq.add(i);
//                }
//            } else {
//                pq.add(i);
//            }
//        }

        System.out.println(pq.peek());


    }
}
