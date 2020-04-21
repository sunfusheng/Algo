package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.Arrays;

/**
 * TODO
 *【题目】
 * 204.计数质数
 * 统计所有小于非负整数 n 的质数的数量。
 *【示例】
 * 输入: 10
 * 输出: 4
 * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 *
 * @author liwangcheng
 * @date 2020/4/21.
 */
public class CountPrimes {

    /**
     * 方法一：Sieve of Eratosthenes
     * 参考：https://leetcode-cn.com/problems/count-primes/solution/ru-he-gao-xiao-pan-ding-shai-xuan-su-shu-by-labula/
     */
    public static int solution1(int n) {
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        for (int i = 2; i * i < n; i++) {
            if (isPrim[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrim[j] = false;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrim[i]) {
                count++;
            }
        }
        return count;
    }

    /**
     * 方法二：优化暴力算法
     * 参考：https://leetcode-cn.com/problems/count-primes/solution/ji-shu-zhi-shu-bao-li-fa-ji-you-hua-shai-fa-ji-you/
     */
    public static int solution2(int n) {
        if(n < 3) {
            return 0;
        }
        //从3开始验算，所以初始值为1（2为质数）。
        int count = 1;
        for (int i = 3; i < n; i++) {
            // 当某个数为 2 的 n 次方时（n为自然数），其 & (n - 1) 所得值将等价于取余运算所得值
            // *如果 x = 2^n ，则 x & (n - 1) == x % n
            // if(i % 2 == 0)
            if ((i & 1) == 0) {
                continue;
            }
            boolean sign = true;
            // 用 j * j <= i 代替 j <= √i 会更好。
            // 因为已经排除了所有偶数，所以每次循环加二将规避偶数会减少循环次数
            for (int j = 3; j * j <=i; j += 2) {
                if (i % j == 0) {
                    sign = false;
                    break;
                }
            }
            if (sign) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(10) = " + CountPrimes.solution1(10));
        LeetCodeUtil.logln("solution1(19) = " + CountPrimes.solution1(19));
        LeetCodeUtil.logln("solution2(9) = " + CountPrimes.solution2(9));
        LeetCodeUtil.logln("solution2(19) = " + CountPrimes.solution2(19));
    }
}
