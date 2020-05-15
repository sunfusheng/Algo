package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.Arrays;

/**
 *【题目】
 * 485.最大连续1的个数
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 *【示例】
 * 输入: [1,1,0,1,1,1]
 * 输出: 3
 * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
 *【注意】
 * 输入的数组只包含 0 和1。
 * 输入数组的长度是正整数，且不超过 10,000。
 *
 * @author liwangcheng
 * @date 2020/5/15.
 */
public class FindMaxConsecutiveOnes {

    /**
     * 方法一：一次遍历
     *
     * 复杂度分析
     * 时间复杂度：O(N)。N 值得是数组的长度。
     * 空间复杂度：O(1)，仅仅使用了 count 和 maxCount。
     */
    public static int solution1(int[] nums) {
        int maxCount = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                maxCount = Math.max(maxCount, count);
                count = 0;
            }
        }
        return Math.max(maxCount, count);
    }

    /**
     * 方法二：滑动窗口
     * 思路：
     * 当输出或比较的结果在原数据结构中是连续排列的时候，可以使用滑动窗口算法求解。
     * 将两个指针比作一个窗口，通过移动指针的位置改变窗口的大小，观察窗口中的元素是否符合题意。
     *
     * - 初始窗口中只有数组开头一个元素。
     * - 当窗口中所有元素为 1 时，右指针向右移，扩大窗口。
     * - 当窗口中存在 0 时，计算连续序列长度，左指针指向右指针。
     *
     * 参考：https://leetcode-cn.com/problems/max-consecutive-ones/solution/java-485-zui-da-lian-xu-1de-ge-shu-hua-dong-chuang/
     */
    public static int solution2(int[] nums) {
        int left = 0;
        int right = 0;
        int maxSize = 0;
        while (right < nums.length) {
            if (nums[right++] == 0) {
                maxSize = Math.max(maxSize, right - left);
                left = right;
            }
        }
        return Math.max(maxSize, right - left);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,0,1,1,1};
        LeetCodeUtil.logln(Arrays.toString(nums));
        LeetCodeUtil.logln("solution1() = " + FindMaxConsecutiveOnes.solution1(nums));
        LeetCodeUtil.logln("solution2() = " + FindMaxConsecutiveOnes.solution2(nums));
    }
}
