package com.company.questions2.Heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class KSortedList {
    public static void main(String[] args) {
        List<Integer> l1 = Arrays.asList(10,20,30,40,50);
        List<Integer> l2 = Arrays.asList(5,7,9,11,19,55,57);
        List<Integer> l3 = Arrays.asList(1,2,3);
        List<Integer> l4 = Arrays.asList(32,39);


        List<List<Integer>> lists = new ArrayList<>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        lists.add(l4);

        System.out.println(kSortedLists(lists));
    }

    static ArrayList<Integer> kSortedLists(List<List<Integer>> lists) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        ArrayList<Integer> ans = new ArrayList<>();

        // pushing first element of each list
        for (int i = 0; i < lists.size(); i++) {
            Pair p = new Pair(0,i,lists.get(i).get(0));
            pq.add(p);
        }

        // at each iteration, there is one pair of each list inside pair
        // list which has current element smallest, it's pair is popped
        // and put inside ans, and next element of that list is put in pq as a Pair
        // if next element exists ie, all elements of list are not iterated
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            ans.add(p.val);
            p.di += 1;

            if (p.di < lists.get(p.li).size()) {
                p.val = lists.get(p.li).get(p.di);
                pq.add(p);
            }
        }

        return ans;
    }


    static class Pair implements Comparable<Pair> {
        int di;
        int li;
        int val;

        public Pair(int di, int li, int val) {
            this.di = di;
            this.li = li;
            this.val = val;
        }


        @Override
        public int compareTo(Pair o) {
            return this.val - o.val;
        }
    }
}
