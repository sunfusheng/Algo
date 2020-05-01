package com.wangcheng.leetcode.LeetCode.LinkedList;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 【题目】
 * 225.用队列实现栈
 * 使用队列实现栈的下列操作：
 * push(x) -- 元素 x 入栈
 * pop() -- 移除栈顶元素
 * top() -- 获取栈顶元素
 * empty() -- 返回栈是否为空
 * 注意:
 * 你只能使用队列的基本操作 -- 也就是 
 * push to back, peek/pop from front, size, 和 is empty 这些操作是合法的。
 * 你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列,
 * 只要是标准的队列操作即可。
 * 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。
 *
 * @author liwangcheng
 * @date 2020/4/23.
 */
public class MyStack {

    /**
     * 方法一（两个队列，压入 -O(1)， 弹出 -O(n)）
     */
    private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();
    private Integer top;

    /**
     * 压入（push）
     * 新元素永远从 q1 的后端入队，同时 q1 的后端也是栈的 栈顶（top）元素。
     * 复杂度分析
     * 时间复杂度：O(1) 队列是通过链表来实现的，入队（add）操作的时间复杂度为 O(1)。
     * 空间复杂度：O(1)
     */
    public void push1(Integer x) {
        q1.add(x);
        top = x;
    }

    /**
     * 弹出（pop）
     * 需要把栈顶元素弹出，就是 q1 中最后入队的元素。
     * 考虑到队列是一种 FIFO 的数据结构，最后入队的元素应该在最后被出队。
     * 因此需要维护另外一个队列 q2，这个队列用作临时存储 q1 中出队的元素。
     * q2 中最后入队的元素将作为新的栈顶元素。接着将 q1 中最后剩下的元素出队。
     * 通过把 q1 和 q2 互相交换的方式来避免把 q2 中的元素往 q1 中拷贝。
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n)
     * 算法让 q1 中的 n 个元素出队，让 n−1 个元素从 q2 入队，在这里 n 是栈的大小。
     * 这个过程总共产生了 2n−1 次操作，时间复杂度为 O(n)。
     */
    public Integer pop1() {
        if (q1.isEmpty()) {
            throw new IllegalStateException();
        }
        while (q1.size() > 1) {
            top = q1.remove();
            q2.add(top);
        }
        top = q1.remove();
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
        return top;
    }

    /**
     * 方法二 （两个队列， 压入 - O(n)， 弹出 - O(1)）
     */
    /**
     * 压入（push)
     * 让每一个新元素从 q2 入队，同时把这个元素作为栈顶元素保存。
     * 当 q1 非空（也就是栈非空），让 q1 中所有的元素全部出队，
     * 再将出队的元素从 q2 入队。通过这样的方式，
     * 新元素（栈中的栈顶元素）将会在 q2 的前端。
     * 通过将 q1， q2 互相交换的方式来避免把 q2 中的元素往 q1 中拷贝。
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n) 算法会让 q1 出队 n 个元素，同时入队 n+1 个元素到 q2。
     * 这个过程会产生 2n+1 步操作，同时链表中 插入 操作和 移除 操作的时间复杂度为 O(1)，
     * 因此时间复杂度为 O(n)。
     * 空间复杂度：O(1)
     */
    public void push2(Integer x) {
        q2.add(x);
        top = x;
        while (!q1.isEmpty()) {
            q2.add(q1.remove());
        }
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    /**
     * 弹出（pop）
     * 直接让 q1 中元素出队，同时将出队后的 q1 中的队首元素作为栈顶元素保存。
     * <p>
     * 复杂度分析
     * 时间复杂度：O(1)
     * 空间复杂度：O(1)
     */
    public Integer pop2() {
        int res = q1.remove();
        if (!q1.isEmpty()) {
            top = q1.peek();
        }
        return res;
    }

    /**
     * 方法三（一个队列， 压入 - O(n)， 弹出 - O(1)）
     */
    /**
     * 压入（push）
     * 将一个元素从队列入队的时候，根据队列的性质这个元素会存在队列的后端。
     * 但实现一个栈的时候，最后入队的元素应该在前端，而不是在后端。
     * 为了实现这个目的，每当入队一个新元素的时候，可以把队列的顺序反转过来。
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n) 这个算法需要从 q1 中出队 n 个元素，
     * 同时还需要入队 n 个元素到 q1，其中 n 是栈的大小。这个过程总共产生了 2n+1 步操作。
     * 链表中 插入 操作和 移除 操作的时间复杂度为 O(1)，因此时间复杂度为 O(n)。
     * 空间复杂度：O(1)
     */
    public void push3(Integer x) {
        q1.add(x);
        int size = q1.size();
        while (size > 1) {
            q1.add(q1.remove());
            size--;
        }
    }

    /**
     * 弹出（pop）
     * 最后一个压入的元素永远在 q1 的前端，这样的话就能在常数时间内让它出队。
     */
    public Integer pop3() {
        return q1.remove();
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    private static void test1() {
        MyStack stack = new MyStack();
        stack.push1(1);
        stack.push1(2);
        stack.push1(3);
        LeetCodeUtil.logln("push1 - 1,2,3");
        LeetCodeUtil.logln("pop1 - " + stack.pop1());
        LeetCodeUtil.logln("pop1 - " + stack.pop1());
        LeetCodeUtil.logln("pop1 - " + stack.pop1());
    }

    private static void test2() {
        MyStack stack = new MyStack();
        stack.push2(1);
        stack.push2(2);
        stack.push2(3);
        LeetCodeUtil.logln("push2 - 1,2,3");
        LeetCodeUtil.logln("pop2 - " + stack.pop2());
        LeetCodeUtil.logln("pop2 - " + stack.pop2());
        LeetCodeUtil.logln("pop2 - " + stack.pop2());
    }

    private static void test3() {
        MyStack stack = new MyStack();
        stack.push3(1);
        stack.push3(2);
        stack.push3(3);
        LeetCodeUtil.logln("push3 - 1,2,3");
        LeetCodeUtil.logln("pop3 - " + stack.pop3());
        LeetCodeUtil.logln("pop3 - " + stack.pop3());
        LeetCodeUtil.logln("pop3 - " + stack.pop3());
    }
}
