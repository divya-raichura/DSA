package Fixed;

import java.util.*;

public class MaxOfAllSubArrays {
    public static void main(String[] args) {

//        int[] arr = {1,3,-1,-3,5,3,6,7}; // 8 size
        int[] arr = {1,3,-1,-3,5,3,6,7,1,-5,3,-1,-2,-4,4}; // 8 size
        int k = 3;


        solution(arr, k);
    }

    static void solution(int[] arr, int k) {
        ArrayList<Integer> ans = new ArrayList<>();

        int i = 0, j = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        while (j < arr.length) {
//            pq.add(arr[j]); // one trick we can do is, if arr[j] is largest, we can empty the pq
            // because, till i reaches this element, this element will be the largest, and when
            // i reaches here, j will also move forward and add new elements till then
            // so:
            // empty the pq
            if (!pq.isEmpty() && pq.peek() < arr[j]) {
                while (!pq.isEmpty()) {
                    pq.remove();
                }
            }

            pq.add(arr[j]);

            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {
                ans.add(pq.peek());

                if (pq.peek() == arr[i]) {
                    pq.remove();
                }

                i++;
                j++;
            }
        }

        System.out.println(ans);
        System.out.println(arr.length);
        System.out.println(ans.size());
    }
}
