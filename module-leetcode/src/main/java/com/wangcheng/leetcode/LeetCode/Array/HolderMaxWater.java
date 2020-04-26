package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.AlgoUtil;

/**
 * 【题目】
 * 11.盛最多水的容器
 * <p>
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * <p>
 * 示例：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
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
     *
     * @param height
     * @return
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
