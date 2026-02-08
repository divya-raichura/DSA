package Variable;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LongestSubStrWithKUniqueChars {
    public static void main(String[] args) {

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

        solution1("aabacbebebe", 3);

    }

    static void solution1(String str, int k) {
        char[] arr = str.toCharArray();
        int i = 0, j = 0, ans = 0;
        Map<Character, Integer> map = new HashMap<>();

        while (j < arr.length) {
            map.put(arr[j], map.getOrDefault(arr[j], 0) + 1);

            while (map.size() > k) {
                int oldValue = map.get(arr[i]);
                int newValue = oldValue - 1;

                if (newValue == 0) {
                    map.remove(arr[i]);
                } else {
                    map.put(arr[i], newValue);
                }

                i++;
            }

            if (map.size() == k) {
                ans = Math.max(ans, j - i + 1);
            }

            j++;
        }

        System.out.println(ans);
    }

//    static class Pair implements Comparable<Pair> {
//        char c;
//        int index;
//
//        public Pair(char c, int index) {
//            this.c = c;
//            this.index = index;
//        }
//
//        @Override
//        public int compareTo(Pair pair) {
//            return this.index - pair.index;
//        }
//
//        @Override
//        public String toString() {
//            return "Pair{" +
//                    "c=" + c +
//                    ", index=" + index +
//                    '}';
//        }
//    }
}
