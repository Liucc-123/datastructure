package com.liucc.chapter_stack_and_queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

import com.liucc.linkedlist.ListNode;

/**
 * 基于链表实现的队列
 */
class LinkedListQueue{
    private ListNode front ,rear; // 队首队尾指针
    private int queueSize; // 队列长度

    // 构造器初始化
    public LinkedListQueue(){
        front = null;
        rear = null;
    }
    // 队列是否为空
    public boolean isEmpty(){
        return queueSize == 0;
    }
    // 获取队列长度
    public int size(){
        return queueSize;
    }
    // 访问队首元素
    public int peek(){
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return front.val;
    }
    // 入队
    public void push(int num){
        ListNode node = new ListNode(num);
        // 如果队列为空，队首队尾指针均指向新节点
        if (isEmpty()) {
            front = node;
            rear = node;
        }else{ // 队列不为空，新节点添加到尾节点之后
            rear.next = node;
            rear = node;
        }
        queueSize++;
    }
    // 出队
    public int pop(){
        int peek = peek();
        front = front.next;
        queueSize--;
        return peek;
    }
    // 转化为数组
    public int[] toArray(){
        int[] arrays = new int[size()];
        ListNode temp = front;
        for (int i = 0; i < arrays.length-1; i++) {
            arrays[i] = temp.val;
            temp = temp.next;  // 指针后羿
        }
        return arrays;
    }
}

public class linkedlist_queue {
    public static void main(String[] args) {
        /* 初始化队列 */
        LinkedListQueue queue = new LinkedListQueue();

        /* 元素入队 */
        queue.push(1);
        queue.push(3);
        queue.push(2);
        queue.push(5);
        queue.push(4);
        System.out.println("队列 queue = " + Arrays.toString(queue.toArray()));

        /* 访问队首元素 */
        int peek = queue.peek();
        System.out.println("队首元素 peek = " + peek);

        /* 元素出队 */
        int pop = queue.pop();
        System.out.println("出队元素 pop = " + pop + "，出队后 queue = " + Arrays.toString(queue.toArray()));

        /* 获取队列的长度 */
        int size = queue.size();
        System.out.println("队列长度 size = " + size);

        /* 判断队列是否为空 */
        boolean isEmpty = queue.isEmpty();
        System.out.println("队列是否为空 = " + isEmpty);
    }    
    
}
