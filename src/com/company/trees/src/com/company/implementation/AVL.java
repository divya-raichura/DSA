package com.company.implementation;

public class AVL {

    class Node {
        public int bf;

        // The value/data contained within the node.
        public int value;

        // The height of this node in the tree.
        public int height;

        // The left and the right children of this node.
        public Node left, right;

        public Node(int value) {
            this.value = value;
        }
    }

    Node root;

    private int nodeCount = 0;

    // Returns the number of nodes in the tree.
    public int size() {
        return nodeCount;
    }

    // Returns whether the tree is empty.
    public boolean isEmpty() {
        return size() == 0;
    }

    // Return true/false depending on whether a value exists in the tree.
    public boolean contains(int value) {
        return contains(root, value);
    }

    // Recursive contains helper method.
    private boolean contains(Node node, int value) {
        if (node == null) return false;

        if (node.value < value) {
            return contains(node.right, value);
        }

        if (node.value > value) {
            return contains(node.left, value);
        }

        return true; // Found value in tree.
    }

    // Insert/add a value to the AVL tree. The value must not be null, O(log(n))
    public boolean insert(int value) {
        if (!contains(root, value)) {
            root = insert(root, value);
            nodeCount++;
            return true;
        }
        return false;
    }

    // Inserts a value inside the AVL tree.
    private Node insert(Node node, int value) {
        if (node == null) return new Node(value);

        if (node.value < value) {
            // Insert node in right subtree.
            node.right = insert(node.right, value);
        } else {
            // Insert node in left subtree.
            node.left = insert(node.left, value);
        }

        // Update balance factor and height values.
        update(node);

        // Re-balance tree.
        return balance(node);
    }

    // Update a node's height and balance factor.
    private void update(Node node) {
        int leftNodeHeight = (node.left == null) ? -1 : node.left.height;
        int rightNodeHeight = (node.right == null) ? -1 : node.right.height;

        // Update this node's height.
        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);

        // Update balance factor.
        node.bf = rightNodeHeight - leftNodeHeight;
    }

    // Re-balance a node if its balance factor is +2 or -2.
    private Node balance(Node node) {
        // Left heavy subtree.
        if (node.bf == -2) {

            // Left-Left case.
            if (node.left.bf <= 0) {
                return leftLeftCase(node);

                // Left-Right case.
            } else {
                return leftRightCase(node);
            }

            // Right heavy subtree needs balancing.
        } else if (node.bf == +2) {

            // Right-Right case.
            if (node.right.bf >= 0) {
                return rightRightCase(node);

                // Right-Left case.
            } else {
                return rightLeftCase(node);
            }
        }

        // Node either has a balance factor of 0, +1 or -1 which is fine.
        return node;
    }

    public static void main(String[] args) {
        AVL avl = new AVL();
        avl.insert(10);
        avl.insert(23);
        avl.insert(2);
        avl.insert(3);
        avl.insert(14);
        System.out.println("inorder display :- ");
        avl.display();
        avl.remove(2);
        System.out.println();
        avl.display();
    }

    public void display(){
        display(root);
    }

    private void display(Node node) {
        if(node == null) return;

        display(node.left);
        System.out.print(node.value + " ");
        display(node.right);
    }

    private Node leftLeftCase(Node node) {
        return rightRotation(node);
    }

    private Node leftRightCase(Node node) {
        node.left = leftRotation(node.left);
        return leftLeftCase(node);
    }

    private Node rightRightCase(Node node) {
        return leftRotation(node);
    }

    private Node rightLeftCase(Node node) {
        node.right = rightRotation(node.right);
        return rightRightCase(node);
    }

    private Node rightRotation(Node node) {
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        update(node); // vv imp to not update new parent first, as values of new parent depends on childrens
        // and node is one of the childrens
        update(newParent);
        return newParent;
    }

    private Node leftRotation(Node node) {
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        update(node);
        update(newParent);
        return newParent;
    }

    // Remove a value from this binary tree if it exists, O(log(n))
    public boolean remove(int elem) {

        if (contains(root, elem)) {
            root = remove(root, elem);
            nodeCount--;
            return true;
        }

        return false;
    }

    // Removes a value from the AVL tree.
    private Node remove(Node node, int elem)  {
        if (node == null) return null;

        // Dig into left subtree, the value we're looking
        // for is smaller than the current value.
        if (elem < node.value) {
            node.left = remove(node.left, elem);

            // Dig into right subtree, the value we're looking
            // for is greater than the current value.
        } else if (elem > node.value) {
            node.right = remove(node.right, elem);

            // Found the node we wish to remove.
        } else {

            // This is the case with only a right subtree or no subtree at all.
            // In this situation just swap the node we wish to remove
            // with its right child.
            if (node.left == null) {
                return node.right;

                // This is the case with only a left subtree or
                // no subtree at all. In this situation just
                // swap the node we wish to remove with its left child.
            } else if (node.right == null) {
                return node.left;

                // When removing a node from a binary tree with two links the
                // successor of the node being removed can either be the largest
                // value in the left subtree or the smallest value in the right
                // subtree. As a heuristic, I will remove from the subtree with
                // the greatest height in hopes that this may help with balancing.
            } else {

                // Choose to remove from left subtree
                if (node.left.height > node.right.height) {

                    // Swap the value of the successor into the node.
                    int successorValue = findMax(node.left);
                    node.value = successorValue;

                    // Find the largest node in the left subtree.
                    node.left = remove(node.left, successorValue);

                } else {

                    // Swap the value of the successor into the node.
                    int successorValue = findMin(node.right);
                    node.value = successorValue;

                    // Go into the right subtree and remove the leftmost node we
                    // found and swapped data with. This prevents us from having
                    // two nodes in our tree with the same value.
                    node.right = remove(node.right, successorValue);
                }
            }
        }

        // Update balance factor and height values.
        update(node);

        // Re-balance tree.
        return balance(node);
    }

    // Helper method to find the rightmost node (which has the largest value)
    private int findMax(Node node) {
        while (node.right != null) node = node.right;
        return node.value;
    }

    // Helper method to find the leftmost node (which has the smallest value)
    private int findMin(Node node) {
        while (node.left != null) node = node.left;
        return node.value;
    }

}
