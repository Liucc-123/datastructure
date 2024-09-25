package com.liucc.linkedlist.exercise;

/**
 * 面试题3：单链表的反转【腾讯面试题】
 */
public class Exercise2 {
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        System.out.println("反转前~~");
        printList(head);
//        Node reverse = reverse(head); // 递归反转
        Node reverse = reverseList(head);
        System.out.println("反转后~~");
        printList(reverse);
    }

    /**
     * 通过递归来反转链表
     *
     * @param head 源链表的头节点(指向第一个有效数据)
     * @return
     */
    public static Node reverse(Node head) {
        if (head == null || head.next == null) {
            return head; //链表为空或链表只有一个节点，无需反转
        }
        Node temp = head.next;
        Node newHead = reverse(head.next);
        // 在弹栈的过程中，将指针进行反转
        temp.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 在遍历的过程中，将指针进行反转
     *
     * @param current
     * @return
     */
    public static Node reverseList(Node current) {
        Node prev = null;
        Node next = null;
        /**
         * 1、将当前节点的后置节点存储到next（next->node.next）
         * 2、当前节点的next域指向prev
         * 3、prev指向 当前节点
         * 4、current 后移(head->next)
         * return prev
         */
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next; // 指针后移
        }
        return prev;
    }

    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println();
    }


}

class Node {
    public int value;
    public Node next;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
