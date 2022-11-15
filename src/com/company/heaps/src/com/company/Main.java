package com.company;

import com.company.implementations.Heap;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Integer[] arr1 = {5, 3, 16, 78, 221};
//        int[] arr2 = arr1;

//        arr1 = null;
//        System.out.println(Arrays.toString(arr1));
//        System.out.println(Arrays.toString(arr2));


        Heap<Integer> heap = new Heap<Integer>(arr1);
        List<Integer> list = heap.heapsort();
        System.out.println(list);
        heap.add(100);
        heap.add(2);
        heap.display();
        boolean ans = heap.isMinHeap(0);
        System.out.println(ans);
    }
}
