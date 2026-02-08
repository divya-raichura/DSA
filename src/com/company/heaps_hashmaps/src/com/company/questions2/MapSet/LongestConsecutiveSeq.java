package com.company.questions2.MapSet;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSeq {
    // in o(n) print the longest consecutive sequence in the arr
    public static void main(String[] args) {
        int[] arr = {10,5,9,1,11,8,6,15,3,12,2};

        Set<Integer> set = new HashSet<>();
        for (int i : arr) {
            set.add(i);
        }

        int longestStreak = 0;
        int startNum = 0;

        for (int num : set) {
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                if (currentStreak > longestStreak) {
                    longestStreak = currentStreak;
                    startNum = num;
                }
            }
        }

        System.out.print("The longest consecutive sequence is: ");
        for (int i = 0; i < longestStreak; i++) {
            System.out.print(startNum + i + " ");
        }
        System.out.println("\nLength: " + longestStreak);
    }
}
