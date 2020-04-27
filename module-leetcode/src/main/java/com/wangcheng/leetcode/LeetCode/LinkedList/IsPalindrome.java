package com.wangcheng.leetcode.LeetCode.LinkedList;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *【题目】
 * 234.回文链表
 * 请判断一个链表是否为回文链表。
 *【示例】
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 *
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 *【进阶】
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * @author liwangcheng
 * @date 2020/4/27.
 */
public class IsPalindrome {

    public static class ListNode {
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data = data;
        }
    }

    /**
     * 方法一：将值复制到数组中后用双指针法
     * 算法：
     * 可以分为两个步骤：
     * - 复制链表值到数组列表中。
     * - 使用双指针法判断是否为回文。
     *
     * 复杂度分析
     * 时间复杂度：O(n)，其中 n 指的是链表的元素个数。
     *  第一步：遍历链表并将值复制到数组中，O(n)。
     *  第二步：双指针判断是否为回文，执行了 O(n/2) 次的判断，即 O(n)。
     *  总的时间复杂度：O(2n)=O(n)。
     * 空间复杂度：O(n)，其中 n 指的是链表的元素个数，
     *  使用了一个数组列表存放链表的元素值。
     */
    public static boolean solution1(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.data);
            head = head.next;
        }
        int front = 0;
        int back = list.size() - 1;
        while (front <= back) {
            if (!list.get(front).equals(list.get(back))) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }

    /**
     * 方法二：递归
     * 如果使用递归反向迭代节点，同时使用递归函数外的变量向前迭代，就可以判断链表是否为回文。
     * 之所以起作用的原因是递归处理节点的顺序是相反的。
     * 由于递归，从本质上，同时在正向和逆向迭代。
     *
     * 复杂度分析
     * 时间复杂度：O(n)，其中 n 指的是链表的大小。
     * 空间复杂度：O(n)，其中 n 指的是链表的大小。
     */
    private static ListNode frontPointer;
    public static boolean solution2(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }

    private static boolean recursivelyCheck(ListNode currentNode) {
        if (null != currentNode) {
            if (!recursivelyCheck(currentNode.next)) {
                return false;
            }
            if (currentNode.data != frontPointer.data) {
                return false;
            }
            frontPointer = frontPointer.next;
        }
        return true;
    }

    /**
     * 方法三：
     * 避免使用 O(n) 额外空间的方法就是改变输入。
     * 可以将链表的后半部分反转（修改链表结构），然后将前半部分和后半部分进行比较。
     * 算法：
     * 可以分为以下几个步骤：
     * - 找到前半部分链表的尾节点。
     * - 反转后半部分链表。
     * - 判断是否为回文。
     * - 恢复链表。
     * - 返回结果。
     *
     * 复杂度分析
     * 时间复杂度：O(n)，其中 n 指的是链表的大小。
     * 空间复杂度：O(1)，是一个接着一个的改变指针，在堆栈上的堆栈帧不超过 O(1)。
     * 该方法的缺点是，在并发环境下，函数运行时需要锁定其他线程或进程对链表的访问，
     * 因为在函数执执行过程中链表暂时断开。
     */
    public static boolean solution3(ListNode head) {
        if (null == head) {
            return true;
        }
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.data != p2.data) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    private static ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (null != fast.next && null != fast.next.next) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        IsPalindrome.ListNode head = new IsPalindrome.ListNode(1);
        head.next = new IsPalindrome.ListNode(2);
        head.next.next = new IsPalindrome.ListNode(2);
        head.next.next.next = new IsPalindrome.ListNode(1);
        LeetCodeUtil.logln("solution1() = " + IsPalindrome.solution1(head));
        LeetCodeUtil.logln("solution2() = " + IsPalindrome.solution2(head));
        LeetCodeUtil.logln("solution3() = " + IsPalindrome.solution3(head));
    }
}
