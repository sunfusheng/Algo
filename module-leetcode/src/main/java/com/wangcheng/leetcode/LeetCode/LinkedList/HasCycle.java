package com.wangcheng.leetcode.LeetCode.LinkedList;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.HashSet;
import java.util.Set;

/**
 *【题目】
 * 141.环形链表
 * 给定一个链表，判断链表中是否有环。
 * <p>
 * 为了表示给定链表中的环，使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。
 * <p>
 *【示例】  
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * -> 3 -> 2 -> 0 -> -4
 *         ^          |
 *         |__________|
 *
 * @author liwangcheng
 * @date 2020/4/7.
 */
public class HasCycle {

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

    /**
     * 方法一：哈希表
     * 思路
     * 可以通过检查一个结点此前是否被访问过来判断链表是否为环形链表。
     * 常用的方法是使用哈希表。
     * <p>
     * 算法
     * 遍历所有结点并在哈希表中存储每个结点的引用（或内存地址）。
     * 如果当前结点为空结点 null（即已检测到链表尾部的下一个结点），
     * 那么已经遍历完整个链表，并且该链表不是环形链表。
     * 如果当前结点的引用已经存在于哈希表中，那么返回 true（即该链表为环形链表）。
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n)，对于含有 n 个元素的链表，访问每个元素最多一次。
     * 添加一个结点到哈希表中只需要花费 O(1) 的时间。
     * 空间复杂度：O(n)，空间取决于添加到哈希表中的元素数目，最多可以添加 n 个元素。
     */
    public static boolean solution1(ListNode head) {
        if (null == head) {
            return false;
        }
        Set<ListNode> set = new HashSet<>();
        while (null != head) {
            if (set.contains(head)) {
                return true;
            } else {
                set.add(head);
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 方法二：双指针
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n)，将 n 设为链表中结点的总数。
     * 空间复杂度：O(1)，只使用了慢指针和快指针两个结点，所以空间复杂度为 O(1)。
     */
    public static boolean solution2(ListNode head) {
        if (null == head || null == head.next) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (null == fast || null == fast.next) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    public static void main(String[] args) {
        HasCycle.ListNode l1 = new HasCycle.ListNode(1);
        l1.next = new HasCycle.ListNode(2);
        l1.next.next = new HasCycle.ListNode(3);
        l1.next.next.next = new HasCycle.ListNode(4);
        l1.next.next.next.next = l1.next;
        HasCycle.ListNode l2 = l1;
        LeetCodeUtil.logln("solution1(l1) = " + solution1(l1));
        LeetCodeUtil.logln("solution2(l2) = " + solution2(l2));
    }
}
