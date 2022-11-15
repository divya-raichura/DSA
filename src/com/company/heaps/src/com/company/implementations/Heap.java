package com.company.implementations;

import java.util.ArrayList;
import java.util.List;

public class Heap<T extends Comparable<T>> {

    List<T> heap = null;

    public Heap() {
        this(1);
    }

    public Heap(int size) {
        heap = new ArrayList<>(size);
    }

//    Construct a priority queue using heapify in O(n) time
    public Heap(T[] arr) {
        this(arr.length);
        int arrLen = arr.length;

        for (int i = 0; i < arrLen; i++) heap.add(arr[i]);

        for (int i = Math.max(0, (arrLen / 2) - 1); i >= 0; i--) sinkRec(i);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return heap.size();
    }

    public void clear() {
        heap.clear();
    }

    public T peek() {
        if (isEmpty()) return null;
        return heap.get(0);
    }

    // Test if an element is in heap, O(n)
    public boolean contains(T elem) {
        // Linear scan to check containment
        for (int i = 0; i < size(); i++) if (heap.get(i).equals(elem)) return true;
        return false;
    }

    // Adds an element to the priority queue, the
    // element must not be null, O(log(n))
    public void add(T elem) {
        if (elem == null) return;

        heap.add(elem);

        int indexOfLastElem = size() - 1;
        swimRec(indexOfLastElem);
    }

    // Perform bottom up node swim, O(log(n))
    private void swimRec(int i) {
        if (i == 0) return;
        int parent = (i - 1) / 2;
        if (less(i, parent)) {
            swap(i, parent);
            swimRec(parent);
        }
    }

    // Perform bottom up node swim, O(log(n))
    private void swimIter(int i) {
        int parent = (i - 1) / 2;
        while (i > 0 && less(i, parent)) {
            swap(i, parent);
            i = parent;
            parent = (i - 1) / 2;
        }
    }

    // Removes a minimum priority node, O(log(n))
    public T remove() {
        if (isEmpty()) return null;

        int indexOfLastElem = size() - 1;
        T removedData = heap.get(0);
        swap(0, indexOfLastElem);

        heap.remove(indexOfLastElem);

        if (indexOfLastElem == 0) return removedData;

        sinkRec(0);

        return removedData;
    }

    // Removes a particular element in the heap, O(n)
    public boolean remove(T element) {
        if (element == null) return false;
        // Linear removal via search, O(n)
        for (int i = 0; i < size(); i++) {
            if (element.equals(heap.get(i))) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    // Removes a node at particular index, O(log(n))
    private T removeAt(int i) {
        if (isEmpty()) return null;

        int indexOfLastElem = size() - 1;
        T removed_data = heap.get(i);
        swap(i, indexOfLastElem);

        // Obliterate the value
        heap.remove(indexOfLastElem);

        // Check if the last element was removed
        if (i == indexOfLastElem) return removed_data;
        T elem = heap.get(i);

        // Try sinking element
        sinkRec(i);

        // If sinking did not work try swimming
        if (heap.get(i).equals(elem)) swimRec(i);
        return removed_data;
    }

    // Top down node sink, O(log(n))
    private void sinkRec(int i) {
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int smallest = i;

        if (l < heap.size() && less(l, smallest)) smallest = l;
        if (r < heap.size() && less(r, smallest)) smallest = r;

        if (smallest != i) {
            swap(i, smallest);
            sinkRec(smallest);
        }
    }

    // Top down node sink, O(log(n))
    private void sinkIter(int i) {
        while (true) {
            int l = 2 * i + 1;
            int r = 2 * i + 2;
            int smallest = i;

            if (l < heap.size() && less(l, smallest)) smallest = l;
            if (r < heap.size() && less(r, smallest)) smallest = r;

            if (smallest != i) {
                swap(i, smallest);
                i = smallest;
            } else break;
        }
    }

    // Recursively checks if this heap is a min heap
    // This method is just for testing purposes to make
    // sure the heap invariant is still being maintained
    // Called this method with k=0 to start at the root
    public boolean isMinHeap(int k) {
        // If we are outside the bounds of the heap return true
        int heapSize = size();
        if (k >= heapSize) return true;

        int left = 2 * k + 1;
        int right = 2 * k + 2;

        // Make sure that the current node k is less than
        // both of its children left, and right if they exist
        // return false otherwise to indicate an invalid heap
        if (left < heapSize && !less(k, left)) return false;
        if (right < heapSize && !less(k, right)) return false;

        // Recurse on both children to make sure they're also valid heaps
        return isMinHeap(left) && isMinHeap(right);
    }

    private void swap(int i, int j) {
        T e1 = heap.get(i);
        T e2 = heap.get(j);
        heap.set(i, e2);
        heap.set(j, e1);
    }

    private boolean less(int i, int j) {
        T e1 = heap.get(i);
        T e2 = heap.get(j);
        return e1.compareTo(e2) <= 0;
    }

//    public List<T> heapsort() {
//        int size = size(); // V.V.V IMP to store size, as
//        // if we use size() again after deleting, then that size will be
//        // different from the size used to create array
//        List<T> ans = new ArrayList<>(size);
//        List<T> list = new ArrayList<>(heap);
//        for (int i = 0; i < size; i++) {
//            ans.add(size - i - 1, remove());
//        }
//        heap = list;
//        return ans;
//    }

    public List<T> heapsort() {
        int size = size(); // V.V.V IMP to store size, as
        // if we use size() again after deleting, then that size will be
        // different from the size used to create array
        List<T> ans = new ArrayList<>(size);
        List<T> list = new ArrayList<>(heap);
        for (int i = 0; i < size; i++) {
            ans.add(remove());
        }
        heap = list;
        return ans;
    }

    // below function is for max-heap, the implementation in this file
    // is of min-heap so above function is correct, cause min-heap will
    // give min value everytime, which we can directly add in the list
//    public List<T> heapsort() {
//        int size = size();
//        List<T> ans = new ArrayList<>(size);
//        for (int i = 0; i < size; i++) ans.add(null);
//        List<T> list = new ArrayList<>(heap);
//        for (int i = 0; i < size; i++) {
//            ans.set(size - i - 1, remove());
//        }
//        heap = list;
//        return ans;
//    }

    // for testing
    public void display() {
        System.out.println(heap);
    }
}
