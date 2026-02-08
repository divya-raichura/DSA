package Variable;

import java.util.Arrays;
import java.util.HashMap;

public class LongestSubArrOfSumKNegatives {

    // same code as positive elements does not work because we increment i
    // so if in future j gets negative element, then that might have worked with that value of i that
    // we skipped
    // so, we use different method: using hashmap to store cumulative sum

    // https://takeuforward.org/arrays/longest-subarray-with-sum-k-postives-and-negatives/


    public static void main(String[] args) {
//        int[] arr = {1,2,3,4,5};
//        int[] sum = new int[arr.length + 1];
//        sum[0] = 0;
//
//        for (int i = 1; i < arr.length + 1; i++) {
//            sum[i] = sum[i - 1] + arr[i - 1];
//        }
//
//        System.out.println(Arrays.toString(sum));
//
//        System.out.print("Sum of all elements of array or we can say, sum of subarr of size = arr: ");
//        System.out.println(sum[sum.length - 1] - sum[0]);


    }
}
