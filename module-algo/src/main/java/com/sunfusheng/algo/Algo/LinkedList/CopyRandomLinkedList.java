package com.sunfusheng.algo.Algo.LinkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * 【题目】
 * 复制含有随机指针节点的链表
 * <p>
 * 一种特殊的链表节点类描述如{@link CopyRandomLinkedList.Node}.
 * <p>
 * Node类中的value是节点值，next指针和正常单链表中next指针的意义一样，都指向下一个节点，
 * rand指针是Node类中新增的指针，这个指针可能指向链表中的任意一个节点，也可能指向null。
 * 给定一个由Node节点类型组成的无环单链表的头节点head，
 * 请实现一个函数完成这个链表中所有结构的复制，并返回复制的新链表的头节点。
 * <p>
 * 例如：链表1-＞2-＞3-＞null
 * 假设1的rand指针指向3，2的rand指针指向null，3的rand指针指向1。
 * 复制后的链表应该也是这种结构，比如：1′-＞2′-＞3′-＞null，
 * 1′的rand指针指向3′，2′的rand指针指向null，3′的rand指针指向1′，最后返回1′。
 * <p>
 * 【进阶问题】
 * 不使用额外的数据结构，只用有限几个变量，且在时间复杂度为O(N)内完成原问题要实现的函数。
 *
 * @author sunfusheng
 * @since 2020/4/11
 */
public class CopyRandomLinkedList {

    static class Node {
        public int value;
        public Node next;
        public Node random;

        Node(int value) {
            this.value = value;
        }
    }

    /**
     * 原问题解法
     * <p>
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     *
     * @param head
     * @return
     */
    public static Node copyRandomLinkedList1(Node head) {
        if (head == null) {
            return null;
        }

        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    /**
     * 进阶问题解法
     * 把原链表的每一个节点的复制节点放到原节点的后面，把它们连起来，
     * 设置复制节点的random指针，最后再拆分成两个链表，返回复制链表的头节点
     * <p>
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     *
     * @param head
     * @return
     */
    public static Node copyRandomLinkedList2(Node head) {
        if (head == null) {
            return null;
        }

        Node cur = head;
        Node next = null;
        while (cur != null) {
            // 复制连接每一个节点
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }

        cur = head;
        while (cur != null) {
            // 设置复制节点的random指针
            next = cur.next.next;
            cur.next.random = cur.random != null ? cur.random.next : null;
            cur = next;
        }

        Node res = head.next;
        Node curCopy = null;
        cur = head;
        while (cur != null) {
            // 拆分
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.next = next != null ? next.next : null;
            cur.next = next;
            cur = next;
        }
        return res;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.next = node2;
        node1.random = node3;
        node2.next = node3;
        node2.random = null;
        node3.next = null;
        node3.random = node1;

        // 请通过debug查看结果
        Node head = copyRandomLinkedList1(node1);
        System.out.println(head.toString());

        // 请通过debug查看结果
        head = copyRandomLinkedList2(node1);
        System.out.println(head.toString());
    }
}
