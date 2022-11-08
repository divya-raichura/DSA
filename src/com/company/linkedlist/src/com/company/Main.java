package com.company;

import com.company.implementation.CircularLL;
import com.company.implementation.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addLast(10);
        list.addLast(11);
        list.addFirst(1);
        list.addFirst(0);
        list.addAt(3, 2);
//        list.display();
        list.addAt(4, 0);
//        list.display();
        list.removeFirst();
//        list.display();
        list.removeLast();
//        list.removeLast();
//        list.removeLast();
//        list.removeLast();
//        list.removeLast();
//        list.display();
//        list.removeAt(3);

        CircularLL ll = new CircularLL();
        ll.insert(10);
        ll.insert(20);
        ll.insert(30);
        ll.insert(40);
        ll.insert(50);
        ll.display();
        ll.delete(40);
        ll.delete(30);
        ll.display();
        ll.delete(130);
        ll.display();
        ll.delete(20);
        ll.delete(50);
        ll.delete(10);
        ll.display();
    }
}
