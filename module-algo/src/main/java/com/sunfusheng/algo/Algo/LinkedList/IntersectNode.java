package com.sunfusheng.algo.Algo.LinkedList;

import com.sunfusheng.algo.common.model.Node;

/**
 * 【题目】
 * 两个单链表相交的一系列问题
 * <p>
 * 在本题中，单链表可能有环，也可能无环。
 * 给定两个单链表的头节点 head1 和 head2，这两个链表可能相交，也可能不相交。
 * 请实现一个函数，如果两个链表相交，请返回相交的第一个节点；如果不相交，返回null即可。
 * <p>
 * 【要求】
 * 如果链表1的长度为N，链表2的长度为M，时间复杂度请达到O(N+M)，额外空间复杂度请达到O(1)
 * <p>
 * 【解题思路】
 * 本题可以拆分成三个子问题，每个问题都可以作为一道独立的算法题，具体如下。
 * 问题一：如何判断一个链表是否有环，如果有，则返回第一个进入环的节点，没有则返回null。
 * 问题二：如何判断两个无环链表是否相交，相交则返回第一个相交节点，不相交则返回null。
 * 问题三：如何判断两个有环链表是否相交，相交则返回第一个相交节点，不相交则返回null。
 * <p>
 * 注意：如果一个链表有环，另外一个链表无环，它们是不可能相交的，直接返回null。
 *
 * @author sunfusheng
 * @since 2020/4/15
 */
public class IntersectNode {

    /**
     * 问题一：判断一个链表是否有环，如果有，则返回第一个进入环的节点，没有则返回null。
     *
     * @param head
     * @return
     */
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        Node fast = head.next.next;
        Node slow = head.next;
        while (fast != slow) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }

        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    /**
     * 问题二：判断两个无环链表是否相交，相交则返回第一个相交节点，不相交则返回null。
     *
     * @param head1
     * @param head2
     * @return
     */
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1.next != null) {
            cur1 = cur1.next;
            n++;
        }
        while (cur2.next != null) {
            cur2 = cur2.next;
            n--;
        }

        // cur1 和 cur2 为null，都是最后一个节点
        if (cur1 != cur2) {
            return null;
        }

        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n != 0) {
            cur1 = cur1.next;
            n--;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /**
     * 问题三：判断两个有环链表是否相交，相交则返回第一个相交节点，不相交则返回null。
     *
     * @param head1 有环的单链表1
     * @param loop1 有环的单链表1，第一个进入环的节点
     * @param head2 有环的单链表2
     * @param loop2 有环的单链表2，第一个进入环的节点
     * @return
     */
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        if (loop1 == loop2) {
            Node cur1 = head1;
            Node cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                cur1 = cur1.next;
                n++;
            }
            while (cur2 != loop2) {
                cur2 = cur2.next;
                n--;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            Node cur1 = loop1.next;
            while (cur1 != loop1) {
                cur1 = cur1.next;
                if (cur1 == loop2) {
                    return loop1;
                }
            }
            return null;
        }
    }

    /**
     * 将前面三个子问题都考虑进来，解决该题的问题
     *
     * @param head1
     * @param head2
     * @return
     */
    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        } else if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

    public static void main(String[] args) {
        /*
         * 构造一个有环的单链表，结构如下：
         *
         * ● 1 -> 2 -> 3 -> 4
         *             ↑    ↓
         *             6 <- 5
         */
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node3;

        Node firstLoopNode = getLoopNode(node1);
        System.out.println("判断一个链表是否有环，如果有，则返回第一个进入环的节点: " +
                (firstLoopNode != null ? firstLoopNode.value : firstLoopNode));

        /*
         * 构造两个无环且相交的单链表，结构如下：
         *
         * ● 1 -> 2 -> 3 -> 4
         *                  ↓
         *      ● 3 -> 4 -> 5 -> 6 -> 7 ->8
         */
        Node node11 = new Node(1);
        Node node12 = new Node(2);
        Node node13 = new Node(3);
        Node node14 = new Node(4);
        Node node15 = new Node(5);
        Node node16 = new Node(6);
        Node node17 = new Node(7);
        Node node18 = new Node(8);
        node11.next = node12;
        node12.next = node13;
        node13.next = node14;
        node14.next = node15;
        node15.next = node16;
        node16.next = node17;
        node17.next = node18;

        Node node23 = new Node(3);
        Node node24 = new Node(4);
        node23.next = node24;
        node24.next = node15;
        firstLoopNode = noLoop(node11, node23);
        System.out.println("判断两个无环链表是否相交，相交则返回第一个相交节点: " +
                (firstLoopNode != null ? firstLoopNode.value : firstLoopNode));
    }
}
