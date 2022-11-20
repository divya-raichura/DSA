package com.company.intro;

import com.company.implementations.Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Generic {
    public static void main(String[] args) {
        Heap<Student> heap = new Heap<>();
        // since the heap class extended interface 'comparable' when we
        // want to use the heap for custom objects we need to implement
        // the methods inside that 'comparable' interface so that
        // it can use those methods to compare and prioritize the elements
        // there is only one method to be implemented which is compareTo

        // this method is overridden inside the custom class itself

        heap.add(new Student(12, 132, 35));
        heap.add(new Student(23, 153, 55));
        heap.add(new Student(13, 142, 43));
        heap.add(new Student(10, 129, 30));

//        while(!heap.isEmpty()) {
//            System.out.println(heap.remove());
//        }

        // now if we want to compare using other properties then? cause
        // in class we can implement methods of 'comparable'
        // so now we make a separate class which implements 'comparator'
        // which takes '<>' as our custom class -> '<Student>' and override
        // methods of comparator which are 'compare'
        // we pass object of this class in constructor of heap and the heap will
        // use this new object of comparator to compare elements

        // to enable this 'comparator' in our custom heap, we need to make
        // few changes, so instead using java's pq for eg
        PriorityQueue<Student> pq = new PriorityQueue<>(new StudentWComparator());

        pq.add(new Student(12, 162, 55));
        pq.add(new Student(23, 153, 25));
        pq.add(new Student(13, 112, 23));
        pq.add(new Student(10, 129, 60));

        while(!pq.isEmpty()) {
            System.out.println(pq.remove());
        }
    }

    static class Student implements Comparable<Student> {
        int age;
        int h;
        int w;

        public Student(int age, int h, int w) {
            this.age = age;
            this.h = h;
            this.w = w;
        }

        @Override
        public int compareTo(Student o) {
            return this.age - o.age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "age=" + age +
                    ", h=" + h +
                    ", w=" + w +
                    '}';
        }
    }

     static class StudentWComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.w - o2.w;
        }
    }
}
