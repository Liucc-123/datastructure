package com.liucc.chapter_stack_and_queue;

import java.util.Arrays;

/**
 * 基于环形数组实现的队列
 */
class ArrayQueue {
    private int[] nums; // 存储队列队列元素的数组
    private int front; // 队首指针
    private int queSize; // 队列实际长度

    // 构造器
    public ArrayQueue(int capacity) {
        nums = new int[capacity]; // 队列容量
    }

    // 队列容量
    public int capacity() {
        return nums.length;
    }

    // 队列实际长度
    public int size() {
        return queSize;
    }

    // 队列是否为空
    public boolean isEmpty() {
        return queSize == 0;
    }

    // 入队
    public void push(int num) {
        // 队满，不可再添加元素
        if (queSize == capacity()) {
            System.out.println("队列已满，不可再添加元素");
            return;
        }
        // 计算新元素应该存放的位置（rear）
        int rear = (front + queSize) % capacity();
        nums[rear] = num;
        queSize++;
    }

    // 出队
    public int pop() {
        int num = peek();
        front = (front + 1) % capacity();
        queSize--;
        return num;
    }

    // 访问队首元素
    public int peek() {
        if (queSize == 0) {
            throw new IndexOutOfBoundsException();
        }
        return nums[front];
    }
    // 转换为数组
    public int[] toArray(){
        int[] arr = new int[queSize];
        int idx = front;
        for (int i = 0; i < queSize; i++) {
            arr[i] = nums[idx];
            idx = (idx+1)%capacity();
        }
        return arr;
    }
}

public class array_queue {
    public static void main(String[] args) {
        /* 初始化队列 */
        ArrayQueue queue = new ArrayQueue(5);

        /* 元素入队 */
        queue.push(1);
        queue.push(3);
        queue.push(2);
        queue.push(5);
        queue.push(4);
        queue.push(6);
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
