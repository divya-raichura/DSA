package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BST {

    Node root;

    public BST() {
        this.root = null;
    }

    public BST(int val) {
        this.root = new Node(val);
    }

    public boolean insert(int val) {
        this.root = insert(val, this.root);
        return true;
    }

    private Node insert(int val, Node node) {
        if (node == null) {
            return new Node(val);
        }

        if (node.val < val) {
            node.right = insert(val, node.right);
        } else {
            node.left = insert(val, node.left);
        }

        return node;
    }

    public int getMax() {
        if (this.root == null) {
            System.out.println("No max");
            return -1;
        }

        Node temp = this.root;
        while (temp.right != null) {
            temp = temp.right;
        }

        return temp.val;
    }

    public int getMaxRec() {
        if (this.root == null) {
            System.out.println("No max");
            return -1;
        }
        return getMaxRec(this.root);
    }

    public int getMaxRec(Node node) {
        if (node.right == null) return node.val;
        return getMaxRec(node.right);
    }

    public int getHeight() {
        if (this.root == null) {
            System.out.println("empty tree");
            return -1;
        }
        return getHeight(this.root);
    }

    public int getHeight(Node node) {
        if (node == null) {
            return -1;
        }

        int l = getHeight(node.left);
        int r = getHeight(node.right);

        return Math.max(l, r) + 1;
    }


    public boolean search(int val) {
        return search(val, this.root);
    }

    private boolean search(int val, Node node) {
        if (node == null) {
            return false;
        }
        if (node.val == val) {
            return true;
        }
        if (node.val < val) {
            return search(val, node.right);
        } else {
            return search(val, node.left);
        }
    }


    public boolean isBst() {
        /*
      3 solutions :
         1) make is greater & is smaller functions and check
         o(n^2)
        return isBst1(this.root);
         2) take range
         o(n)
        return isBstRange(this.root, INT_MIN, INT_MAX);
         3) do inorder and check in place cause, inorder of bst => sorted list
        * */
        return true;
    }

    private boolean isBst1(Node node) {
        if (node == null) return true;

        return isSubtreeLesser(node.left, node.val) && isSubtreeGreater(node.right, node.val) && isBst1(node.left) && isBst1(node.right);
    }

    private boolean isSubtreeLesser(Node node, int val) {
        if (node == null) return true;

        return node.val <= val && isSubtreeLesser(node.left, val) && isSubtreeLesser(node.right, val);
    }

    private boolean isSubtreeGreater(Node node, int val) {
        return node.val > val && isSubtreeLesser(node.left, val) && isSubtreeLesser(node.right, val);
    }

    private boolean isBstRange(Node node, int min, int max) {
        if(node == null) return true;

        int nodeVal = node.val;
        // nodeVal > min && nodeVal < max are constant time, in method 1, those func were inefficient
        return (nodeVal > min && nodeVal < max && isBstRange(node.left, min, nodeVal) && isBstRange(node.right, nodeVal, max));
    }

    public void dfs() {
        System.out.println("preorder");
        preorder(this.root);
        System.out.println();
        System.out.println("inorder");
        inorder(this.root);
        System.out.println();
        System.out.println("postorder");
        postorder(this.root);
        System.out.println();
    }

    private void preorder(Node node) {
        if (node == null) return;

        System.out.print(node.val + " ");
        preorder(node.left);
        preorder(node.right);
    }

    private void inorder(Node node) {
        if (node == null) return;

        inorder(node.left);
        System.out.print(node.val + " ");
        inorder(node.right);
    }

    private void postorder(Node node) {
        if (node == null) return;

        postorder(node.left);
        postorder(node.right);
        System.out.print(node.val + " ");
    }

    public boolean delete(int val) {
        this.root = delete(this.root, val);
        return true;
    }

    private Node delete(Node node, int val) {
        if (node == null) return null;

        if (node.val < val) {
            node.right = delete(node.right, val);
        } else if (node.val > val) {
            node.left = delete(node.left, val);
        } else {
            if (node.right == null && node.left == null) {
                return null;
            } else if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left; // or
                // node = node.left only, because we are returning node below
            } else {
                // get min from right subtree
                Node temp = node.right;
                while (temp.left != null) {
                    temp = temp.left;
                }
                node.val = temp.val;
                // now delete the duplicate from right subtree
                node.right = delete(node.right, node.val);
            }
        }
        return node;
    }

    public int getSuccessor(int val) {
        Node successor = successor(this.root, val);
        return successor.val;
    }

    public Node successor(Node node, int val) {
        // search // o(h)
        Node curr = getNode(val);
        if (curr == null) return null; // value does not exist

        // case 1 : node has right subtree
        if (curr.right != null) {
            return getMinNode(curr.right); // o(h)
        } else { // case 2 : no right subtree // o(h)

            Node ancestor = this.root; // or node as we have passed root as node
            // from main successor function
            Node successor = null;
            while (ancestor != curr) {
                if (ancestor.val > curr.val) {// ancestor has greater val
                    // than curr, so this may be the successor
                    successor = ancestor;
                    ancestor = ancestor.left;
                } else {
                    ancestor = ancestor.right;
                }
            }
            return successor; // node with max val will not have succ
        }
    }

    public Node getMinNode(Node node) {
        if (node == null) return null;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public Node getNode(int val) {
        return getNode(this.root, val);
    }

    private Node getNode(Node node, int val) {
        if (node == null) {
            return null;
        }
        if (node.val == val) {
            return node;
        }
        if (node.val < val) {
            return getNode(node.right, val);
        } else {
            return getNode(node.left, val);
        }
    }

    public void bfs() {
        if (this.root == null) {
            System.out.println("empty tree");
            return;
        }
        List<List<Integer>> bfsAns = bfs(this.root);
        for (List<Integer> lists : bfsAns) {
            for (Integer node : lists) {
                System.out.print(node + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> bfs(Node node) {
        Queue<Node> queue = new LinkedList<>();
        List<List<Integer>> bfsAns = new ArrayList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node temp = queue.remove();
                if (temp.left != null) queue.add(temp.left);
                if (temp.right != null) queue.add(temp.right);
                list.add(temp.val);
            }
            bfsAns.add(list);
        }
        return bfsAns;
    }

    public int minValue() {
        if (root == null) {
            System.out.println("empty tree");
            return -1;
        }
        Node temp = this.root;
        int min = this.root.val;
        while (temp.left != null) {
            temp = temp.left;
            min = temp.val;
        }
        return min;
    }

    public static void main(String[] args) {
        BST bst = new BST();
//        bst.insert(10);
//        bst.insert(-11);
//        bst.insert(12);
//        bst.insert(13);
//        bst.insert(3);
//        bst.insert(4);
//        bst.insert(2);
//        bst.insert(1);
//        System.out.println(bst.getMax());
//        System.out.println(bst.search(-1));
//        System.out.println(bst.getMaxRec());
//        System.out.println(bst.getHeight());
//        System.out.println("bfs :-");
//        bst.bfs();
//        bst.dfs();
//        bst.delete(10);bst.bfs();
//        System.out.println(bst.minValue());

        // to test height
        bst.insert(20);
        bst.insert(10);
        bst.insert(30);
        bst.insert(40);
        bst.insert(25);
        System.out.println("bfs:- ");
        bst.bfs();
        bst.dfs();
        System.out.println("height:- ");
        int height = bst.getHeight();
        System.out.println(height);
    }

    private static class  Node {
        Node left;
        Node right;
        int val;

        public Node(int val) {
            this.val = val;
        }

        public Node(Node left, Node right, int val) {
            this.left = left;
            this.right = right;
            this.val = val;
        }
    }
}
