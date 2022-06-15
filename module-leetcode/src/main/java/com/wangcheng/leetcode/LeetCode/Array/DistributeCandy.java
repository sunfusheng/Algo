package com.wangcheng.leetcode.LeetCode.Array;

import java.util.Arrays;

/**
 * 【题目】
 * 135.分发糖果
 * <p>
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 * <p>
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 * 1、每个孩子至少分配到 1 个糖果。
 * 2、相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * <p>
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 * <p>
 * 示例 1:
 * 输入: [1,0,2]
 * 输出: 5
 * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 * 示例 2:
 * <p>
 * 输入: [1,2,2]
 * 输出: 4
 * 解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
 * 第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 *
 * @author sunfusheng
 * @since 2020/5/1
 */
public class DistributeCandy {

    /**
     * 方法一：使用两个数组
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param ratings
     * @return
     */
    public static int solution1(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }

        int sum = 0;
        int[] left2right = new int[ratings.length];
        int[] right2left = new int[ratings.length];
        Arrays.fill(left2right, 1);
        Arrays.fill(right2left, 1);
        int len = ratings.length;

        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left2right[i] = left2right[i - 1] + 1;
            }
        }

        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right2left[i] = right2left[i + 1] + 1;
            }
        }

        for (int i = 0; i < len; i++) {
            sum += Math.max(left2right[i], right2left[i]);
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] ratings = new int[]{1, 0, 2};
        int[] ratings2 = new int[]{1, 2, 2};

        System.out.println("方法一输出：" + solution1(ratings));
        System.out.println("方法一输出：" + solution1(ratings2));
    }
}
