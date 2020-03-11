package com.sunfusheng.algo.Algo.StackQueue;

import java.util.Stack;

/**
 * 【题目】
 * 设计一个有getMin功能的栈
 * <p>
 * 实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作。
 * <p>
 * 【要求】
 * 1、pop、push、getMin操作的时间复杂度都是O（1）。
 * 2、设计的栈类型可以使用现成的栈结构。
 *
 * @author sunfusheng
 * @since 2020/2/27
 */
public class MinStack {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public MinStack() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public void push(int value) {
        if (stackMin.isEmpty()) {
            stackMin.push(value);
        } else if (value <= getMin()) {
            stackMin.push(value);
        }
        stackData.push(value);
    }

    public int pop() {
        if (stackData.isEmpty()) {
            throw new RuntimeException("stackData is empty!");
        }
        int value = stackData.pop();
        if (value == getMin()) {
            stackMin.pop();
        }
        return value;
    }

    public int getMin() {
        if (stackMin.isEmpty()) {
            throw new RuntimeException("stackMin is empty!");
        }
        return stackMin.peek();
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        int[] arr = {3, 4, 5, 1, 2, 1};

        System.out.println("将数据压入栈：");
        for (int value : arr) {
            stack.push(value);
            System.out.println("In value: " + value + ", min: " + stack.getMin());
        }

        System.out.println("将数据弹出栈：");
        while (!stack.stackData.isEmpty()) {
            int min = stack.getMin();
            System.out.println("Out value: " + stack.pop() + ", min: " + min);
        }
    }
}
