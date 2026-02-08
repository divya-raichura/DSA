package com.company.questions2.MapSet;

import java.util.HashMap;

public class CommonElement2 {
    public static void main(String[] args) {
        int[] arr1 = {1,1,2,2,2,3,5};
        int[] arr2 = {1,1,1,2,2,4,5};

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i : arr1) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (int i : arr2) {
            // cannot use just map.get() because if i is not in hashmap, it will give null
            // would need 2 checks: map.contains(i) && map.get(i) > 0
            if (map.getOrDefault(i, 0) > 0) {
                System.out.print(i + " ");
                map.put(i, map.get(i) - 1);
            }
        }
    }
}
