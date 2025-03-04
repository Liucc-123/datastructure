package com.liucc.chapter_tree;

import com.liucc.utils.PrintUtil;
import com.liucc.utils.TreeNode;

/**
 * 
 */
class AVLTree {
    TreeNode root; // 根节点

    /**
     * 获取节点高度
     * 空节点高度为 -1，叶节点高度为 0
     * 
     * @param node
     * @return
     */
    public int height(TreeNode node) {
        return node == null ? -1 : node.height;
    }

    // 更新节点高度
    public void updateHeight(TreeNode node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * 获取平衡因子
     * 规定：空节点的平衡因子为 0、平衡因子 = 左子树高度 - 右子树高度
     * 
     * @param node
     * @return
     */
    public int balanceFactor(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    /**
     * 右旋操作(左偏树)
     * 
     * @param node 失衡节点
     * @return
     */
    public TreeNode rightRotate(TreeNode node) {
        TreeNode child = node.left;
        TreeNode grandChild = child.right;
        // 以 child 节点为原点进行旋转
        child.right = node;
        node.left = grandChild;
        // 更新节点的高度
        updateHeight(node);
        updateHeight(child);
        // 返回旋转后子树的根节点
        return child;
    }

    // 左旋操作(右偏树)
    public TreeNode leftRotate(TreeNode node) {
        TreeNode child = node.right;
        TreeNode grandChild = child.left;
        // 以 child 节点为原点进行旋转
        child.left = node;
        node.right = grandChild;
        // 更新节点的高度
        updateHeight(node);
        updateHeight(child);
        // 返回旋转后子树的根节点
        return child;
    }

    // 执行旋转操作，使该节点的子树恢复平衡
    public TreeNode rotate(TreeNode node) {
        int balanceFactor = balanceFactor(node);
        // 左偏树
        if (balanceFactor > 1) {
            if (balanceFactor(node.left) >= 0) {
                // 右旋
                return rightRotate(node);
            } else {
                // 先左旋
                node.left = leftRotate(node.left);
                // 后右旋
                return rightRotate(node);
            }
        } else if (balanceFactor < -1) { // 右偏树
            if (balanceFactor(node.right) <= 0) {
                // 左旋
                return leftRotate(node);
            } else {
                // 先右旋后左旋
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        // 平衡树，无需旋转，直接返回
        return node;
    }

    /**
     * 插入节点
     * 
     * @param val 新值
     */
    public void insert(int val) {
        root = insertHelper(root, val);
    }

    // 删除节点
    public void remove(int val) {
        root = removeHelper(root, val);
    }
    // 查找节点

    // 递归删除节点（辅助方法）
    private TreeNode removeHelper(TreeNode node, int val) {
        if (node == null) {
            return null;
        }
        // 1、查找节点，并删除
        if (node.val > val)
            node.left = removeHelper(node.left, val);
        else if (node.val < val)
            node.right = removeHelper(node.right, val);
        else {
            // 删除节点
            if (node.left == null || node.right == null) { // 度为 0 或 1
                TreeNode child = node.left != null ? node.left : node.right;
                // 度为 0，直接删除node，并返回
                if (child == null) {
                    return null;
                } else { // 度为 1，直接删除 node
                    node = child;
                }
            }else{ // 度为 2，查找该节点右子树中最小节点并删除，最小节点值替换 node 值
                TreeNode temp = node.right;
                while (temp.left != null) {
                    temp = temp.left;
                }
                node.right = removeHelper(node.right, temp.val);
                node.val = temp.val;
            }

        }
        // 2、更新节点高度
        updateHeight(node);
        // 3、执行旋转操作，使子树恢复平衡
        node = rotate(node);
        return node;
    }

    public TreeNode search(int val){
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val < val) {
                cur = cur.right;
            }else if (cur.val > val) {
                cur = cur.left;
            }else
                break;
        }
        return cur;
    }

    // 递归插入节点（辅助方法）
    private TreeNode insertHelper(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val);
        }
        // 查找插入位置并插入节点
        if (node.val > val)
            node.left = insertHelper(node.left, val);
        else if (node.val < val)
            node.right = insertHelper(node.right, val);
        else
            // 重复节点，返回
            return node;
        // 更新节点高度
        updateHeight(node);
        // 执行旋转操作，使该子树重新恢复平衡
        node = rotate(node);
        // 返回子树的根节点
        return node;
    }
}

public class avl_tree {

    static void testInsert(AVLTree tree, int val) {
        tree.insert(val);
        System.out.println("\n插入节点 " + val + " 后，AVL 树为");
        PrintUtil.printTree(tree.root);
    }

    static void testRemove(AVLTree tree, int val) {
        tree.remove(val);
        System.out.println("\n删除节点 " + val + " 后，AVL 树为");
        PrintUtil.printTree(tree.root);
    }

    public static void main(String[] args) {
        /* 初始化空 AVL 树 */
        AVLTree avlTree = new AVLTree();

        /* 插入节点 */
        // 请关注插入节点后，AVL 树是如何保持平衡的
        testInsert(avlTree, 1);
        testInsert(avlTree, 2);
        testInsert(avlTree, 3);
        testInsert(avlTree, 4);
        testInsert(avlTree, 5);
        testInsert(avlTree, 8);
        testInsert(avlTree, 7);
        testInsert(avlTree, 9);
        testInsert(avlTree, 10);
        testInsert(avlTree, 6);

        /* 插入重复节点 */
        testInsert(avlTree, 7);

        /* 删除节点 */
        // 请关注删除节点后，AVL 树是如何保持平衡的
        testRemove(avlTree, 8); // 删除度为 0 的节点
        testRemove(avlTree, 5); // 删除度为 1 的节点
        testRemove(avlTree, 4); // 删除度为 2 的节点

        /* 查询节点 */
        TreeNode node = avlTree.search(7);
        System.out.println("\n查找到的节点对象为 " + node + "，节点值 = " + node.val);
    }
}
