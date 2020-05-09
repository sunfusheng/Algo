package com.wangcheng.leetcode.LeetCode.LinkedList;

/**
 * 【题目】
 * 206.反转链表
 * 反转一个单链表。
 * <p>
 * 【示例】
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * <p>
 * 【进阶】
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 * @author liwangcheng
 * @date 2020/4/22.
 */
public class ReverseList {

    public static class ListNode {
        int data;
        ListNode next;

        public ListNode(int data) {
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
     * 方法一：迭代
     * 假设存在链表 1 → 2 → 3 → Ø，我们想要把它改成 Ø ← 1 ← 2 ← 3。
     * 在遍历列表时，将当前节点的 next 指针改为指向前一个元素。
     * 由于节点没有引用其上一个节点，因此必须事先存储其前一个元素。
     * 在更改引用之前，还需要另一个指针来存储下一个节点。
     * 不要忘记在最后返回新的头引用！
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n)，假设 n 是列表的长度，时间复杂度是 O(n)。
     * 空间复杂度：O(1)。
     */
    public static ListNode solution1(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    /**
     * 方法二：递归
     * 递归版本稍微复杂一些，其关键在于反向工作。
     * 假设列表的其余部分已经被反转，现在该如何反转它前面的部分？
     * 要小心的是 n1 的下一个必须指向 Ø 。
     * 如果你忽略了这一点，你的链表中可能会产生循环。
     * 如果使用大小为 2 的链表测试代码，则可能会捕获此错误。
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n)，假设 n 是列表的长度，那么时间复杂度为 O(n)。
     * 空间复杂度：O(n)，由于使用递归，将会使用隐式栈空间。递归深度可能会达到 n 层。
     */
    public static ListNode solution2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = solution2(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}
