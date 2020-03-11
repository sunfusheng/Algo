package com.sunfusheng.algo.Algo.StackQueue;

import java.util.Stack;

/**
 * 【题目】
 * 用一个栈实现另一个栈的排序
 * <p>
 * 一个栈中元素的类型为整型，现在想将该栈从顶到底按从大到小的顺序排序，只许申请一个栈。
 * 除此之外，可以申请新的变量，但不能申请额外的数据结构。如何完成排序？
 *
 * @author sunfusheng
 * @since 2020/3/10
 */
public class SortStackByStack {

    public static void sortStackByStack(Stack<Integer> stack) {
        if (stack.isEmpty() || stack.size() < 2) {
            return;
        }

        Stack<Integer> help = new Stack<>();
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            while (!help.isEmpty() && help.peek() < cur) {
                stack.push(help.pop());
            }
            help.push(cur);
        }

        while (!help.isEmpty()) {
            stack.push(help.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        int[] arr = {3, 2, 5, 1, 4};

        System.out.print("将数据压入栈：");
        for (int value : arr) {
            stack.push(value);
            System.out.print(value + " ");
        }

        // 用一个栈实现另一个栈的排序
        sortStackByStack(stack);

        System.out.println(" ");
        System.out.print("排序后将数据弹出栈：");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}
