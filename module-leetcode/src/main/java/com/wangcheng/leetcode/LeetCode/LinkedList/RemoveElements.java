package com.wangcheng.leetcode.LeetCode.LinkedList;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 203.移除链表元素
 * <p>
 * 删除链表中等于给定值 val 的所有节点。
 * <p>
 *【示例】
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 *
 * @author liwangcheng
 * @date 2020/4/21.
 */
public class RemoveElements {

    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "{\"ListNode\":{"
                    + "\"val\":\"" + val + "\""
                    + ", \"next\":" + next
                    + "}}";
        }
    }

    /**
     * 方法：哨兵节点
     * 当要删除的一个或多个节点位于链表的头部时，事情会变得复杂。
     * 可以通过哨兵节点去解决它，哨兵节点广泛应用于树和链表中，
     * 如伪头、伪尾、标记等，它们是纯功能的，通常不保存任何数据，
     * 其主要目的是使链表标准化，如使链表永不为空、永不无头、简化插入和删除。
     *
     * 复杂度分析
     * 时间复杂度：O(N)，只遍历了一次。
     * 空间复杂度：O(1)。
     */
    public static ListNode solution(ListNode head, int target) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        ListNode prev = sentinel;
        ListNode curr = head;
        while (curr != null) {
            if (curr.val == target) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }
        return sentinel.next;
    }

    public static void main(String[] args) {
        RemoveElements.ListNode head = new RemoveElements.ListNode(1);
        head.next = new RemoveElements.ListNode(2);
        head.next.next = new RemoveElements.ListNode(3);
        head.next.next.next = new RemoveElements.ListNode(4);
        head.next.next.next.next = new RemoveElements.ListNode(5);
        head.next.next.next.next.next = new RemoveElements.ListNode(6);
        head.next.next.next.next.next.next = new RemoveElements.ListNode(7);
        LeetCodeUtil.logln("solution() = " + RemoveElements.solution(head, 3));
    }
}
