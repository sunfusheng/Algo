package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;
import com.sunfusheng.algo.common.util.LeetCodeUtilKt;

import java.util.Arrays;

/**
 *【题目】
 * 66.加一
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位，数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 *【示例】
 * 示例 1:
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 *
 * 示例 2:
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 *
 *  @author liwangcheng
 * @date 2020/3/25.
 */
public class PlusOne {

    public static int[] solution1(int[] nums) {
        if (null == nums || nums.length == 0) {
            return nums;
        }
        int carry = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            int sum = nums[i] + carry;
            carry = sum / 10;
            sum %= 10;
            nums[i] = sum;
            if (i == 0 && carry > 0) {
                int[] newNums = new int[nums.length + 1];
                newNums[0] = 1;
                return newNums;
            }
        }
        return nums;
    }

    public static int[] solution2(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1([1, 2, 3]) = " + LeetCodeUtilKt.format(PlusOne.solution1(new int[]{1, 2, 3})));
        LeetCodeUtil.logln("solution1([1, 2, 9]) = " + LeetCodeUtilKt.format(PlusOne.solution1(new int[]{1, 2, 9})));
        LeetCodeUtil.logln("solution1([1, 9, 9]) = " + LeetCodeUtilKt.format(PlusOne.solution1(new int[]{1, 9, 9})));
        LeetCodeUtil.logln("solution1([9, 9, 9]) = " + LeetCodeUtilKt.format(PlusOne.solution1(new int[]{9, 9, 9})));
        LeetCodeUtil.logln("solution2([1, 2, 3]) = " + LeetCodeUtilKt.format(PlusOne.solution2(new int[]{1, 2, 3})));
        LeetCodeUtil.logln("solution2([1, 2, 9]) = " + LeetCodeUtilKt.format(PlusOne.solution2(new int[]{1, 2, 9})));
        LeetCodeUtil.logln("solution2([1, 9, 9]) = " + LeetCodeUtilKt.format(PlusOne.solution2(new int[]{1, 9, 9})));
        LeetCodeUtil.logln("solution2([9, 9, 9]) = " + LeetCodeUtilKt.format(PlusOne.solution2(new int[]{9, 9, 9})));
    }
}
