package com.wangcheng.leetcode.LeetCode.LinkedList;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 * 【题目】
 * 21. 合并两个有序链表
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 【示例】
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * @author liwangcheng
 * @date 2020/3/19.
 */
public class MergeTwoLists {

    private static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
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
     * 方法一：迭代
     * <p>
     * 复杂度分析
     * 【时间复杂度】：O(n + m) 。因为每次循环迭代中，l1 和 l2 只有一个元素会被放进合并链表中，
     * while 循环的次数等于两个链表的总长度。所有其他工作都是常数级别的，所以总的时间复杂度是线性的。
     * <p>
     * 【空间复杂度】：O(1) 。迭代的过程只会产生几个指针，所以它所需要的空间是常数级别的。
     */
    public static ListNode solution1(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(-1);
        ListNode prev = preHead;
        while (null != l1 && null != l2) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = null == l1 ? l2 : l1;
        return preHead.next;
    }

    /**
     * 方法二：递归
     * <p>
     * 复杂度分析
     * 【时间复杂度】：O(n + m)。因为每次调用递归都会去掉 l1 或者 l2 的头元素（直到至少有一个链表为空），
     * 函数 solution2 中只会遍历每个元素一次。所以，时间复杂度与合并后的链表长度为线性关系。
     * 【空间复杂度】：O(n + m)。调用 solution2 退出时 l1 和 l2 中每个元素都一定已经被遍历过了，
     * 所以 n + m 个栈帧会消耗 O(n+m) 的空间。
     */
    public static ListNode solution2(ListNode l1, ListNode l2) {
        if (null == l1) {
            return l2;
        } else if (null == l2) {
            return l1;
        } else if (l1.val <= l2.val) {
            l1.next = solution2(l1.next, l2);
            return l1;
        } else {
            l2.next = solution2(l1, l2.next);
            return l2;
        }
    }

    private static ListNode l1 = new ListNode(1);
    private static ListNode l2 = new ListNode(1);

    private static void init() {
        l1.next = new ListNode(2);
        l2.next = new ListNode(1);
        l1.next.next = new ListNode(3);
        l2.next.next = new ListNode(2);
        l1.next.next.next = new ListNode(4);
        l2.next.next.next = new ListNode(5);
    }

    public static void main(String[] args) {
        init();
        LeetCodeUtil.logln("solution1([1,2,3,4], [1,1,2,5]) = " + solution1(l1, l2));
        LeetCodeUtil.logln("solution2([1,2,3,4], [1,1,2,5]) = " + solution2(l1, l2));
    }
}
