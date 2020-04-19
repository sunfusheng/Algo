package com.sunfusheng.algo.Algo.LinkedList;

import com.sunfusheng.algo.common.model.Node;
import com.sunfusheng.algo.common.util.AlgoUtil;

import java.util.Stack;

/**
 * 【题目】
 * 将单链表的每K个节点之间逆序
 * <p>
 * 给定一个单链表的头节点head，实现一个调整单链表的函数，使得每K个节点之间逆序，
 * 如果最后不够K个节点一组，则不调整最后几个节点。
 * <p>
 * 【例如】
 * 原链表为：1-＞2-＞3-＞4-＞5-＞6-＞7-＞8-＞null，K=3。
 * 调整后为：3-＞2-＞1-＞6-＞5-＞4-＞7-＞8-＞null。
 * 其中7、8不调整，因为不够一组。
 *
 * @author sunfusheng
 * @since 2020/4/18
 */
public class ReverseKNodes {

    /**
     * 方法一：利用栈结构解法
     * <p>
     * 时间复杂度：O(N)
     * 空间复杂度：O(K)
     *
     * @param head
     * @param K
     * @return
     */
    public static Node reverseKNodes1(Node head, int K) {
        if (K < 2) {
            return head;
        }

        Stack<Node> stack = new Stack<>();
        Node newHead = head;
        Node cur = head;
        Node pre = null;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            stack.push(cur);
            if (stack.size() == K) {
                pre = resign1(stack, pre, next);
                newHead = newHead == head ? cur : newHead;
            }
            cur = next;
        }
        return newHead;
    }

    private static Node resign1(Stack<Node> stack, Node left, Node right) {
        Node cur = stack.pop();
        if (left != null) {
            left.next = cur;
        }
        Node next = null;
        while (!stack.isEmpty()) {
            next = stack.pop();
            cur.next = next;
            cur = next;
        }
        cur.next = right;
        return cur;
    }

    /**
     * 方法二：在原链表中直接调整
     * <p>
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     *
     * @param head
     * @param K
     * @return
     */
    public static Node reverseKNodes2(Node head, int K) {
        if (K < 2) {
            return head;
        }

        Node pre = null;
        Node start = null;
        Node cur = head;
        Node next = null;
        int count = 1;
        while (cur != null) {
            next = cur.next;
            if (count == K) {
                start = pre == null ? head : pre.next;
                head = pre == null ? cur : head;
                resign2(pre, start, cur, next);
                pre = start;
                count = 0;
            }
            count++;
            cur = next;
        }
        return head;
    }

    private static void resign2(Node left, Node start, Node end, Node right) {
        Node pre = start;
        Node cur = start.next;
        Node next = null;
        while (cur != right) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        if (left != null) {
            left.next = end;
        }
        start.next = right;
    }

    public static void main(String[] args) {
        Node head = AlgoUtil.createLinkedList(1, 2, 3, 4, 5, 6, 7, 8);
        System.out.print("输入：");
        AlgoUtil.printLinkedList(head);
        System.out.print("方法一输出：");
        head = reverseKNodes1(head, 3);
        AlgoUtil.printLinkedList(head);

        head = AlgoUtil.createLinkedList(1, 2, 3, 4, 5, 6, 7, 8);
        head = reverseKNodes2(head, 3);
        System.out.print("方法二输出：");
        AlgoUtil.printLinkedList(head);
    }
}
