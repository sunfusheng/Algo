package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.AlgoUtil;

/**
 * 【题目】
 * 11.盛最多水的容器
 * <p>
 * 给定一个长度为 n 的整数数组 height。有 n 条垂线，第 i 条线的两个端点是 (i, 0)和 (i, height[i])。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * <p>
 * 说明：你不能倾斜容器。
 * <p>
 * 示例 1：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为49。
 * <p>
 * 示例 2：
 * 输入：height = [1,1]
 * 输出：1
 *
 * @author sunfusheng
 * @since 2020/4/26
 */
public class HolderMaxWater {

    /**
     * 解法：双指针
     * <p>
     * 复杂度分析
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     */
    public static int solution(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }

        int res = 0;
        int left = 0; // 左指针
        int right = height.length - 1; // 右指针
        while (left < right) {
            int area = (right - left) * Math.min(height[left], height[right]);
            res = Math.max(res, area);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.print("输入：");
        AlgoUtil.printlnArray(height);

        System.out.println("输出：" + solution(height));
    }
}
