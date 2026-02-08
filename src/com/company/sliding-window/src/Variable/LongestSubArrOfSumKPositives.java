package Variable;

public class LongestSubArrOfSumKPositives {

    /*
    *
    * ps:
    * we have to find all subarrays whose sum is k
    * and from all those subarrays we have to output the subarr whose length is largest
    *
    * so,
    * fixed: maximize sum and win size given
    * variable: sum given and maximize win size
    *
    * */
    public static void main(String[] args) {
//        int[] arr = {1,1,1};
        int[] arr = {4,1,1,1,2,3,5};
        int k = 5;
        solution1(arr, k);
    }

    // this does not work for -ve nos
    static void solution1(int[] arr, int k) {
        int i = 0, j = 0;
        int sum = 0;
        int maxWindowSize = 0;

        while (j < arr.length) {
            sum += arr[j];

            while (sum > k) {
                sum -= arr[i];
                i++;
            }

            // this flow is imp, this check should be below sum > k
            // for eg: 2,2,1,1,2 k=2
            // element at ind 1 is also a subarr but it will be missed if we don't put this check
            // no of subarrays with sum k will give wrong ans
            if (sum == k) {
                maxWindowSize = Math.max(maxWindowSize, j - i + 1);
            }

            j++;
        }

        System.out.println(maxWindowSize);
    }
}
