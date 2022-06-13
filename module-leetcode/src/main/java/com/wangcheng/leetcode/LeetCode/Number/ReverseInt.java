package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 * 7.整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * <p>
 * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31,  2^31 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * <p>
 * 示例 1：
 * 输入：x = 123
 * 输出：321
 * <p>
 * 示例 2：
 * 输入：x = -123
 * 输出：-321
 * <p>
 * 示例 3：
 * 输入：x = 120
 * 输出：21
 * <p>
 * 示例 4：
 * 输入：x = 0
 * 输出：0
 * <p>
 * 提示：
 * -2^31 <= x <= 2^31 - 1
 *
 * @author liwangcheng
 * @date 2020/3/17.
 */
public class ReverseInt {

    /**
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     */
    public static int solution(int x) {
        int rev = 0;
        while (x != 0) {
            if (rev > Integer.MAX_VALUE / 10 || rev < Integer.MIN_VALUE / 10) {
                return 0;
            }
            int num = x % 10;
            rev = rev * 10 + num;
            x /= 10;
        }
        return rev;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution(123) = " + solution(123));
        LeetCodeUtil.logln("solution(-123) = " + solution(-123));
        LeetCodeUtil.logln("solution(120) = " + solution(120));
    }
}
