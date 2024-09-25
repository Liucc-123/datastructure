package com.liucc.stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * 使用数组模拟栈数据结构
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        // 创建菜单
        Scanner scanner = new Scanner(System.in);
        String key = "";
        boolean loop = true;
        while (loop){
            System.out.println("show: 表示展示栈内所有元素");
            System.out.println("push: 表示入栈操作");
            System.out.println("pop: 表示出栈操作");
            System.out.println("exit: 表示退出程序");
            System.out.printf("请输入你要操作的字符：");
            key = scanner.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.printf("请输入你要添加的数据：");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int pop = stack.pop();
                        System.out.printf("出栈元素是%d\n", pop);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    loop = false;
                    break;
            }
        }
        System.out.println("程序已退出~");
    }
}

/**
 * 数组模拟栈
 * maxSize：表示栈最大容量
 * top：栈顶
 * value：栈内元素
 */
class ArrayStack{
    private int maxSize;
    private int top = -1; //栈顶初始位置
    private int[] stack; //栈

    // 构造器
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    // 栈满
    public boolean isFull(){
        return top == maxSize -1;
    }
    // 栈空
    public boolean isEmpty(){
        return top == -1;
    }
    // 入栈
    public void push(int value){
        if (isFull()) {
            System.out.println("栈满，无法添加新元素~");
            return;
        }
        top++;
        stack[top] = value;
    }
    // 出栈
    public int pop(){
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有元素~");
        }
        int value = stack[top];
        top--;
        return value;
    }
    // 遍历栈(从栈顶开始遍历)
    public void list(){
        if(isEmpty()){
            System.out.println("栈空，没有元素~");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}
