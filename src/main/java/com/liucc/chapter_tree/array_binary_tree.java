package com.liucc.chapter_tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.liucc.utils.PrintUtil;
import com.liucc.utils.TreeNode;

/**
 * 基于数组实现的二叉树
 */
class ArrayBinaryTree{
    private List<Integer> tree;
    public ArrayBinaryTree(List<Integer> arr){
        this.tree = arr;
    }
    // 列表容量
    public int size(){
        return tree.size();
    }
    // 获取索引为 i节点的值
    public Integer val(int i){
        // 索引越界，返回 null
        if (i < 0 || i >= size()) {
            return null;
            
        }
        return tree.get(i);
    }
    // 获取索引为 i 左子节点的索引
    public Integer left(int i){
        return 2 * i + 1;
    }
    // 获取索引为 i 右子节点的索引
    public Integer right(int i){
        return 2 * i + 2;
    }
    // 获取索引为 i 父节点的索引
    public Integer parent(int i){
        return (i - 1) / 2;
    }
    // 层序遍历
    public List<Integer> levelOrder(){
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            if (val(i) != null) {
                res.add(val(i));
            }
        }
        return res;
    }

    // 深度优先遍历
    /**
     * 
     * @param i 当前节点索引
     * @param order 遍历方式 pre：前序、in：中序、post：后序
     * @param res 遍历结果
     */
    public void dfs(Integer i, String order, List<Integer> res){
        // 如果为空位，直接返回
        if (val(i) == null) {
            return;
        }
        if ("pre".equals(order)) {
            res.add(val(i));
        }
        dfs(left(i), order, res);
        if ("in".equals(order)) {
            res.add(val(i));
        }
        dfs(right(i), order, res);
        if ("post".equals(order)) {
            res.add(val(i));
        }
    }
    // 前序遍历
    public List<Integer> preOrder(){
        List<Integer> res = new ArrayList<>();
        dfs(0, "pre", res);
        return res;
    }
    // 中序遍历
    public List<Integer> inOrder(){
        List<Integer> res = new ArrayList<>();
        dfs(0, "in", res);
        return res;
    }
    // 后序遍历
    public List<Integer> postOrder(){
        List<Integer> res = new ArrayList<>();
        dfs(0, "post", res);
        return res;
    }
}

public class array_binary_tree {

    public static void main(String[] args) {
        // 初始化二叉树
        // 这里借助了一个从数组直接生成二叉树的函数
        List<Integer> arr = Arrays.asList(1, 2, 3, 4, null, 6, 7, 8, 9, null, null, 12, null, null, 15);

        TreeNode root = TreeNode.listToTree(arr);
        System.out.println("\n初始化二叉树\n");
        System.out.println("二叉树的数组表示：");
        System.out.println(arr);
        System.out.println("二叉树的链表表示：");
        PrintUtil.printTree(root);

        // 数组表示下的二叉树类
        ArrayBinaryTree abt = new ArrayBinaryTree(arr);

        // 访问节点
        int i = 1;
        Integer l = abt.left(i);
        Integer r = abt.right(i);
        Integer p = abt.parent(i);
        System.out.println("\n当前节点的索引为 " + i + " ，值为 " + abt.val(i));
        System.out.println("其左子节点的索引为 " + l + " ，值为 " + (l == null ? "null" : abt.val(l)));
        System.out.println("其右子节点的索引为 " + r + " ，值为 " + (r == null ? "null" : abt.val(r)));
        System.out.println("其父节点的索引为 " + p + " ，值为 " + (p == null ? "null" : abt.val(p)));

        // 遍历树
        List<Integer> res = abt.levelOrder();
        System.out.println("\n层序遍历为：" + res);
        res = abt.preOrder();
        System.out.println("前序遍历为：" + res);
        res = abt.inOrder();
        System.out.println("中序遍历为：" + res);
        res = abt.postOrder();
        System.out.println("后序遍历为：" + res);
    }
}
