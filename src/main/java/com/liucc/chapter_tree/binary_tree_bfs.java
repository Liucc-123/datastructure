package com.liucc.chapter_tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.liucc.utils.PrintUtil;
import com.liucc.utils.TreeNode;

/**
 * 广度优先搜索 遍历树
 */
public class binary_tree_bfs {

    public static List<Integer> levelOrder(TreeNode root){
        // 初始化队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 初始化一个列表，保存遍历序列
        List<Integer> res = new ArrayList<>();
        // 遍历队列
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll(); // 出队
            res.add(poll.val); // 访问
            if (poll.left!= null) {
                queue.offer(poll.left); // 左子节点入队
            }else if (poll.right != null) {
                queue.offer(poll.right); // 右子节点入队
            }
        }
        return res;
    }

    public static void main(String[] args) {
        /* 初始化二叉树 */
        // 这里借助了一个从数组直接生成二叉树的函数
        TreeNode root = TreeNode.listToTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        System.out.println("\n初始化二叉树\n");
        PrintUtil.printTree(root);

        /* 层序遍历 */
        List<Integer> list = levelOrder(root);
        System.out.println("\n层序遍历的节点打印序列 = " + list);
    }
}
