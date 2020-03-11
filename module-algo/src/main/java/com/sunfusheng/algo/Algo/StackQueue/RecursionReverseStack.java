package com.sunfusheng.algo.Algo.StackQueue;

import java.util.Stack;

/**
 * 【题目】
 * 如何仅用递归函数和栈操作逆序一个栈
 * <p>
 * 一个栈依次压入1、2、3、4、5，那么从栈顶到栈底分别为5、4、3、2、1。
 * 将这个栈转置后，从栈顶到栈底为1、2、3、4、5，也就是实现栈中元素的逆序，
 * 但是只能用递归函数来实现，不能用其他数据结构。
 *
 * @author sunfusheng
 * @since 2020/3/8
 */
public class RecursionReverseStack {

    // 逆序一个栈
    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }

        int i = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(i);
    }

    // 将栈stack的栈底元素返回并移除
    private static int getAndRemoveLastElement(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        int[] arr = {1, 2, 3, 4, 5};

        System.out.println("依次将 {1, 2, 3, 4, 5} 数据压入栈");
        for (int value : arr) {
            stack.push(value);
        }

        // 调用递归函数逆序
        reverse(stack);

        System.out.print("调用递归函数逆序后将数据弹出栈：");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}
