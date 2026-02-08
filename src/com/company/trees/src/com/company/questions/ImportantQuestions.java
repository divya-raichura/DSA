package com.company.questions;

public class ImportantQuestions {
    public static void main(String[] args) {
        /*
         * Populating Next Right Pointers in Each Node (no extra space) 1 and 2
         *
         * print only the right/left child of each node
         *
         * right view (bfs, dfs method(imp for knowing how to find level using dfs that i used in cousins qn))
         *
         * cousins (using dfs level-see my leetcode soln)
         *
         * symmetric (iterative + dfs)
         *
         * zigzag traversal -> put flag and add according to flag as 'list.insert(0,
         * val) or list.insert(val)'
         *
         * Diameter
         *
         * invert bt(levelorder(1st submission), preorder-topdown_swap(my 11 may todays soln), postorder-bottomup_swap(kk))
         *
         * https://chatgpt.com/c/552e76f1-56dc-419b-b222-33618e53bf48
         * flatten to linkedlist(leetcode most vote soln, kk soln(iterative, so, better space))
         *
         * validate bst -> max and min as arg, iterative inorder using stack
         *
         * lca in bst -> logN method : do bs and if they are splitting at node OR you
         * hit a node, then you found lca
         *
         * lca in bt -> N method : do inorder, if you found p or q return it else keep
         * going and return null. If while returning a node gets both p and q in l and r
         * respectively(after recur calls) then that node is lca, and if it gets one of
         * the node(l or r), take that node and return it
         *
         * kth smallest in bst
         *
         * Construct Binary Tree from Preorder and Inorder Traversal: 3x solns:-
         * 1) 1st most vote which i submitted
         * 2) its optimised version using hashmap
         * 3) global index of pre and in: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solutions/34543/simple-o-n-without-map/
         *
         * serialize and deserialize bt
         *
         * path sum
         *
         * Sum Root to Leaf Numbers
         *
         * maximum path sum
         *
         * check if given path exists
         *
         * count the no of paths for a given target, not just root to leaf but between any two nodes
         * like we did in max path sum, but here target is given and we have to count no of paths
         *
         * Count Good Nodes in Binary Tree -> take max as arg
         * 
         * vertical order traversal ->
         *
         * inorder successor
         *
         */
    }
}
