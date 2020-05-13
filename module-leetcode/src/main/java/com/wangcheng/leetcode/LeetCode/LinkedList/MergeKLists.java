package com.wangcheng.leetcode.LeetCode.LinkedList;

import com.sunfusheng.algo.common.model.Node;
import com.sunfusheng.algo.common.util.AlgoUtil;

/**
 * 【题目】
 * 23.合并K个排序链表
 * <p>
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * @author sunfusheng
 * @since 2020/4/28
 */
public class MergeKLists {

    /**
     * 解题思路：
     * 使用分治思想，转成合并两个有序链表，再递归分而治之
     *
     * @param lists
     * @return
     */
    public static Node mergeKLists(Node[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        if (lists.length == 1) {
            return lists[0];
        }
        return merge(lists, 0, lists.length - 1);
    }

    private static Node merge(Node[] lists, int left, int right) {
        if (left > right) {
            return null;
        }

        if (left == right) {
            return lists[left];
        }

        int mid = left + (right - left) / 2;
        return mergeTwoLists(merge(lists, left, mid), merge(lists, mid + 1, right));
    }

    // 合并两个有序链表
    public static Node mergeTwoLists(Node n1, Node n2) {
        if (n1 == null || n2 == null) {
            return n1 == null ? n2 : n1;
        }

        Node root = new Node(-1);
        Node cur = root;
        while (n1 != null && n2 != null) {
            if (n1.value < n2.value) {
                cur.next = n1;
                n1 = n1.next;
            } else {
                cur.next = n2;
                n2 = n2.next;
            }
            cur = cur.next;
        }
        cur.next = n1 == null ? n2 : n1;
        return root.next;
    }

    public static void main(String[] args) {
        Node n1 = AlgoUtil.createLinkedList(1, 4, 5);
        Node n2 = AlgoUtil.createLinkedList(1, 3, 4);
        Node n3 = AlgoUtil.createLinkedList(2, 6);

        Node[] nodes = new Node[]{n1, n2, n3};
        Node res = mergeKLists(nodes);
        System.out.println("输出：");
        AlgoUtil.printLinkedList(res);
    }
}
