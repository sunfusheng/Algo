package com.wangcheng.leetcode.LeetCode.LinkedList;

import java.util.Stack;

/**
 * 155.最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * 实现 MinStack 类:
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 * <p>
 * <p>
 * 示例 1:
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * <p>
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 * <p>
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 * <p>
 * 提示：
 * -2^31 <= val <= 2^31 - 1
 * pop、top 和 getMin 操作总是在 非空栈 上调用
 * push, pop, top, and getMin最多被调用 3 * 10^4 次
 *
 * @author liwangcheng
 * @date 2020/4/7.
 */
public class MinStack {

    private Stack<Integer> data = null;
    private Stack<Integer> min = null;

    public MinStack() {
        data = new Stack<>();
        min = new Stack<>();
    }

    public void push(int val) {
        data.push(val);
        if (min.isEmpty() || val <= min.peek()) {
            min.push(val);
        }
    }

    public int pop() {
        int res = data.pop();
        if (res == min.peek()) {
            min.pop();
        }
        return res;
    }

    public int top() {
        return data.peek();
    }

    public int getMin() {
        return min.peek();
    }
}
