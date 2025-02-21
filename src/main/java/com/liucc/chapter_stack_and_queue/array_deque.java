package com.liucc.chapter_stack_and_queue;

import java.util.Arrays;

/* 基于环形数组实现的双向队列 */
class ArrayDeque {
    private int[] nums; // 用于存储双向队列元素
    private int front; // 计算队首元素索引
    private int queSize; // 队列长度

    // 构造器
    public ArrayDeque(int capacity) {
        nums = new int[capacity];
    }

    // 队列容量
    public int capacity() {
        return nums.length;
    }
    public int size(){
        return queSize;
    }

    // 队列是否为空
    public boolean isEmpty() {
        return queSize == 0;
    }

    // 计算环形数组的索引
    public int index(int i) {
        return (i + capacity()) % capacity();
    }

    // 访问队首元素
    public int peekFirst() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return nums[front];
    }

    // 访问队尾元素
    public int peekLast(){
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        // 计算队尾元素位置
        int rearIdx = index(front+queSize-1);
        return nums[rearIdx];
    }
    // 队首入队
    public void pushFirst(int num){
        if (queSize == capacity()) {
            System.out.println("双向队列已满");
            return;
        }
        // 计算新头指针索引位置
         front = index(front-1);
        nums[front] = num;
        queSize++;
    }
    // 队尾入队
    public void pushLast(int num){
        if (queSize == capacity()) {
            System.out.println("双向队列已满");
            return;
        }
        // 计算尾指针索引位置
        int idx = index(front+queSize);
        nums[idx] = num;
        queSize++;
    }
    // 队首出队
    public int popFirst(){
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        int val = nums[front];
        front = index(front+1);
        queSize--;
        return val;
    }
    // 队尾出队
    public int popLast(){
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        int val = peekLast();
        queSize--;
        return val;
    }
    // 转换为数组
    public int[] toArray(){
        int[] arr = new int[queSize];
        int idx = front;
        for (int i = 0; i < queSize; i++) {
            arr[i] = nums[idx];
            idx = index(idx+1);
        }
        return arr;
    }
}

public class array_deque {
    public static void main(String[] args) {
         /* 初始化双向队列 */
         ArrayDeque deque = new ArrayDeque(10);
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
