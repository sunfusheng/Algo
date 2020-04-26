package com.wangcheng.leetcode.LeetCode.LinkedList;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.Stack;

/**
 *【题目】
 * 232.用栈实现队列
 * 使用栈实现队列的下列操作：
 * push(x) -- 将一个元素放入队列的尾部。
 * pop() -- 从队列首部移除元素。
 * peek() -- 返回队列首部的元素。
 * empty() -- 返回队列是否为空。
 *【示例】
 * MyQueue queue = new MyQueue();
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // 返回 1
 * queue.pop();   // 返回 1
 * queue.empty(); // 返回 false
 *
 * @author liwangcheng
 * @date 2020/4/26.
 */
public class MyQueue {

    /**
     * 思路：
     * 为了满足队列的 FIFO 的特性，我们需要用到两个栈，用它们其中一个来反转元素的入队顺序，用另一个来存储元素的最终顺序。
     */

    /**
     * 方法一（使用两个栈 入队 - O(n)， 出队 - O(1)）
     */
    /**
     * 入队（add）
     * 一个队列是 FIFO 的，但一个栈是 LIFO 的。这就意味着最新压入的元素必须得放在栈底。
     * 为了实现这个目的，首先需要把 s1 中所有的元素移到 s2 中，接着把新元素压入 s2。
     * 最后把 s2 中所有的元素弹出，再把弹出的元素压入 s1。
     */
    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();

    /**
     * 复杂度分析
     *
     * 时间复杂度：O(n)
     * 对于除了新元素之外的所有元素，它们都会被压入两次，弹出两次。
     * 新元素只被压入一次，弹出一次。这个过程产生了 4n+2 次操作，
     * 其中 n 是队列的大小。由于压入操作和弹出操作的时间复杂度为 O(1)，
     * 所以时间复杂度为 O(n)。
     * 空间复杂度：O(n)
     * 需要额外的内存来存储队列中的元素。
     */
    public void add1(int x) {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        s2.push(x);
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
    }

    /**
     * 出队（pop）
     * 直接从 s1 弹出就可以了，因为 s1 的栈顶元素就是队列的队首元素。
     * 同时把弹出之后 s1 的栈顶元素赋值给代表队首元素的 front 变量。
     */
    public int poll1() {
        return s1.pop();
    }

    public int peek1() {
        return s1.peek();
    }

    public boolean isEmpty1() {
        return s1.isEmpty();
    }

    private Stack<Integer> pushStack = new Stack<>();
    private Stack<Integer> popStack = new Stack<>();
    /**
     * 方法二（使用两个栈 入队 - O(1)，出队 - 摊还复杂度 O(1)）
     */
    public void add2(int x) {
        pushStack.push(x);
    }

    public int poll2() {
        pushToPop();
        return popStack.pop();
    }

    public int peek2() {
        pushToPop();
        return popStack.peek();
    }

    public boolean empty2() {
        pushToPop();
        return popStack.isEmpty();
    }

    private void pushToPop() {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.add1(1);
        queue.add1(2);
        queue.add1(3);
        queue.add1(4);
        LeetCodeUtil.logln("- " + queue.poll1());
        LeetCodeUtil.logln("- " + queue.poll1());
        LeetCodeUtil.logln("当前元素： " + queue.peek1());
        LeetCodeUtil.logln("- " + queue.poll1());
        LeetCodeUtil.logln("- " + queue.poll1());
        queue.add2(1);
        queue.add2(2);
        queue.add2(3);
        queue.add2(4);
        LeetCodeUtil.logln("- " + queue.poll2());
        LeetCodeUtil.logln("- " + queue.poll2());
        LeetCodeUtil.logln("当前元素： " + queue.peek2());
        LeetCodeUtil.logln("- " + queue.poll2());
        LeetCodeUtil.logln("- " + queue.poll2());
    }
}
