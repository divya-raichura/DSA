package com.company.implementation;

public class SegmentTree {
    /*
    *
    * notes in dsa3 book
    *
    * */

    public static void main(String[] args) {
        int[] arr = {3,8,6,7,-2,-8,4,9};
        SegmentTree segmentTree = new SegmentTree(arr);
        segmentTree.display();
    }

    private Node root;

    public SegmentTree(int[] arr) {
        // create tree using this arr
        if (arr.length == 0) {
            System.out.println("arr should not be empty");
            return;
        }
        this.root = constructTree(arr,0, arr.length - 1);
    }

    // o(n) to make tree
    private Node constructTree(int[] arr, int s, int e) {
        if (s == e) {
            // leaf node
            Node leaf = new Node(s, e);
            leaf.data = arr[s];
            return leaf;
        }

        Node node = new Node(s, e);

        // arr will be divided into half, m=len(arr)/2 means left arr will have m elements
        // right arr will have remaining elements starting from m
        // m=(s+e)/2 means, n/2 karke jo ans ayega utne hi elements left me honge, its just the mid element
        // jo m h, woh left me jaega instead of right
        // so no of elements in l and r will not change, but m will be in l arr for s+e and on r for n/2
        int m = (s + e) / 2;
        // eg: 0,3 range, 1) m=s+e/2=3/2=1 and 2) n/2=4/2=2
        // so 1) says 1 element in left and others in right 2) says 2 in left
        // 2) is correct but 1) is not, so to make 1) correct, we give left array the mid element
        // so now, 1) elements till index 1 or 1 element and mid too, so 2 elements
        // 0,1 and 2,3
        // MAIN NOTE: BASICALLY, n/2 takes perfect number of elements in array, but since s+e is working on
        // indexes, it takes 1 element less into consideration, hence we give it middle element too, so it stays
        // balanced and equally divides
        // tldr; 1) gives "index" till which we have to go to fill elements in left arr
        // 2) gives "no of elements" to fill in left array

        node.left = constructTree(arr, s, m);
        node.right = constructTree(arr, m + 1, e);

        node.data = node.left.data + node.right.data;
        return node;
    }

    public void display() {
        if (this.root == null) return;
        display(this.root);
    }

    private void display(Node node) {
        String str = "";

        if (node.left != null) {
            str = str + "Interval=[" + node.left.startInterval + "-" + node.left.endInterval + "] and data: " + node.left.data + " => ";
        } else {
            str = str + "No left child => ";
        }

        // for current node
        str = str + "Interval=[" + node.startInterval + "-" + node.endInterval + "] and data: " + node.data + " <= ";


        if (node.right != null) {
            str = str + "Interval=[" + node.right.startInterval + "-" + node.right.endInterval + "] and data: " + node.right.data;
        } else {
            str = str + "No right child";
        }

        System.out.println(str + '\n');

        if (node.left != null) {
            display(node.left);
        }

        if (node.right != null) {
            display(node.right);
        }
    }

    public int query(int qsi, int qei) {
        if (this.root == null) return -1;
        return this.query(this.root, qsi, qei);
    }

    private int query(Node node, int qsi, int qei) {
        if (node.startInterval >= qsi && node.endInterval <= qei) {
            // node is completely inside the query
            return node.data;
        } else if (node.startInterval > qei || node.endInterval < qsi) {
            // completely outside
            return 0;
        } else {
            // overlapping
            return this.query(node.left, qsi, qei) + this.query(node.right, qsi, qei);
        }
    }

    public void update(int index, int value) {
        if (this.root == null) return;
        this.root.data = update(this.root, index, value);
    }

    private int update(Node node, int index, int value) {
        if (index >= node.startInterval && index <= node.endInterval) {
            if (index == node.startInterval && index == node.endInterval) {
                node.data = value;
            } else {
                int l = update(node.left, index, value);
                int r = update(node.right, index, value);
                node.data = l + r;
            }
            return node.data;
        }
        return node.data;
    }

    private static class Node {
        int data;
        int startInterval;
        int endInterval;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }

        public Node(int startInterval, int endInterval) {
            this.startInterval = startInterval;
            this.endInterval = endInterval;
        }

        public Node(int data, int startInterval, int endInterval) {
            this.data = data;
            this.startInterval = startInterval;
            this.endInterval = endInterval;
        }
    }
}
