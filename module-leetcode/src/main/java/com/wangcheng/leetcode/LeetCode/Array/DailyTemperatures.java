package com.wangcheng.leetcode.LeetCode.Array;

import java.util.Stack;

/**
 * 739.每日温度
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
 * 其中 answer[i] 是指在第 i 天之后，才会有更高的温度。
 * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * <p>
 * 示例 2:
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * <p>
 * 示例 3:
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 * <p>
 * 提示：
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 *
 * @author sunfusheng
 * @since 2022/06/15
 */
public class DailyTemperatures {

    /**
     * 算法思想：单调递减栈
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) {
            return temperatures;
        }
        int len = temperatures.length;
        int[] res = new int[len];
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        while (i < len) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int lastIndex = stack.pop();
                res[lastIndex] = i - lastIndex;
            }
            stack.push(i++);
        }
        return res;
    }
}
