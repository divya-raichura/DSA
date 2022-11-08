package com.company.implementation;

public class LinkedList {

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public void addFirst(int val) {
        if (isEmpty()) {
            head = tail = new Node(val);
        } else {
            head = new Node(val, head);
        }
        size++;
    }

    public void addLast(int val) {
        if (isEmpty()) {
            head = tail = new Node(val);
        } else {
            tail.next = new Node(val);
            tail = tail.next;
        }
        size++;
    }

    public void addAt(int val, int index) {
        if (index == 0) {
            addFirst(val);
            return;
        }

        if (index == size) {
            addLast(val);
            return;
        }

        Node temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        temp.next = new Node(val, temp.next);
        size++;
    }

    public int removeFirst() {
        if (isEmpty()) {
            System.out.println("list is empty");
            return -1;
        }
        int val = head.value;
        head = head.next;
        size--;

        if (isEmpty()) tail = null;

        return val;
    }

    public int removeLast() {
        if (isEmpty()) {
            System.out.println("list is empty");
            return -1;
        }
        int val = tail.value;
        tail = getNode(size - 2);
        size--;

        if (isEmpty()) head = null;
        else tail.next = null;

        return val;
    }

    public int removeAt(int index) {
        if (index < 0 || index >= size) {
            System.out.println("invalid index");
            return -1;
        }

        if (index == 0) return removeFirst();
        if (index == size - 1) return removeLast();

        Node remove = getNode(index - 1);
        int val = remove.next.value;
        remove.next = remove.next.next;
        size--;
        return val;
    }

    private Node getNode(int index) {
//        if(index < 0) return null;
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.value + " -> ");
            temp = temp.next;
        }
        System.out.println("NULL");
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    private static class Node {
        private int value;
        private Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node(int value) {
            this.value = value;
        }
    }
}
