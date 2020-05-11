package com.wangcheng.leetcode.LeetCode.LinkedList;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/*
 *【题目】
 * 160.相交链表
 * 编写一个程序，找到两个单链表相交的起始节点。
 * 在节点 c1 开始相交。
 *【示例】
 * 如下面的两个链表：
 *      4 -> 1 ---
 *                |
 * 5 -> 0 -> 1 -> 8 -> 4 -> 5
 * 输入：
 *  intersectVal = 8,
 *  listA = [4,1,8,4,5],
 *  listB = [5,0,1,8,4,5],
 *  skipA = 2,
 *  skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
 * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 *
 * @author liwangcheng
 * @date 2020/4/7.
 */
public class GetIntersectionNode {

    static class ListNode {
        int data;
        ListNode next;

        ListNode(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "{\"ListNode\":{"
                    + "\"data\":\"" + data + "\""
                    + ", \"next\":" + next
                    + "}}";
        }
    }

    public static ListNode solution1(ListNode headA, ListNode headB) {
        if (null == headA || null == headA.next || null == headB || null == headB.next) {
            return null;
        }
        int n = 0;
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA.next != null) {
            pA = pA.next;
            n++;
        }
        while (pB.next != null) {
            pB = pB.next;
            n--;
        }
        if (pA != pB) {
            return null;
        }

        pA = n > 0 ? headA : headB;
        pB = pA == headA ? headB : headA;
        n = Math.abs(n);
        while (n != 0) {
            n--;
            pA = pA.next;
        }
        while (pA != null && pB != null && pA != pB) {
            pA = pA.next;
            pB = pB.next;
        }
        return pA;
    }

    public static ListNode solution2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    public static void main(String[] args) {
        ListNode listA = new ListNode(4);
        ListNode comm = new ListNode(1);
        comm.next = new ListNode(8);
        comm.next.next = new ListNode(4);
        comm.next.next.next = new ListNode(5);
        listA.next = comm;
        ListNode listB = new ListNode(4);
        listB.next = new ListNode(0);
        listB.next.next = comm;
        ListNode listC = new ListNode(3);
        listC.next = new ListNode(9);

        LeetCodeUtil.logln("solution1 = " + solution1(listA, listB));
        LeetCodeUtil.logln("solution2 = " + solution2(listA, listB));
        LeetCodeUtil.logln("solution1 = " + solution1(listA, listC));
        LeetCodeUtil.logln("solution2 = " + solution2(listA, listC));
        LeetCodeUtil.logln("solution1 = " + solution1(listB, listC));
        LeetCodeUtil.logln("solution2 = " + solution2(listB, listC));
    }
}
