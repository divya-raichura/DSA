package com.company.questions2.MapSet;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Frequency {
    public static void main(String[] args) {
        HashMap<Character, Integer> map = new HashMap<>();

        Scanner in = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String str = in.nextLine();
        System.out.println();

        for (char c : str.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            System.out.println(map);
        }

        Map.Entry<Character, Integer> maxEntry = null;

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }

        System.out.println("Character with highest frequency: " + maxEntry.getKey());
        System.out.println("Frequency: " + maxEntry.getValue());
    }
}
