package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.AlgoUtil;

import java.util.Stack;

/**
 * 【题目】
 * 42.接雨水
 * <p>
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * <p>
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *
 * @author sunfusheng
 * @since 2020/4/26
 */
public class HoldRainWater {

    /**
     * 解法一：单调栈
     * <p>
     * 复杂度分析
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     */
    public static int solution1(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }

        int res = 0;
        // 单调递减栈：存放 height 数组的索引
        Stack<Integer> stack = new Stack<>();
        // 当前索引指针
        int index = 0;
        while (index < height.length) {
            while (!stack.isEmpty() && height[index] > height[stack.peek()]) {
                int popIndex = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                // 底部的宽度
                int w = index - stack.peek() - 1;
                // 可接雨水的高度
                int h = Math.min(height[index], height[stack.peek()]) - height[popIndex];
                res += w * h;
            }
            stack.push(index++);
        }
        return res;
    }

    /**
     * 解法二：双指针
     * <p>
     * 复杂度分析
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     */
    public static int solution2(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }

        int res = 0;
        int left = 0; // 左指针
        int right = height.length - 1; // 右指针
        int leftMax = 0;
        int rightMax = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] > leftMax) {
                    leftMax = height[left];
                } else {
                    res += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] > rightMax) {
                    rightMax = height[right];
                } else {
                    res += rightMax - height[right];
                }
                right--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.print("输入：");
        AlgoUtil.printlnArray(height);

        System.out.println("解法一输出：" + solution1(height));
        System.out.println("解法二输出：" + solution2(height));
    }
}
