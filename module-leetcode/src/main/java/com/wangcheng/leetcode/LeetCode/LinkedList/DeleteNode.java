package com.wangcheng.leetcode.LeetCode.LinkedList;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 * 【题目】
 * 237.删除链表中的节点
 * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，
 * 你将只被给定要求被删除的节点。
 * 【示例】
 * <p>
 * 现有一个链表 -- head = [4,5,1,9]，它可以表示为:
 * 4 -> 5 -> 1 -> 9
 * <p>
 * 示例 1:
 * 输入: head = [4,5,1,9], node = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * <p>
 * 示例 2:
 * 输入: head = [4,5,1,9], node = 1
 * 输出: [4,5,9]
 * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 *
 * @author liwangcheng
 * @date 2020/4/28.
 */
public class DeleteNode {

    static class ListNode {
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
     * 方法：与下一个节点交换
     * 从链表里删除一个节点 node 的最常见方法是修改之前节点的 next 指针，
     * 使其指向之后的节点。
     * 因为，无法访问想要删除的节点之前的节点，始终不能修改该节点的 next 指针。
     * 相反，必须将想要删除的节点的值替换为它后面节点中的值，然后删除它之后的节点。
     * 因为我们知道要删除的节点不是列表的末尾，所以我们可以保证这种方法是可行的。
     * <p>
     * 复杂度分析
     * 时间和空间复杂度都是：O(1)。
     */
    public static void solution(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(5);
        ListNode target = head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(9);
        solution(target);
        LeetCodeUtil.logln("solution(1) = " + head);
    }
}
