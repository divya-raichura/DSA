package com.company.questions2.Heap;

import java.util.PriorityQueue;

public class NearOrigin {

    public static void main(String[] args) {
        int[][] coOrdinates = {
                {1, 3},
                {-2, 2},
                {5, 8},
                {0, 1}
        };

        int k = 2;

        nearOrigin(coOrdinates, k);
    }

    static void nearOrigin(int[][] coOrdinates, int k) {
        // since we want k minimum distance points, we use max-heap
        // so at the end, only k minimum points remain in the heap, all max points are removed

        PriorityQueue<Points> maxHeap = new PriorityQueue<>(); // maxHeap created using class compareTo

        for (int[] coOrdinate : coOrdinates) {
            Points point = new Points(coOrdinate[0], coOrdinate[1]);
            maxHeap.add(point);
            if (maxHeap.size() > k) {
                maxHeap.remove();
            }
        }

        // yes, we can just use min heap, store all elements, then just pop k times
        // we will get k minimum, not only in this but all questions till now

        // but what's the point then? we get same time complexity that we will get in sorting
        // so we might just use sorting instead right?
        // point here is to get solution in n*log(k) not n*log(n), that's why we use max heap
        // and don't let the heap go beyond k size, so time complexity to add or remove will be n*log(k)

        while (!maxHeap.isEmpty()) {
            System.out.println(maxHeap.remove());
        }
    }

    static class Points implements Comparable<Points> {
        int x;
        int y;
        int distance;

        public Points(int x, int y) {
            this.x = x;
            this.y = y;
            this.distance = (int) (Math.pow(this.x, 2) + Math.pow(this.y, 2)); // no need to use sqrt
            // because, if a point's x2 + y2 is bigger, then sqrt will also be bigger, so we save some calculations here
//            this.distance = (int) Math.pow(Math.pow(this.x, 2) + Math.pow(this.y, 2), 0.5);
//            this.distance = (int) Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
        }

        @Override
        public String toString() {
            return "Points{" +
                    "x=" + x +
                    ", y=" + y +
                    ", distance=" + distance +
                    '}';
        }

        @Override
        public int compareTo(Points points) {
            return points.distance - this.distance; // max-heap
        }
    }
}
