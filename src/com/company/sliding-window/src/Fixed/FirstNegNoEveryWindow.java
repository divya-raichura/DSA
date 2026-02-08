package Fixed;

import java.util.*;

public class FirstNegNoEveryWindow {
    public static void main(String[] args) {
        int[] arr = {12,-1,-7,8,-15,30,16,28}; // ans = {-1,-1,-7,-15,-15,0}
        int k = 3;
        int n = arr.length;

        // first negative number in every window of size k, print 0 if a window does not contain
        // any negative number
        // so, for every window, there will be an output


        solution3(arr, k);
        solution2(arr, k);
        solution1(arr, k);
    }

    // 1 loop 2 pointer //
    static void solution3(int[] arr, int k) {
        List<Integer> ans = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        int i = 0, j = 0;

        while (j < arr.length) {
            if (arr[j] < 0) {
                queue.add(arr[j]);
            }

            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {
                if (queue.isEmpty()) {
                    ans.add(0);
                } else {
                    ans.add(queue.peek());
                }
                if (!queue.isEmpty() && queue.peek() == arr[i]) {
                    queue.remove();
                }
                i++;
                j++;
            }
        }

        System.out.println(ans);
    }

    // 2 while loops and 2 pointers (correct version of my try)
    static void solution1(int[] arr, int k) {
        List<Integer> result = new ArrayList<>();
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int start = 0, end = 0;

        // Process the first window
        while (end < k) {
            if (arr[end] < 0) {
                deque.offerLast(arr[end]);
            }
            end++;
        }

        // Store the first negative number from the first window
        if (!deque.isEmpty()) {
            result.add(deque.peekFirst());
        } else {
            result.add(0);
        }

        // Slide the window and process remaining elements
        while (end < arr.length) {
            // Remove elements from the deque that are out of the current window
            if (!deque.isEmpty() && deque.peekFirst() == arr[start]) {
                deque.pollFirst();
            }

            // Add the current element if it's negative
            if (arr[end] < 0) {
                deque.offerLast(arr[end]);
            }

            // Store the first negative number from the current window
            if (!deque.isEmpty()) {
                result.add(deque.peekFirst());
            } else {
                result.add(0);
            }

            start++;
            end++;
        }

        System.out.println(result);
    }

    // 2 loops 1 pointer
    static void solution2(int[] arr, int k) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();

        // Process the first window
        for (int i = 0; i < k; i++) {
            if (arr[i] < 0) {
                queue.offer(i);
            }
        }

        // Store the first negative number from the first window
        if (!queue.isEmpty()) {
            result.add(arr[queue.peek()]);
        } else {
            result.add(0);
        }

        // Slide the window and process remaining elements
        for (int i = k; i < arr.length; i++) {
            // Remove elements from the queue that are out of the current window
            if (!queue.isEmpty() && queue.peek() < i - k + 1) {
                queue.remove();
            }

            // Add the current element if it's negative
            if (arr[i] < 0) {
                queue.offer(i);
            }

            // Store the first negative number from the current window
            if (!queue.isEmpty()) {
                result.add(arr[queue.peek()]);
            } else {
                result.add(0);
            }
        }

        System.out.println(result);
    }


    // wrong
    // problem is, i did not consider the output of last window
    // in sum problem (previous), we considered first window after first loop and saved its ans
    // then in second loop, we shifted window, and considered that window's calculations

    // so outside the loop,
    // we need to consider first window's solution
    // or the last window's solution

    // if we do 1st, then in 2nd while loop we shift window and then do calcs, like we did in sum q
    // if we do last, then in 2nd loop, we do calcs and then shift window like i did below
    static void myTry(int[] arr, int n, int k) {
        int s = 0;
        int e = 0;

        Queue<Integer> queue = new LinkedList<>();

        while (e < k) {
            if (arr[e] < 0) {
                queue.add(arr[e]);
            }
            e++;
        }

        List<Integer> ans = new ArrayList<>();

        while (e < n) {
            if (queue.isEmpty()) {
                ans.add(0);
            } else {
                ans.add(queue.peek());
            }

            if (!queue.isEmpty() && queue.peek() == arr[s]) {
                queue.remove();
            }

            if (arr[e] < 0) {
                queue.add(arr[e]);
            }

            s++;
            e++;
        }

        System.out.println(ans);
    }
}
