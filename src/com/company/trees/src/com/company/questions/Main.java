package com.company.questions;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
//        List<Integer> reversedList = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//            list.add(i);
//            reversedList.add(0, i);
//        }
//
//        System.out.println(list);
//        System.out.println(reversedList);
//        Stack<Integer> stack = new Stack<>();
//        stack.push(null);
//
//        System.out.println(stack);

//        HashMap<Integer, Integer> map = new HashMap<>();
//        map.put(1,2);
//        fn();
//        char c = 4 + '0';
//        char z = '0';
//        char a = 'a' + 0;
//        System.out.println(a);
//        System.out.println('a' + 0);
//        System.out.println(z);
//        System.out.println(c);
//        System.out.println((char)(4 + '0'));
//
//        List<String> list = new ArrayList<>(Arrays.asList("divya", "raichura", "is", "a", "boy"));
//        String s = "divya";

//        System.out.println(list);

//        int ch = 'a';
//        System.out.println(ch);
//        char a = (char) ch;
//        System.out.println(a);

        int[] arr = {1,2,3,6,2,3,4,7,8};

        HashMap<Integer, Integer> map = new HashMap<>();

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i : arr) {
            if (!map.containsKey(i)) {
                pq.add(i);
                map.put(i, 1);
            } else {
                int ov = map.get(i);
                int nv = ov + 1;
                map.put(i, nv);
            }
        }

        System.out.println(map);
        while (!pq.isEmpty()) {
            System.out.println(pq.remove());
        }
    }

    static void fn() {
//        StringBuilder str = new StringBuilder();
//        str.append("divya");
//        str.append(1);
//        str.append("r").append("a");
//        String a = "A";
//        str.append(a);
//        String s = 20 + " ";
//        System.out.println("s: " + s);
//        str.delete(0,str.length());
//        return str.toString();
        String data = -25 + "";
        System.out.println(Integer.parseInt(data));
        System.out.println(data.charAt(0));
    }
}
