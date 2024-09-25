package com.liucc.linkedlist.circlesingleLinkedList;

/**
 * 环形链表：模拟丢手帕小游戏
 */
public class CircleSingleLinkedListDemo {

    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.initCircleLinkedList(5);
//        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.diuShouJuan(1, 2, 5);
    }
}

/**
 * 环形链表
 */
class CircleSingleLinkedList {

    private Boy first = null; // 指向环形链表中第一个节点，环形链表加入第一个节点时，将first初始化

    /**
     * 创建指定数量节点的环形链表
     *
     * @param nums 节点个数，最少1个
     */
    public void initCircleLinkedList(int nums) {
        if (nums < 1) {
            System.out.println("nums的数量至少为1");
            return;
        }
        // 循环创建链表
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy newNode = new Boy(i);
            if (i == 1) {
                // 创建第一个节点时，让curBoy、first指针指向该节点，并形成环形；
                first = newNode;
                first.next = first; // 单节点构成一个环
                curBoy = first;
            } else {
                curBoy.next = newNode;
                newNode.next = first; //形成一个环
                curBoy = newNode; //移向新节点
            }

        }
    }

    /**
     * 展示环形链表元素
     */
    public void showBoy() {
        if (first == null) {
            System.out.println("链表为空~");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.println(curBoy);
            if (curBoy.next == first) {
                break; //遍历完成
            }
            curBoy = curBoy.next;

        }
    }


    /**
     * 丢手绢游戏
     * 条件准备
     * 1、定义辅助指针helper，始终指向环形链表的最后一个节点，与first相邻；
     * 2、定位到开始报数小孩的位置，first、helper向前移动 k-1下
     * 开始报数
     * first、helper向前移动m-1下
     * 小孩出圈
     * first = first.next
     * helper.next = first.next
     * 游戏结束
     * 当first == helper时，说明圈中只剩一个小孩，游戏结束
     *
     * @param startNo   从 startNo开始报数
     * @param count     数 count下
     * @param totalBoys 游戏开始有 totalBoys个小孩
     */
    public void diuShouJuan(int startNo, int count, int totalBoys) {
        // 参数校验
        if (startNo < 1 || totalBoys < 1 || count < 1 || startNo > totalBoys || first == null) {
            System.out.println("您输入参数不合法，请重新输入~~~");
            return;
        }
        // 0、 条件准备
        Boy helper = first;
        while (true) {
            // 找到环形链表最后一个节点，helper指向该节点
            if (helper.next == first) {
                break;
            }
            helper = helper.next; //指针后移
        }
        // 定位到开始报数小孩的位置
        for (int i = 0; i < startNo - 1; i++) {
            first = first.next;
            helper = helper.next;
        }
        while (true) {
            // 1、开始报数
            // first、helper向前移动m-1下
            for (int i = 0; i < count - 1; i++) {
                first = first.next;
                helper = helper.next;
            }
            // 小孩出圈
            System.out.printf("编号为%d 的小孩出圈\n", first.no);
            first = first.next;
            helper.next = first;
            // 游戏结束
            if (first == helper) {
                break;
            }
        }
        System.out.printf("圈中最后一名的小孩编号为%d \n", first.no);

    }
}

class Boy {
    public int no;
    public Boy next;

    public Boy(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
