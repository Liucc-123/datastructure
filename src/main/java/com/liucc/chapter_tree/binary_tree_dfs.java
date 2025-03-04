package com.liucc.chapter_tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.liucc.utils.PrintUtil;
import com.liucc.utils.TreeNode;

/**
 * 深度优先搜索 遍历树
 */
public class binary_tree_dfs {
    static List<Integer> list = new ArrayList<>();
    //前序遍历 根->左->右
    static void preOrder(TreeNode root){
        if (root == null) {
            return;
        }
        list.add(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    // 中序遍历 左->根->右
    static void inOrder(TreeNode root){
        if (root == null) {
            return;
        }
        inOrder(root.left);
        list.add(root.val);
        inOrder(root.right);
    }

    // 后序遍历 左->右->根
    static void postOrder(TreeNode root){
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        list.add(root.val);
    }


    public static void main(String[] args) {
        /* 初始化二叉树 */
        // 这里借助了一个从数组直接生成二叉树的函数
        TreeNode root = TreeNode.listToTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        System.out.println("\n初始化二叉树\n");
        PrintUtil.printTree(root);

        // 前序遍历
        preOrder(root);
        System.out.println("\n前序遍历序列打印：" + list.toString());
        // 中序遍历
        list.clear();
        inOrder(root);
        System.out.println("\n中序遍历序列打印：" + list.toString());
        // 后序遍历
        list.clear();
        postOrder(root);
        System.out.println("\n后序遍历序列打印：" + list.toString());
    }
}
