package com.company.implementations_heaps;

import java.util.Collections;
import java.util.PriorityQueue;

public class PQ {
    public static void main(String[] args) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // Min Heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // Max Heap
        PriorityQueue<Integer> customPriorityQueue = new PriorityQueue<>((a, b) -> b - a); // Max Heap using lambda comparator
        // instead of using b - a, we can also do Integer.compare(b, a) // it internally does the same b - a
        /*
        * how does this lambda comparator work? nothing related to lambda, but related to comparator actually
        *
        * so, pq is naturally a min-heap, so if(less(n1,n2)) then it swaps child with parent
        * where less => n1.compareTo(n2) or compare(n1,n2) <= 0
        * ie, when n1 is lesser then return negative and so, it will swap with parent
        *
        * when we make custom comparator to return n2-n1
        * it returns negative when n2 is smaller,
        * so what will happen is, if parent is smaller, it will get swapped instead
        * hence bigger numbers will be available in root, ie, max-heap
        *
        * */

        minHeap.add(5); // throws exception if heap cannot add
        maxHeap.offer(10); // false if heap cannot add
        minHeap.add(15); // throws exception if heap cannot add
        maxHeap.offer(20); // false if heap cannot add

        int min = minHeap.poll(); // throws null
        int max = maxHeap.remove(); // throws exception

        int minValue = minHeap.peek();
        int maxValue = maxHeap.peek();

    }


    static void fun() {

//        PriorityQueue<Pair> pq = new PriorityQueue<>();

        // i want to do following:
        // put 1) pair in pq 2) map entry in pq
        // if i change the pair ka value, does it also change the pq ordering of max/min?
        // with that, it also came to me that, why did i use pair? i could just use map? in those qns

        // map ke sath
//        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
//        Map<Character, Integer> map = new HashMap<>();
//
//        map.put('c', 3);
//        map.put('d', 5);
//
//        pq.addAll(map.entrySet());
//
//        System.out.println(map);
//        System.out.println(pq + "\n");
//
//        map.put('c', 9);
//
//        System.out.println(map);
//        System.out.println(pq);
//
//        while (!pq.isEmpty()) {
//            System.out.println(pq.remove());
//        }

// conclusion
// Changes made to the HashMap after adding its entries to the PriorityQueue do not affect
// the elements already in the PriorityQueue.


        // using pair
//        PriorityQueue<Pair> pq = new PriorityQueue<>();
//        Pair a = new Pair('a', 9999);
//        Pair b = new Pair('b', 100);
//
//        pq.add(a);
//        pq.add(b);
//
//        System.out.println(pq);
//
//        a.index = 1;
//
//        System.out.println(pq);

        // same conclusion

    }
}
