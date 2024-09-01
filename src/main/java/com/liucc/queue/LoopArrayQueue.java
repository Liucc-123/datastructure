package com.liucc.queue;

import java.util.Scanner;

/**
 * 数组方式实现循环队列
 */
public class LoopArrayQueue {

    public static void main(String[] args) {
        LoopQueue queue = new LoopQueue(4);
        Scanner scanner = new Scanner(System.in);
        char key = ' ';
        boolean loop = true;
        while (loop) {
            System.out.println("========================");
            System.out.println("s(show)：显示当前队列所有元素");
            System.out.println("e(exit)：退出程序~");
            System.out.println("a(add)：往队列中添加元素");
            System.out.println("g(get)：从队列中取出元素");
            System.out.println("h(head)：显示当前队列头部元素");
            System.out.printf("请输入您想操作的字符：");

            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    try {
                        queue.showQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    System.out.printf("请输入您想添加的数字：");
                    int num = scanner.nextInt();
                    queue.deQueue(num);
                    break;
                case 'g':
                    try {
                        int res = queue.enQueue();
                        System.out.println("取出的元素是：" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        queue.head();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    loop = false;
                    scanner.close(); //关闭输入流
                    break;
            }
        }
        System.out.println("程序已退出~~");
    }
}

/**
 * 循环队列
 * 队列为空条件：front==rear
 * 队列满条件：（rear+1）% maxSize = front 因为队列需要预留一个位置给队尾指针，所以队列的容量是比数组的长度小1的
 * 指针后移：front = （front+1）% maxSize
 */
class LoopQueue {
    private int front; // 队头
    private int rear; // 队尾
    private int maxSize; // 队列容量
    private int[] arr; // 数组

    /**
     * 队列初始化
     */
    public LoopQueue(int size) {
        this.maxSize = size;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    /**
     * 判断队列是否已满
     *
     * @return
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 入队
     *
     * @param item 入队元素
     */
    public void deQueue(int item) {
        if (isFull()) {
            System.out.println("队列已满，不能再添加元素~");
            return;
        }
        arr[rear] = item;
        // rear++; 不可以这么简单的++
        rear = (rear + 1) % maxSize;
    }

    /**
     * 出队
     *
     * @return
     */
    public int enQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能再取元素~");
        }
        int frontItem = arr[front];
        front = (front + 1) % maxSize;
        return frontItem;
    }

    /**
     * 展示当前队列
     */
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = front; i != rear; i = (i + 1) % maxSize) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    /**
     * 展示队首元素
     */
    public void head() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        System.out.println("队首元素：" + arr[front]);
    }
}
