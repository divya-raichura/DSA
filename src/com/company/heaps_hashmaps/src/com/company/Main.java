package com.company;

import com.company.implementations_heaps.Heap;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Integer[] arr1 = {10, 12, 25, 5};
//
//        Heap<Integer> heap = new Heap<Integer>(arr1);
//        heap.display();
//        List<Integer> list = heap.heapsort();
//        System.out.println(list);
//        heap.add(100);
//        heap.add(2);
//        heap.display();
//        boolean ans = heap.isMinHeap(0);
//        System.out.println(ans);

        int n = 16;
        System.out.println(Integer.highestOneBit(n));
        System.out.println(Integer.highestOneBit(n) << 1);
        System.out.println(16 >> 1);
    }
}
