package com.company.implementations_heaps;

public class HeapSort {

    // MAX HEAP
    public void heapsort(int[] ar) {
        if (ar == null) return;
        int n = ar.length;

        // Heapify, converts array into binary heap O(n)
        for (int i = Math.max(0, (n / 2) - 1); i >= 0; i--) {
            sink(ar, n, i);
        }

        // Sorting bit O(nlogn)
        for (int i = n - 1; i >= 0; i--) { // note the for loop
            // and the function call to sink
            // i selects last element index in for loop
            // and in function call it sends arr length as that index
            // which means one length is reduced, so sink won't touch last element
            // so last element stays largest
            swap(ar, 0, i);
            sink(ar, i, 0);
        }
    }

    private static void sink(int[] ar, int n, int i) {
        while (true) {
            int left = 2 * i + 1; // Left  node
            int right = 2 * i + 2; // Right node
            int largest = i;

            // Right child is larger than parent
            if (right < n && ar[right] > ar[largest]) largest = right;

            // Left child is larger than parent
            if (left < n && ar[left] > ar[largest]) largest = left;

            // Move down the tree following the largest node
            if (largest != i) {
                swap(ar, largest, i);
                i = largest;
            } else break;
        }
    }

    private static void swap(int[] ar, int i, int j) {
        int tmp = ar[i];
        ar[i] = ar[j];
        ar[j] = tmp;
    }
}
