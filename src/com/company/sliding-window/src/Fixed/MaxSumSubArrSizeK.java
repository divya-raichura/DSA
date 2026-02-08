package Fixed;

public class MaxSumSubArrSizeK {
    public static void main(String[] args) {
        int[] arr = {2,5,1,99,8,2,9,111};
        int k = 3;
//        8
//        14
//        11
//        19
//        12

        // calculate sum of all possible sub-arrays of size k
        // find the max of these sums
        sol3(arr, k);
    }

    // 1 loop 2 pointer //
    static void sol1(int[] arr, int k) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;
        int n = arr.length;

        int left = 0;
        int right = 0;
        while (right < n) {
//            // Add the next element to currentSum
//            currentSum += arr[right];
//
//            // If the window size exceeds k, remove the leftmost element
//            if (right - left + 1 > k) {
//                currentSum -= arr[left];
//                left++;
//            }
//
//            // Update maxSum if currentSum is greater
//            maxSum = Math.max(maxSum, currentSum);
//
//            right++;
            currentSum += arr[right];
            if (right - left + 1 < k) {
                right++;
            } else if (right - left + 1 == k) {
                maxSum = Math.max(currentSum, maxSum);
                currentSum = currentSum - arr[left];
                left++;
                right++;
            }
        }

        System.out.println(maxSum);
    }

    // 2 loops 1 pointer
    static void sol2(int[] arr, int k) {
        int maxSum = 0;
        int currentSum = 0;
        int n = arr.length;

        // Calculate the sum of the first subarray of size k
        for (int i = 0; i < k; i++) {
            currentSum += arr[i];
        }

        // Initialize maxSum with the sum of the first subarray
        maxSum = currentSum;

        // Slide the window and calculate the maximum sum
        for (int i = k; i < n; i++) {
            currentSum = currentSum - arr[i - k] + arr[i];
            maxSum = Math.max(maxSum, currentSum);
        }

        System.out.println(maxSum);
    }

    // 2 loops, 2 pointers // :)
    static void sol3(int[] arr, int k) {
        int sum = 0;

        int s = 0;
        int e = 0;

        while (e < k) {
            sum += arr[e];
            e++;
        }

        int ans = sum;

        while (e < arr.length) {
            sum += arr[e++];
            sum -= arr[s++];
            ans = Math.max(ans, sum);
        }

        System.out.println(ans);
    }

    // 1 loop 1 pointers
    static void sol4(int[] arr, int k) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;
        int n = arr.length;

        int i = 0;
        while (i < n) {
            // Initialize the first subarray sum of size k
            if (i < k) {
                currentSum += arr[i];
            } else {
                // Slide the window
                currentSum = currentSum - arr[i - k] + arr[i];
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                }
            }
            i++;
        }

        System.out.println(maxSum);
    }
}
