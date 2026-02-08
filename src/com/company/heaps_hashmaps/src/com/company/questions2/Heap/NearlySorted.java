package com.company.questions2.Heap;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class NearlySorted {
    public static void main(String[] args) {
        int[] arr = {2,3,1,4,6,7,5,8,9};
        int k = 2;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        ArrayList<Integer> list = new ArrayList<>();

//        for (int i : arr) {
//            if (pq.size() > k + 1) {
//                list.add(pq.remove());
//                pq.add(i);
//            } else {
//                pq.add(i);
//            }
//        }

        for (int i : arr) {
            // if arr = 6,5,3,2,8,10,9 and k = 3
            // then 6,5,3,2 => itne elements ke bichme fight h cause i + k and i - k tak jana h
            // that's why we fill elements till k + 1 then remove the smallest one
            // so, size > k means pq has 4 elements, 6,5,3,2 remove the smallest
            pq.add(i);
            if (pq.size() > k) {
                list.add(pq.remove());
            }
        }

        while (!pq.isEmpty()) {
            list.add(pq.remove());
        }

        System.out.println(list);
    }
}
