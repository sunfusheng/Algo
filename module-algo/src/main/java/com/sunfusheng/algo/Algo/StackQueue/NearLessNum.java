package com.sunfusheng.algo.Algo.StackQueue;

import com.sunfusheng.algo.common.util.AlgoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 【题目】
 * 单调栈结构
 * <p>
 * 给定一个[不含有重复值]的数组arr，找到每一个i位置左边和右边离i位置最近且值比arr[i]小的位置。
 * 返回所有位置相应的信息。
 * <p>
 * 【举例】
 * int[] arr = {3, 4, 1, 5, 6, 2, 7};
 * <p>
 * 返回如下二维数组作为结果：
 * {
 * -- {-1, 2},
 * -- {0, 2},
 * -- {-1, -1},
 * -- {2, 5},
 * -- {3, 5},
 * -- {2, -1},
 * -- {5, -1}
 * }
 * <p>
 * -1表示不存在，所以上面的结果表示中：
 * 0位置左边和右边离0位置最近且值比arr[0]小的位置是-1和2；
 * 1位置左边和右边离1位置最近且值比arr[1]小的位置是0和2；
 * 2位置左边和右边离2位置最近且值比arr[2]小的位置是-1和-1；
 * ......
 * <p>
 * 【进阶问题】
 * 给定一个可能[含有重复值]的数组arr，找到每一个i位置左边和右边离i位置最近且值比arr[i]小的位置。
 * 返回所有位置相应的信息。
 * <p>
 * 【要求】
 * 如果arr长度为N，实现原问题和进阶问题的解法，时间复杂度都达到O（N）。
 *
 * @author sunfusheng
 * @since 2020/4/1
 */
public class NearLessNum {

    /**
     * 解法一
     * <p>
     * 暴力解法，时间复杂度O(n2)
     *
     * @param arr
     * @return
     */
    public static int[][] getNearLestNum1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        int[][] res = new int[arr.length][2];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;
            index = i - 1;
            while (index >= 0) {
                if (arr[index] < arr[i]) {
                    leftLessIndex = index;
                    break;
                }
                index--;
            }

            index = i + 1;
            while (index < arr.length) {
                if (arr[index] < arr[i]) {
                    rightLessIndex = index;
                    break;
                }
                index++;
            }
            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;
        }
        return res;
    }

    /**
     * 解法二
     * <p>
     * 单调栈解法，时间复杂度O(N)
     *
     * @param arr
     * @return
     */
    public static int[][] getNearLestNum2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int popIndex = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                res[popIndex][0] = leftLessIndex;
                res[popIndex][1] = i;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            res[popIndex][0] = leftLessIndex;
            res[popIndex][1] = -1;
        }
        return res;
    }

    /**
     * 进阶问题解法
     * <p>
     * 单调栈解法，时间复杂度O(N)
     *
     * @param arr
     * @return
     */
    public static int[][] getNearLestNum3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> popIndexes = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (int popIndex : popIndexes) {
                    res[popIndex][0] = leftLessIndex;
                    res[popIndex][1] = i;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }

        while (!stack.isEmpty()) {
            List<Integer> popIndexes = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (int popIndex : popIndexes) {
                res[popIndex][0] = leftLessIndex;
                res[popIndex][1] = -1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 1, 5, 6, 2, 7};

        System.out.println("解法一");
        AlgoUtil.printArray2(getNearLestNum1(arr));

        System.out.println("解法二");
        AlgoUtil.printArray2(getNearLestNum2(arr));

        int[] arr2 = {3, 1, 3, 4, 3, 5, 3, 2, 2};
        System.out.println("进阶问题解法");
        AlgoUtil.printArray2(getNearLestNum3(arr2));
    }
}
