package com.sunfusheng.algo.Algo.StackQueue;

import java.util.Stack;

/**
 * 【题目】
 * 用两个栈实现队列
 * <p>
 * 【要求】
 * 编写一个类，用两个栈实现队列，支持队列的操作（add, poll, peek）
 *
 * @author sunfusheng
 * @since 2020/3/2
 */
public class TwoStacksQueue {
    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;

    public TwoStacksQueue() {
        this.stackPush = new Stack<>();
        this.stackPop = new Stack<>();
    }

    private void stackPush2StackPop() {
        if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
    }

    public boolean isEmpty() {
        return stackPush.isEmpty() && stackPop.isEmpty();
    }

    public void add(int value) {
        stackPush.push(value);
        stackPush2StackPop();
    }

    public int poll() {
        if (isEmpty()) {
            throw new RuntimeException("TwoStacksQueue is empty!");
        }
        stackPush2StackPop();
        return stackPop.pop();
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("TwoStacksQueue is empty!");
        }
        stackPush2StackPop();
        return stackPop.peek();
    }

    public static void main(String[] args) {
        TwoStacksQueue queue = new TwoStacksQueue();
        int[] arr = {1, 2, 3};

        System.out.print("将数据加入队列：");
        for (int value : arr) {
            queue.add(value);
            System.out.print(value + " ");
        }

        int[] arr2 = {4, 5, 6};
        System.out.println(" ");
        System.out.print("再次将数据加入队列：");
        for (int value : arr2) {
            queue.add(value);
            System.out.print(value + " ");
        }

        System.out.println(" ");
        System.out.print("将数据弹出队列：");
        while (!queue.isEmpty()) {
            int value = queue.poll();
            System.out.print(value + " ");
        }
    }
}
