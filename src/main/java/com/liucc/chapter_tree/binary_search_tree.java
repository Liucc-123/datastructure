package com.liucc.chapter_tree;

import com.liucc.utils.PrintUtil;
import com.liucc.utils.TreeNode;

/**
 * 二叉搜索树
 * 特点：左子树值 < 根节点值 < 右子树值
 */
class BinarySearchTree {
    private TreeNode root;

    public BinarySearchTree() {
        this.root = null; // 初始化空树
    }

    // 获取根节点
    public TreeNode getRoot() {
        return root;
    }

    // 查找节点
    public TreeNode search(int num) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val == num) { // 找到目标值
                return cur;
            } else if (cur.val > num) { // 目标值在左子树
                cur = cur.left;
            } else { // 目标值在右子树
                cur = cur.right;
            }
        }
        return null;
    }

    /**
     * 插入节点
     * 注意事项：1、插入节点的值不能重复；2、定义节点 prev 保存父节点，方便插入操作
     */
    public void insert(int num) {
        // 空树情况
        if (root == null) {
            root = new TreeNode(num);
            return;
        }
        // 循环查找，定位待插入元素位置
        TreeNode cur = root, prev = null;
        while (cur != null) {
            // 元素重复，直接结束
            if (cur.val == num) {
                System.out.println("树中已存在值为" + num + "的节点，禁止重复插入");
                return;
            }
            prev = cur;
            if (cur.val > num) { // 左子树
                cur = cur.left;
            } else { // 右子树
                cur = cur.right;
            }
        }
        // 插入新节点
        TreeNode newNode = new TreeNode(num);
        if (prev.val > num) {
            prev.left = newNode;
        } else {
            prev.right = newNode;
        }
    }

    /**
     * 删除节点
     * 注意事项：节点删除后，依然要保持二叉搜索书的特点
     * 
     * @param num
     */
    public void remove(int num) {
        // 如果树为空，提前返回
        if (root == null) {
            return;
        }
        TreeNode cur = root, prev = null;
        // 1、循环查找，定位待删除节点位置
        while (cur != null) {
            // 找到目标节点，退出循环
            if (cur.val == num) {
                break;
            }
            prev = cur;
            if (cur.val > num) { // 左子树找
                cur = cur.left;
            } else { // 右子树找
                cur = cur.right;
            }
        }
        // 2、删除节点
        // 未找到待删除节点
        if (cur == null) {
            System.out.println("未找到待删除节点");
            return;
        }
        // 2.1、待删除节点的度为 0 或 1
        if (cur.left == null || cur.right == null) {
            TreeNode child = cur.left != null ? cur.left : cur.right;
            if (cur != root) {
                if (prev.val > cur.val) { // 挂在父结点的左子树
                    prev.left = child;
                } else { // 挂在右子树
                    prev.right = child;
                }
            } else { // 若删除根节点，重新指定根节点
                root = child;
            }

        }
        // 2.2、度为 2，不可直接删除，找到右子树中的最小节点，来替换删除节点
        else {
            // 中序遍历查找
            TreeNode temp = cur.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            // 递归删除
            remove(temp.val);
            // 替换待删除节点
            cur.val = temp.val;
        }
    }
}

public class binary_search_tree {
    public static void main(String[] args) {
        /* 初始化二叉搜索树 */
        BinarySearchTree bst = new BinarySearchTree();
        // 请注意，不同的插入顺序会生成不同的二叉树，该序列可以生成一个完美二叉树
        int[] nums = { 8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15 };
        for (int num : nums) {
            bst.insert(num);
        }
        System.out.println("\n初始化的二叉树为\n");
        PrintUtil.printTree(bst.getRoot());

        /* 查找节点 */
        TreeNode node = bst.search(7);
        System.out.println("\n查找到的节点对象为 " + node + "，节点值 = " + node.val);

        /* 插入节点 */
        bst.insert(16);
        System.out.println("\n插入节点 16 后，二叉树为\n");
        PrintUtil.printTree(bst.getRoot());

        /* 删除节点 */
        bst.remove(1);
        System.out.println("\n删除节点 1 后，二叉树为\n");
        PrintUtil.printTree(bst.getRoot());
        bst.remove(2);
        System.out.println("\n删除节点 2 后，二叉树为\n");
        PrintUtil.printTree(bst.getRoot());
        bst.remove(4);
        System.out.println("\n删除节点 4 后，二叉树为\n");
        PrintUtil.printTree(bst.getRoot());
    }
}
