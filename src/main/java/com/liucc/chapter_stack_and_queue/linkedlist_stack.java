package com.liucc.chapter_stack_and_queue;

import java.util.Arrays;

import com.liucc.linkedlist.ListNode;

/** 基于链表实现的栈 */
class LinkedListStack {
    private ListNode head; // 头节点作为栈顶元素
    private int stackSize; // 栈大小

    public LinkedListStack(){
        this.head = null;
    }
    
    /** 获取栈顶元素 */
    public int peek(){
        if (size() == 0) {
            throw new IndexOutOfBoundsException();
        }
        return head.val;
    }
    /** 获取栈的长度 */ 
    public int size(){
        return stackSize;
    }
    /** 栈是否为空 */
    public boolean isEmpty(){
        return size() == 0;
    }
    /** 入栈 */ 
    public void push(int num){
        ListNode node = new ListNode(num);
        node.next = head;
        head = node; // 头节点指向新节点
        stackSize++;
    }
    /** 出栈 */
    public int pop(){
        if (size() == 0) {
            throw new IndexOutOfBoundsException();
        }
        int val = head.val;
        head = head.next; // 指针后移
        stackSize--;
        return val;
    }
    /** 栈转为数组 */
    public int[] toArray(){
        // 1->2->3->4
        int[] array = new int[size()];
        ListNode temp = head;
       for (int i = array.length - 1; i >= 0; i--) {
            array[i] = temp.val;
            temp = temp.next;
       }
       return array;
    }
}

public class linkedlist_stack {
    public static void main(String[] args) {
        /* 初始化栈 */
        LinkedListStack stack = new LinkedListStack();

        /* 元素入栈 */
        stack.push(1);
        stack.push(3);
        stack.push(2);
        stack.push(5);
        stack.push(4);
        System.out.println("栈 stack = " + Arrays.toString(stack.toArray()));

        /* 访问栈顶元素 */
        int peek = stack.peek();
        System.out.println("栈顶元素 peek = " + peek);

        /* 元素出栈 */
        int pop = stack.pop();
        System.out.println("出栈元素 pop = " + pop + "，出栈后 stack = " + Arrays.toString(stack.toArray()));

        /* 获取栈的长度 */
        int size = stack.size();
        System.out.println("栈的长度 size = " + size);

        /* 判断是否为空 */
        boolean isEmpty = stack.isEmpty();
        System.out.println("栈是否为空 = " + isEmpty);
    }
}
