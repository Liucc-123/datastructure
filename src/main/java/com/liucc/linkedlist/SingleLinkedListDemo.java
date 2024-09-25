package com.liucc.linkedlist;

/**
 * 单向链表
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");

        singleLinkedList.addNode(heroNode1);
        singleLinkedList.addNode(heroNode2);
        singleLinkedList.addNode(heroNode3);
        singleLinkedList.addNode(heroNode4);
        singleLinkedList.list();
//        singleLinkedList.update(new HeroNode(2, "小卢", "玉麒麟"));
//        System.out.println("链表修改后~");
//        singleLinkedList.list();
        singleLinkedList.deleteNode(1);
        singleLinkedList.deleteNode(2);
        singleLinkedList.deleteNode(4);
        singleLinkedList.deleteNode(3);
        System.out.println("链表指定节点删除后~");
        singleLinkedList.list();
    }
}

class SingleLinkedList {

    // 头节点，仅指向链表首节点，不存储业务数据
    private HeroNode head = new HeroNode(0, "", "");

    /**
     * 删除链表中指定编号的节点
     * 1、遍历找到目标节点
     * 2、前置节点的next域指向目标节点的next节点
     * 3、目标节点的next域指向null
     *
     * @param nodeNo
     */
    public void deleteNode(int nodeNo) {
        HeroNode temp = head;
        HeroNode beforeNode = null;
        while (true) {
            if (temp.getNo() == nodeNo) {
                // 找到啦
                beforeNode.setNext(temp.getNext());
//                temp.setNext(null);
                return;
            }
            if (temp.getNext() == null) {
                System.out.println("未找到目标节点~");
                break;
            }
            beforeNode = temp;
            // 指针后移
            temp = temp.getNext();
        }
    }

    /**
     * 根据新节点的编号修改链表中的指定节点
     *
     * @param newNode
     */
    public void update(HeroNode newNode) {
        boolean flag = false;
        HeroNode temp = head;
        // 遍历链表
        while (true) {
            // 遍历结束
            if (temp.getNext() == null) {
                break;
            }
            // 找到目标节点
            if (temp.getNo() == newNode.getNo()) {
                // 更新目标节点的属性
                temp.setName(newNode.getName());
                temp.setNickname(newNode.getNickname());
                return;
            }
            // 指针后移
            temp = temp.getNext();
        }
    }

    // 插入新节点
    // 方式一：尾插法
//    public void addNode(HeroNode heroNode) {
//        // 遍历找到最后一个节点
//        HeroNode temp = head; // 临时指针
//        while (true) {
//            if (temp.getNext() == null) {
//                break;
//            }
//            temp = temp.getNext(); // 指针后移
//        }
//        // 最后节点的next域指向新节点
//        temp.setNext(heroNode);
//    }
    // 考虑英雄排名
    public void addNode(HeroNode newNode) {
        HeroNode temp = head;
        HeroNode beforeNode = null;
        // 1、遍历找到链表中编号比新加节点的编号大的首个节点，记为 afterNode，afterNode的前置节点记为 beforeNode
        while (true) {
            if (temp.getNext() == null) {
                break;
            }
            if (temp.getNo() > newNode.getNo()) {
                // 2、beforeNode 的next域指向新节点，新节点的next域指向 afterNode
                beforeNode.setNext(newNode);
                newNode.setNext(temp);
                return;
            }
            beforeNode = temp;
            // 指针后移
            temp = temp.getNext();
        }
        temp.setNext(newNode);
    }

    /**
     * 展示链表元素
     */
    public void list() {
        // 从链表第一个有效数据开始遍历
        HeroNode temp = head.getNext();
        if (temp == null) {
            System.out.println("链表为空~");
            return;
        }
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            // 指针后移
            temp = temp.getNext();
        }
    }
}

class HeroNode {
    private int no;
    private String name;
    private String nickname;
    private HeroNode next;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    /**
     * 添加节点，默认尾插法
     *
     * @param no
     * @param name
     * @param nickname
     */
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
        this.next = null;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname +
                '}';
    }
}
