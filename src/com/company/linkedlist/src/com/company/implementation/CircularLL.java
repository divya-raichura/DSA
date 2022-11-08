package com.company.implementation;

public class CircularLL {
    private Node head;
    private Node tail;
    private int size;

    public CircularLL() {
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(int val) {
        if (isEmpty()) {
            Node node = new Node(val);
            head = tail = node;
            node.next = node;
        } else {
            tail.next = new Node(val, head);
            tail = tail.next;
        }

        size++;
    }

    public int delete(int val) {
        if (isEmpty()) {
            System.out.println("empty list!");
            return -1;
        }
        int deleted = -1;

        // only one node
        if (size == 1) {
            deleted = head.val;
            head = tail = null;
        } else if (head.val == val) {
            deleted = head.val;
            tail.next = head.next;
            head = head.next;
        } else {
            Node temp = getPrevNode(val);
            if (temp == null) {
                System.out.println("no such value exists in list");
                return -1;
            }
            temp.next = temp.next.next;
        }
        size--;
        return deleted;
    }

    private Node getPrevNode(int val) {
        Node temp = head;
        do {
            if (temp.next.val == val) {
                return temp;
            }
            temp = temp.next;
        } while (temp != head);
        return null;
    }

    public void display() {
        if (head == null) {
            System.out.println("NULL");
            return;
        }
        Node temp = head;
        do {
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        } while (temp != head);
        System.out.printf("HEAD (%d)\n", temp.val);
    }

    private static class Node {
        private int val;
        private Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

        public Node(int val) {
            this.val = val;
        }
    }
}
