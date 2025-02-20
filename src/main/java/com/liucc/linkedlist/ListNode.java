package com.liucc.linkedlist;

/** 双向链表节点 */
public class ListNode {
    public int val;
    public ListNode next; // 后继结点
    public ListNode prev; // 前驱节点

    public ListNode(int val) {
        this.val = val;
        next = prev = null;
    }
}
