package com.cheng.leetcode.LeetCode.LinkedList;

import android.annotation.TargetApi;


import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.Objects;

/**
 * 【题目】
 * 给出两个非空的链表用来表示两个非负的整数。
 * 其中，它们各自的位数是按照逆序的方式存储的，
 * 并且它们的每个节点只能存储一位数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 【示例】
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * @author liwangcheng
 * @date 2020/3/10.
 */
public class AddTwoNumbers {

    private static class ListNode {
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data = data;
        }
    }

    @TargetApi(24)
    private ListNode solution(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode ll = root;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int v1 = Objects.nonNull(l1) ? l1.data : 0;
            int v2 = Objects.nonNull(l2) ? l2.data : 0;
            int sum = carry + v1 + v2;
            carry = sum / 10;
            ll.next = new ListNode(sum % 10);
            ll = ll.next;
            l1 = Objects.nonNull(l1) ? l1.next : null;
            l2 = Objects.nonNull(l2) ? l2.next : null;
        }
        return root.next;
    }

    public static void main(String[] args) {
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode ll = addTwoNumbers.solution(l1, l2);
        while (ll != null) {
            LeetCodeUtil.log(ll.data + "");
            ll = ll.next;
        }
    }

}
