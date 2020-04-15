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
     * 判断一个链表是否有环，如果有，则返回第一个进入环的节点
     *
     * @param head
     * @return
     */
    public static Node getLoopNode(Node head) {
        if (head == null) {
            return null;
        }

        Node fast = head;
        Node slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }

        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
