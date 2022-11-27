package com.company.questions;

public class ImportantQuestions {
    public static void main(String[] args) {
        /*
         * maximum path sum
         * zigzag traversal
         * Count Good Nodes in Binary Tree
         * invert bt
         * validate bst
         * */
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null) return q == null;
        if (q == null) return p == null;
        /* if(p == null || q == null) return p == q; */

        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    private static class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode() {
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
