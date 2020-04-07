package com.wangcheng.leetcode.LeetCode.LinkedList;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.Stack;

/**
 *【题目】
 * 155.最小栈
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 * <p>
 *【示例】
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *
 * @author liwangcheng
 * @date 2020/4/7.
 */
public class MinStack {

    /**
     * 数据栈
     */
    private Stack<Integer> data;
    /**
     * 辅助栈
     */
    private Stack<Integer> helper;

    public MinStack() {
        data = new Stack<>();
        helper = new Stack<>();
    }

    public void push(int x) {
        data.push(x);
        if (helper.isEmpty()) {
            helper.push(x);
        } else {
            int top = helper.peek();
            helper.push(Math.min(top, x));
        }
    }

    public int pop() {
        if (!data.isEmpty()) {
            helper.pop();
            return data.pop();
        }
        throw new UnsupportedOperationException();
    }

    public int top() {
        if(!data.isEmpty()){
            return data.peek();
        }
        throw new UnsupportedOperationException();
    }

    public int getMin() {
        if (!data.isEmpty()) {
            data.pop();
            return helper.pop();
        }
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(1);
        stack.push(3);
        stack.push(2);
        stack.push(4);
        stack.push(2);
        stack.push(1);
        stack.push(3);
        stack.push(7);
        stack.push(4);
        stack.push(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        LeetCodeUtil.logln("pop = " + stack.pop());
        LeetCodeUtil.logln("top = " + stack.top());
        LeetCodeUtil.logln("min = " + stack.getMin());
        LeetCodeUtil.logln("pop = " + stack.pop());
        LeetCodeUtil.logln("top = " + stack.top());
        LeetCodeUtil.logln("min = " + stack.getMin());
        LeetCodeUtil.logln("pop = " + stack.pop());
        LeetCodeUtil.logln("top = " + stack.top());
        LeetCodeUtil.logln("min = " + stack.getMin());
        LeetCodeUtil.logln("pop = " + stack.pop());
        LeetCodeUtil.logln("top = " + stack.top());
        LeetCodeUtil.logln("min = " + stack.getMin());
    }
}
