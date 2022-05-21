package com.wangcheng.leetcode.LeetCode.LinkedList;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.sunfusheng.algo.common.model.Node;
import com.sunfusheng.algo.common.util.AlgoUtil;

import java.util.PriorityQueue;

/**
 * 【题目】
 * 23.合并K个升序链表
 * <p>
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * <p>
 * 示例 2：
 * 输入：lists = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：lists = [[]]
 * 输出：[]
 *
 * @author sunfusheng
 * @since 2020/4/28
 */
public class MergeKLists {

    /**
     * 方法一：归并排序
     * <p>
     * 时间复杂度：O(nlogk)
     * 空间复杂度：O(logk)
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
        Node head1 = merge(lists, left, mid);
        Node head2 = merge(lists, mid + 1, right);
        return mergeTwoLists(head1, head2);
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

    /**
     * 方法二：最小堆
     * <p>
     * 时间复杂度：O(nlogk)
     * 空间复杂度：O(k)
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Node mergeKLists2(Node[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        Node dummy = new Node(-1);
        Node cur = dummy;
        PriorityQueue<Node> minHeap = new PriorityQueue<>((n1, n2) -> n1.value - n2.value);
        for (Node node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }
        while (!minHeap.isEmpty()) {
            Node least = minHeap.poll();
            if (least == null) {
                break;
            }
            cur.next = least;
            cur = least;
            if (least.next != null) {
                minHeap.offer(least.next);
            }
        }
        return dummy.next;
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
