package com.liucc.queue;

import java.util.Scanner;

/**
 * 通过数组实现队列
 */
public class ArrayQueue {
    public static void main(String[] args) {
        Array2Queue queue = new Array2Queue(3);
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
                    queue.addQueue(num);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.println("取出的元素是：" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        queue.headQueue();
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
 * 通过数组模拟队列
 */
class Array2Queue {
    private int maxSize;
    private int front; // 队首指针
    private int tail; // 队尾指针
    private int[] arr; // 数组
    /**
     * 一个队列中要做的几件事：
     * 1、构造器初始化数组
     * 2、往队列中添加元素
     * 3、从队列中取出元素
     * 4、显示当前队列所有元素
     * 5、显示当前队列头部元素
     * 6、队列判满
     * 7、判断队列是否含有元素
     */


    /**
     * 构造器初始化队列
     *
     * @param size 队列大小
     */
    public Array2Queue(int size) {
        maxSize = size;
        arr = new int[maxSize];
        front = -1; // 指向队首前一位置作为初始位置
        tail = -1;  // 指向队首前一位置作为初始位置
    }

    /**
     * 当前队列是否已满
     *
     * @return
     */
    public boolean isFull() {

        return maxSize == tail + 1;
    }

    /**
     * 判断当前队列中是否还有元素
     *
     * @return
     */
    public boolean hasNext() {

        return front != tail;
    }

    /**
     * 往队列中添加元素
     */
    public void addQueue(int item) {
        if (isFull()) {
            System.out.println("队列已满，无法添加元素~~");
            return;
        }
        tail++;
        arr[tail] = item;
    }

    /**
     * 从队列中取出元素
     *
     * @return
     */
    public int getQueue() {
        if (!hasNext()) {
            throw new RuntimeException("队列已空，没有元素可取~~");
        }
        front++;
        int temp = arr[front];
        arr[front] = 0;
        return temp;
    }

    /**
     * 显示当前队列所有元素
     */
    public void showQueue() {
        if (!hasNext()) {
            throw new RuntimeException("队列为空~~");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    /**
     * 显示当前队首元素
     */
    public void headQueue() {
        if (!hasNext()) {
            throw new RuntimeException("队列为空~~");
        }
        System.out.printf("head=%d\n", arr[front + 1]);
    }

}
