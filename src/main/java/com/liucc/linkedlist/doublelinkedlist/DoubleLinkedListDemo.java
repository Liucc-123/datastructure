package com.liucc.linkedlist.doublelinkedlist;

/**
 * 单向链表
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        doubleLinkedList.add(heroNode1); //增加
        doubleLinkedList.add(heroNode2);
        doubleLinkedList.add(heroNode4);
        doubleLinkedList.add(heroNode3);
        doubleLinkedList.list(); //列表查询
        System.out.println("===更新");
        doubleLinkedList.update(new HeroNode(3, "小吴", "智多星"));
        doubleLinkedList.list(); //列表查询
        System.out.println("===删除");
        doubleLinkedList.delete(4);
        doubleLinkedList.list(); //列表查询
    }
}

class DoubleLinkedList {
    // 头节点，仅指向链表首节点，不存储业务数据
    private HeroNode head = new HeroNode(0, "", "");
    /**
     * 展示链表元素
     */
    public void list() {
        // 从链表第一个有效数据开始遍历
        HeroNode temp = head.next;
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
            temp = temp.next;
        }
    }

    // 插入新节点  尾插法
    public void add(HeroNode newNode) {
        // 遍历找到最后一个节点
        HeroNode temp = head; // 临时指针
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next; // 指针后移
        }
        // 新节点与最后一个节点形成双向链表
        temp.next = newNode;
        newNode.prev = temp;
    }

    // 修改
    public void update(HeroNode newNode) {
        HeroNode temp = head.next;
        // 遍历链表
        while (true) {
            // 遍历结束
            if (temp == null) {
                break;
            }
            // 找到目标节点
            if (temp.no == newNode.no) {
                // 更新目标节点的属性
                temp.name = newNode.name;
                temp.nickname = newNode.nickname;
                return;
            }
            // 指针后移
            temp = temp.next;
        }
    }

    // 删除
    public void delete(int nodeNo) {
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                System.out.println("未找到目标节点~");
                break;
            }
            if (temp.no == nodeNo) {
                // 找到啦
                temp.prev.next = temp.next;
                // 如果是最后一个节点，则省略此步骤，防止空指针异常
                if (temp.next != null) {
                    temp.next.prev = temp.prev;
                }
                return;
            }
            // 指针后移
            temp = temp.next;
        }
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;
    public HeroNode prev;

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
