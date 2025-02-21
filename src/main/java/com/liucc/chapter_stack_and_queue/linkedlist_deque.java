package com.liucc.chapter_stack_and_queue;

import java.util.Arrays;

import com.liucc.linkedlist.ListNode;

/* 基于链表实现的双向队列 */
class LinkedListDeque{
    private ListNode front, rear; // 队列头节点，尾节点
    private int queSize; // 队列长度

    public LinkedListDeque(){
        front = rear = null;
    }

    // 队列是否为空
    public boolean isEmpty(){
        return queSize == 0;
    }
    // 队列大小
    public int size(){
        return queSize;
    }
    // 获取队首元素
    public int peekFirst(){
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return front.val;
    }
    // 获取队尾元素
    public int peekLast(){
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return rear.val;
    }
    // 入队
    public void push(int num, boolean isFront){
        ListNode node = new ListNode(num);
        // 如果队列为空，front、rear 均指向新节点
        if (isEmpty()) {
            front = node;
            rear = node;
        }else{
            if (isFront) { // 头插法
                front.prev = node;
                node.next = front;
                front = node; // 更新头节点
            }else{ // 尾插法
                rear.next = node;
                node.prev = rear;
                rear = node;
            }
        }
        queSize++;
    }
    // 队首入队
    public void pushFirst(int num){
        push(num, true);
    }
    // 队尾入队
    public void pushLast(int num){
        push(num, false);
    }
    // 出队
    public int pop(boolean isFront){
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        int val = -1;
        if (isFront) {
            // 保存头节点的值
            val = front.val;
            ListNode temp = front.next;
            front.next = null;
            temp.prev = null;
            front = temp; // 更新头节点
        }else{
            val = rear.val;
            ListNode temp = rear.prev;
            rear.prev = null;
            temp.next = null;
            rear = temp; // 更新尾节点
        }
        queSize--;
        return val;
    }
    // 队首出队
    public int popFirst(){
        return pop(true);
    }
    // 队尾出队
    public int popLast(){
        return pop(false);
    }
    // 转为数组
    public int[] toArray(){
        int[] arr = new int[queSize];
        ListNode pointer = front;
        for (int i = 0; i < queSize; i++) {
            arr[i] = pointer.val;
            pointer = pointer.next; // 指针后移
        }
        return arr;
    }
}

public class linkedlist_deque {
    public static void main(String[] args) {
        /* 初始化双向队列 */
        LinkedListDeque deque = new LinkedListDeque();
        deque.pushLast(3);
        deque.pushLast(2);
        deque.pushLast(5);
        System.out.println("双向队列 deque = " + Arrays.toString(deque.toArray()));

        /* 访问元素 */
        int peekFirst = deque.peekFirst();
        System.out.println("队首元素 peekFirst = " + peekFirst);
        int peekLast = deque.peekLast();
        System.out.println("队尾元素 peekLast = " + peekLast);

        /* 元素入队 */
        deque.pushLast(4);
        System.out.println("元素 4 队尾入队后 deque = " + Arrays.toString(deque.toArray()));
        deque.pushFirst(1);
        System.out.println("元素 1 队首入队后 deque = " + Arrays.toString(deque.toArray()));

        /* 元素出队 */
        int popLast = deque.popLast();
        System.out.println("队尾出队元素 = " + popLast + "，队尾出队后 deque = " + Arrays.toString(deque.toArray()));
        int popFirst = deque.popFirst();
        System.out.println("队首出队元素 = " + popFirst + "，队首出队后 deque = " + Arrays.toString(deque.toArray()));

        /* 获取双向队列的长度 */
        int size = deque.size();
        System.out.println("双向队列长度 size = " + size);

        /* 判断双向队列是否为空 */
        boolean isEmpty = deque.isEmpty();
        System.out.println("双向队列是否为空 = " + isEmpty);
    }
}
