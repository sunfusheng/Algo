package com.sunfusheng.algo.Algo.LinkedList;

import com.sunfusheng.algo.common.model.Node;
import com.sunfusheng.algo.common.util.AlgoUtil;

/**
 * 【题目】
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式
 * <p>
 * 给定一个单向链表的头节点head，节点的值类型是整型，再给定一个整数pivot。
 * 实现一个调整链表的函数，将链表调整为：
 * ● 左部分都是值小于pivot的节点，
 * ● 中间部分都是值等于pivot的节点，
 * ● 右部分都是值大于pivot的节点。
 * 除这个要求外，对调整后的节点顺序没有更多的要求。
 * <p>
 * 例如：链表9-＞3->0-＞4-＞5-＞1，pivot=3。
 * 调整后链表可以是 1-＞0->3-＞5-＞4-＞9，也可以是 1-＞0->3-＞5-＞9-＞4。
 * 总之，满足左部分都是小于3的节点，中间部分都是等于3的节点，右部分都是大于3的节点即可。
 * 对某部分内部的节点顺序不做要求。
 * <p>
 * 【进阶】
 * 在原问题的要求之上再增加如下两个要求。
 * ● 在左、中、右三个部分的内部也做顺序要求，
 * 要求每部分里的节点从左到右的顺序与原链表中节点的先后次序一致。
 * 例如：链表9-＞3->0-＞4-＞5-＞1，pivot=3。
 * 调整后的链表是0-＞1-＞3->9-＞4-＞5。
 * ● 如果链表长度为N，时间复杂度请达到O(N)，额外空间复杂度请达到O(1)。
 *
 * @author sunfusheng
 * @since 2020/4/10
 */
public class Partition {

    /**
     * 原问题解法
     * <p>
     * 时间复杂度O(N)
     * 空间复杂度O(N)
     *
     * @param head  单向链表头节点
     * @param pivot 中心点
     * @return 返回调整后的链表
     */
    public static Node listPartition1(Node head, int pivot) {
        if (head == null) {
            return head;
        }

        int len = 0;
        Node cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        Node[] arr = new Node[len];
        cur = head;
        for (int i = 0; i < len; i++) {
            arr[i] = cur;
            cur = cur.next;
        }

        quickSort(arr, pivot);
        for (int i = 0; i < len - 1; i++) {
            arr[i].next = arr[i + 1];
        }
        arr[len - 1].next = null;
        return arr[0];
    }

    private static void quickSort(Node[] arr, int pivot) {
        int small = -1;
        int big = arr.length;
        int index = 0;
        while (index != big) {
            if (arr[index].value < pivot) {
                swap(arr, ++small, index++);
            } else if (arr[index].value == pivot) {
                index++;
            } else {
                swap(arr, --big, index);
            }
        }
    }

    private static void swap(Node[] arr, int a, int b) {
        Node temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * 进阶问题解法：
     * 将所有的节点分进三个链表里，再连接三个链表
     * <p>
     * 时间复杂度O(N)
     * 空间复杂度O(1)
     *
     * @param head  单向链表头节点
     * @param pivot 中心点
     * @return 返回调整后的链表
     */
    public static Node listPartition2(Node head, int pivot) {
        if (head == null) {
            return head;
        }
        Node sH = null; // 小的头
        Node sT = null; // 小的尾
        Node eH = null; // 相等的头
        Node eT = null; // 相等的尾
        Node bH = null; // 大的头
        Node bT = null; // 大的尾
        Node next = null; // 保存下一个节点
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.value == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if (bH == null) {
                    bH = head;
                    bT = head;
                } else {
                    bT.next = head;
                    bT = head;
                }
            }
            head = next;
        }

        if (sT != null) {
            sT.next = eH;
            eT = eT == null ? sT : eT;
        }
        if (eT != null) {
            eT.next = bH;
        }
        return sH != null ? sH : eH != null ? eH : bH;
    }

    public static void main(String[] args) {
        Node head = AlgoUtil.createLinkedList(9, 3, 0, 4, 5, 1);
        System.out.println("输入:");
        AlgoUtil.printLinkedList(head);

        head = listPartition1(head, 3);
        System.out.println("原问题输出:");
        AlgoUtil.printLinkedList(head);

        head = AlgoUtil.createLinkedList(9, 3, 0, 4, 5, 1);
        head = listPartition2(head, 3);
        System.out.println("进阶问题输出:");
        AlgoUtil.printLinkedList(head);
    }
}
