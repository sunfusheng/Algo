package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 507.完美数
 * 对于一个 正整数，如果它和除了它自身以外的所有正因子之和相等，
 * 我们称它为“完美数”。
 * <p>
 * 给定一个 整数 n， 如果他是完美数，返回 True，否则返回 False
 * <p>
 *【示例】
 * 输入: 28
 * 输出: True
 * 解释: 28 = 1 + 2 + 4 + 7 + 14
 *【提示】
 * 输入的数字 n 不会超过 100,000,000. (1e8)
 *
 * @author liwangcheng
 * @date 2020/5/20.
 */
public class CheckPerfectNumber {

    /**
     * 方法一：枚举
     * 枚举 n 的所有因数，并计算它们的和。
     *
     * 在枚举时，只需要从 1 到 sqrt(n) 进行枚举即可。
     * 这是因为如果 n 有一个大于 sqrt(n) 的因数 x，
     * 那么它一定有一个小于 sqrt(n) 的因数 n/x。因此
     * 可以从 1 到 sqrt(n) 枚举 n 的因数，当出现一个
     * n 的因数 x 时，还需要算上 n/x。此外还需要考虑
     * 特殊情况，即 x = n/x，这时不能重复计算。
     *
     * 在求出 n 的所有因数之和 sum 后，由于 sum 包含了
     * n 本身，因此只需要判断 sum == n * 2 即可。
     *
     * 复杂度分析
     * 时间复杂度：O(sqrt{N})。
     * 空间复杂度：O(1)。
     */
    public static boolean solution1(int num) {
        if (num < 0) {
            return false;
        }
        int sum = 0;
        for (int i = 1; i*i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                if (i * i != num) {
                    sum += num/i;
                }
            }
        }
        return sum - num == num;
    }

    /**
     * TODO
     * 方法二：欧几里得-欧拉定理
     * 欧几里得-欧拉定理告诉我们，每个偶完全数都可以写成 2^(p-1)((2^p) - 1)
     * 的形式，其中 p 为素数。例如前四个完全数可以写成如下形式：
     * 6 = 2^1 * (2^2 - 1)
     * 28 = 2^2 * (2^3 - 1)
     * 496 = 2^3 * (2^4 - 1)
     * 8128 = 2^4 * (2^5 - 1)
     * 由于目前奇完全数还未被发现，因此所有的完全数都可以写成上述形式。
     * 当 n 不超过 10^8 时，p 也不会很大，因此只要带入最小的若干个
     * 素数 2, 3, 5, 7, 13, 17, 19, 31)，将不超过 10^8 的所有
     * 完全数计算出来即可。
     *
     * 复杂度分析
     * 时间复杂度：O(1)。
     * 空间复杂度：O(1)。
     */
    public static boolean solution2(int num) {
        int[] primes=new int[]{2,3,5,7,13,17,19,31};
        for (int prime: primes) {
            if (pn(prime) == num) {
                return true;
            }
        }
        return false;
    }

    private static int pn(int p) {
        return (1 << (p - 1)) * ((1 << p) - 1);
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(28) = " + CheckPerfectNumber.solution1(28));
        LeetCodeUtil.logln("solution1(29) = " + CheckPerfectNumber.solution1(29));
        LeetCodeUtil.logln("solution1(496) = " + CheckPerfectNumber.solution1(496));
        LeetCodeUtil.logln("solution1(8128) = " + CheckPerfectNumber.solution1(8128));
        LeetCodeUtil.logln("solution1(81280) = " + CheckPerfectNumber.solution1(81280));
        LeetCodeUtil.logln("solution2(28) = " + CheckPerfectNumber.solution2(28));
        LeetCodeUtil.logln("solution2(29) = " + CheckPerfectNumber.solution2(29));
        LeetCodeUtil.logln("solution2(496) = " + CheckPerfectNumber.solution2(496));
        LeetCodeUtil.logln("solution2(8128) = " + CheckPerfectNumber.solution2(8128));
        LeetCodeUtil.logln("solution2(81280) = " + CheckPerfectNumber.solution2(81280));
    }
}
