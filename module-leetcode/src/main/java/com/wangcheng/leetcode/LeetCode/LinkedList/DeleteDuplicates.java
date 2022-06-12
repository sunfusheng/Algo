package com.wangcheng.leetcode.LeetCode.LinkedList;

import com.sunfusheng.algo.common.model.ListNode;
import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 * 【题目】
 * 83.删除排序链表中的重复元素
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * <p>
 * 示例 1:
 * 输入: 1->1->2
 * 输出: 1->2
 * <p>
 * 示例 2:
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 *
 * @author liwangcheng
 * @date 2020/3/26.
 */
public class DeleteDuplicates {

    public static ListNode solution(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }
}
