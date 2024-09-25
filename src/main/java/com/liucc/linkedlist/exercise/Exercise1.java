package com.liucc.linkedlist.exercise;

import java.util.Stack;

/**
 * 面试题1：求单链表中有效节点的个数
 * 面试题2：查找单链表中倒数第k个节点【新浪面试题】
 * 面试题3：单链表的反转【腾讯面试题】
 * 面试题4：逆序打印单链表【百度面试题】
 */
public class Exercise1 {
    public static void main(String[] args) {
//        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(new IntNode(1, 1));
//        singleLinkedList.add(new IntNode(2, 2));
//        singleLinkedList.add(new IntNode(3, 3));
//        int count = singleLinkedList.count();
//        System.out.println("链表中元素的个数是：" + count);
//        int k = 2;
//        IntNode nodeFromTail = singleLinkedList.getNodeFromTail(k);
//        if (nodeFromTail != null) {
//            System.out.println("倒数第k个节点是：" + nodeFromTail);
//        }
        // 反转方式一：缺点：创建对象比较多
//        SingleLinkedList reversedLinkedList = reverseLinkedList(singleLinkedList);
//        reversedLinkedList.list();
//        System.out.println("反转前~");
//        singleLinkedList.list();
//        IntNode recursion = reverseByRecursion(singleLinkedList.getHead());
//        System.out.println("反转后~");
//        singleLinkedList.list(recursion);

        // == 逆序打印目标单链表 ==
//        System.out.println("逆序打印目标单链表，不会改变原有单链表的数据结构~~");
//        reversePrint(singleLinkedList.getHead());

        // === 合并两个有序的单链表，合并之后的单链表依然有序
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        singleLinkedList1.addWithNo(new IntNode(1, 1));
        singleLinkedList1.addWithNo(new IntNode(3, 3));
        singleLinkedList1.addWithNo(new IntNode(5, 5));
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.addWithNo(new IntNode(2, 2));
        singleLinkedList2.addWithNo(new IntNode(4, 4));
        singleLinkedList2.addWithNo(new IntNode(6, 6));
        SingleLinkedList mergeSingLinkedList = mergeSingLinkedList(singleLinkedList1.getHead(), singleLinkedList2.getHead());
        System.out.println("===合并之后的有序单链表~~");
        mergeSingLinkedList.list();

    }

    /**
     * 合并两个有序的单链表，合并之后的单链表依然有序
     *
     * @param head1 单链表1的头节点
     * @param head2 单链表2的头节点
     * @return 合并之后的有序单链表
     */
    public static SingleLinkedList mergeSingLinkedList(IntNode head1, IntNode head2) {
        SingleLinkedList mergeSingLinkedList = new SingleLinkedList();
        // 遍历第一个单链表
        while (head1.getNext() != null) {
            // 拷贝节点
            IntNode newNode = new IntNode(head1.getNext().getData(),head1.getNext().getNo());
            mergeSingLinkedList.addWithNo(newNode);
            head1 = head1.getNext();
        }
        // 遍历第二个单链表
        while (head2.getNext() != null) {
            // 拷贝节点
            IntNode newNode = new IntNode(head2.getNext().getData(),head2.getNext().getNo());
            mergeSingLinkedList.addWithNo(newNode);
            head2 = head2.getNext();
        }
        return mergeSingLinkedList;
    }

    /**
     * 逆序打印目标单链表
     * 利用栈 数据结构的特点，先入后出，实现单链表的逆序打印
     *
     * @param head
     */
    public static void reversePrint(IntNode head) {
        if (head.getNext() == null) {
            return; //空链表，直接返回
        }
        Stack<IntNode> stack = new Stack<>();
        while (head.getNext() != null) {
            stack.push(head.getNext());
            head = head.getNext(); // 指针后移
        }
        // 逆序打印
        while (stack.size() > 0) {
            System.out.print(stack.pop() + "\t");
        }

    }

    /**
     * 反转当前链表的所有元素  【不建议】
     *
     * @param singleLinkedList 源单链表
     * @return 反转后的单链表
     */
    public static SingleLinkedList reverseLinkedList(SingleLinkedList singleLinkedList) {
        int count = singleLinkedList.count();
        if (count <= 0) {
            System.out.println("源链表为空");
            return null;
        }
        SingleLinkedList targetLinkedList = new SingleLinkedList();
        for (int i = 1; i <= count; i++) {
            IntNode nodeFromTail = singleLinkedList.getNodeFromTail(i);
            if (nodeFromTail == null) {
                System.out.println("节点不存在");
                return null;
            }
            // 拷贝一个新的Node
            IntNode newNode = new IntNode(nodeFromTail.getData(), nodeFromTail.getNo());
            targetLinkedList.add(newNode);
        }
        return targetLinkedList;
    }

    /**
     * 通过递归，反转链表
     *
     * @param head 源链表的头结点
     * @return 反转后链表的头节点
     */
    public static IntNode reverseByRecursion(IntNode head) {
        // 最小子问题
        if (head == null || head.getNext() == null) {
            return head;
        }
        IntNode temp = head.getNext();
        IntNode newHead = reverseByRecursion(head.getNext());
        // 在弹栈的过程中，将指针进行反转
        temp.setNext(head);
        head.setNext(null);
        return newHead;
    }
}

class SingleLinkedList {
    private IntNode head = new IntNode(0, 0); //头节点不存储有效数据，仅代表指针

    public IntNode getHead() {
        return head;
    }

    /**
     * 根据编号添加元素
     * 思路：1、找到链表中比新节点编号大的的首个元素 targetNode
     * 2、存储 targetNode 的前置节点 beforeNode
     * 3、newNode.next = targetNode
     * 4、beforeNode.next = newNode
     *
     * @param newNode
     */
    public void addWithNo(IntNode newNode) {
        IntNode temp = head;
        // 1、找到链表中比新节点编号大的的首个元素 targetNode
        while (true) {
            if (temp.getNext() == null) {
                break;
            }
            if (temp.getNext().getNo() > newNode.getNo()) {
                newNode.setNext(temp.getNext());
                break;
            }
            temp = temp.getNext(); // 指针后移
        }
        temp.setNext(newNode);
    }

    /**
     * 添加新节点 【Normal】
     *
     * @param node
     */
    public void add(IntNode node) {
        IntNode temp = head;
        while (true) {
            // 找到尾节点
            if (temp.getNext() == null) {
                temp.setNext(node);
                break;
            }
            temp = temp.getNext(); // 指针后移
        }
    }

    /**
     * 根据链表头节点打印链表信息
     *
     * @param head
     */
    public void list(IntNode head) {
        IntNode temp = head;
        while (temp != null) {
            System.out.print(temp + "\t");
            temp = temp.getNext();
        }
        System.out.println();
    }

    // 面试题1：统计链表有效节点个数
    public int count() {
        IntNode temp = head;
        Integer count = 0;
        while (true) {
            // 找到尾节点
            if (temp.getNext() == null) {
                break;
            }
            temp = temp.getNext(); // 指针后移
            count++; // 计数器++
        }
        return count;
    }

    /**
     * 展示链表所有元素
     */
    public void list() {
        // 从链表中第一个有效数据遍历
        IntNode temp = head.getNext();
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.getNext();
        }
    }

    /**
     * 面试题2：查找单链表中倒数第k个节点【新浪面试题】
     * 实现思路：找倒数第k个节点 等同于查找 no=(count-K+1) 的节点【no从1开始】
     *
     * @param k
     * @return
     */
    public IntNode getNodeFromTail(int k) {
        int no = count() - k + 1;
        if (no <= 0) {
            System.out.println("未找到倒数第" + k + "个节点");
            return null;
        }
        // 查找链表中编号为no的节点
        IntNode temp = head;
        while (true) {
            // 找到
            if (temp.getNo() == no) {
                return temp;
            }
            if (temp.getNext() == null) {
                System.out.println("未找到倒数第" + k + "个节点");
                break; //遍历结束，仍未找到目标节点
            }

            temp = temp.getNext();
        }
        return null;
    }
}

class IntNode {
    private Integer data;
    private Integer no; // 从1开始
    private IntNode next; // next域

    public IntNode(Integer data, Integer no) {
        this.data = data;
        this.no = no;
        this.next = null;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public IntNode getNext() {
        return next;
    }

    public void setNext(IntNode next) {
        this.next = next;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "IntNode{" +
                "data=" + data +
                ", no=" + no +
                '}';
    }
}
