package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 * 9.回文数
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * <p>
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 例如，121 是回文，而 123 不是。
 * <p>
 * 示例 1：
 * 输入：x = 121
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：x = -121
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：x = 10
 * 输出：false
 * <p>
 * 提示：
 * -2^31 <= x <= 2^31 - 1
 * <p>
 * 进阶：你能不将整数转为字符串来解决这个问题吗？
 *
 * @author liwangcheng
 * @date 2020/3/17.
 */
public class PalindromeNum {

    /**
     * 思想：反转一半数字
     * <p>
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     */
    public static boolean solution(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }
        int rev = 0;
        while (x > rev) {
            int num = x % 10;
            rev = rev * 10 + num;
            x /= 10;
        }
        return rev == x || x == rev / 10;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution(121) = " + solution(121));
        LeetCodeUtil.logln("solution(122) = " + solution(122));
        LeetCodeUtil.logln("solution(1221) = " + solution(1221));
    }
}
