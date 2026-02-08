package com.company.questions2.MapSet;

import java.util.HashSet;
import java.util.Set;

public class CommonElement {
    public static void main(String[] args) {
        int[] arr1 = {1,1,2,2,2,3,5};
        int[] arr2 = {1,1,1,2,2,4,5};

        // print elements in arr2 that also appear in arr1, order of elements should be acc to they appear in arr2
        // the elements should be only printed once, they should not be printed more than once
        Set<Integer> set = new HashSet<>();

        for (int i : arr1) {
            set.add(i);
        }

        for (int i : arr2) {
            if (set.contains(i)) {
                System.out.print(i + " ");
                set.remove(i);
            }
        }
    }
}
