package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 509.斐波那契数
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。
 * 该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。
 * <p>
 * 也就是：
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 给定 N，计算 F(N)。
 * <p>
 *【示例】
 * 示例 1：
 * 输入：2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1.
 * <p>
 * 示例 2：
 * 输入：3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2.
 * <p>
 * 示例 3：
 * 输入：4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3.
 * <p>
 * 提示：
 * 0 ≤ N ≤ 30
 *
 * @author liwangcheng
 * @date 2020/5/21.
 */
public class Fibonacci {

    /**
     * 方法一：递归
     * 使用递归计算给定整数的斐波那契数。
     *
     * 算法：
     * - 检查整数 N，如果 N 小于等于 1，则返回 N。
     * - 否则，通过递归关系：F(n) = F(n-1) + F(n-2) 调用自身。
     * - 直到所有计算返回结果得到答案。
     *
     * 复杂度分析
     * 时间复杂度：O(2^N)。这是计算斐波那契数最慢的方法。因为它需要指数的时间。
     * 空间复杂度：O(N)，在堆栈中我们需要与 N 成正比的空间大小。
     *  该堆栈跟踪 fib(N) 的函数调用，随着堆栈的不断增长如果没有足够的内存则会
     *  导致 StackOverflowError。
     */
    public static int solution1(int N) {
        if (N <= 1) {
            return N;
        }
        return fib1(N);
    }

    private static int fib1(int N) {
        if (N <= 1) {
            return N;
        }
        return fib1(N - 1) + fib1(N - 2);
    }

    /**
     * 方法二：记忆化自底向上的方法
     * 自底向上通过迭代计算斐波那契数的子问题并存储已计算的值，
     * 通过已计算的值进行计算。减少递归带来的重复计算。
     *
     * 算法：
     * - 如果 N 小于等于 1，则返回 N。
     * - 迭代 N，将计算出的答案存储在数组中。
     * - 使用数组前面的两个斐波那契数计算当前的斐波那契数。
     * - 知道我们计算到 N，则返回它的斐波那契数。
     *
     * 复杂度分析
     * 时间复杂度：O(N)。
     * 空间复杂度：O(N)，使用了空间大小为 N 的数组。
     */
    public static int solution2(int N) {
        if (N <= 1) {
            return N;
        }
        return memoize2(N);
    }

    private static int memoize2(int N) {
        int[] cache = new int[N + 1];
        cache[1] = 1;
        for (int i = 2; i <= N; i++) {
            cache[i] = cache[i - 1] + cache[i - 2];
        }
        return cache[N];
    }

    /**
     * 方法三：记忆化自顶向下的方法
     * 先计算存储子问题的答案，然后利用子问题的答案计算当前斐波那契数的答案。
     * 将递归计算，但是通过记忆化不重复计算已计算的值。
     *
     * 算法：
     * - 如果 N <= 1，则返回 N。
     * - 调用和返回 memoize(N)。
     * - 如果 N 对应的斐波那契数存在，则返回。
     * - 否则将计算 N 对应的斐波那契数为 memoize(N-1) + memoize(N-2)。
     *
     * 复杂度分析
     * 时间复杂度：O(N)。
     * 空间复杂度：O(N)，内存中使用的堆栈大小。
     */
    private static Integer[] cache = new Integer[31];
    public static int solution3(int N) {
        if (N <= 1) {
            return 1;
        }
        cache[0] = 0;
        cache[1] = 1;
        return memoize3(N);
    }

    private static int memoize3(int N) {
        if (cache[N] != null) {
            return cache[N];
        }
        cache[N] = memoize3(N - 1) + memoize3(N - 2);
        return cache[N];
    }

    /**
     * 方法四：自底向上进行迭代
     * 算法：
     * - 若 N <= 1，则返回 N。
     * - 若 N == 2，则返回 fib(2-1) + fib(2-2) = 1。
     * - 使用迭代的方法，至少需要三个变量存储 fib(N), fib(N-1) 和 fib(N-2)。
     * - 预置初始值：
     *   - current = 0。
     *   - prev1 = 1，代表 fib(N-1)。
     *   - prev2 = 1，代表 fib(N-2)
     * - 从 3 计算到 N；0，1，2对应的斐波那契数是预先计算。
     * - 设置 current = fib(N-1) + fib(N-2)，因为 current 代表的是当前计算的斐波那契数。
     * - 设置 prev2 = fib(N-1)。
     * - 设置 prev1 = current。
     * - 当到达 N+1，将退出循环并返回 current。
     *
     * 复杂度分析
     * 空间复杂度：O(1)，仅仅使用了 current，prev1，prev2。
     */
    public static int solution4(int N) {
        if (N <= 1) {
            return N;
        }
        if (N == 2) {
            return 1;
        }
        int current = 0;
        int prev1 = 1;
        int prev2 = 1;
        for (int i = 3; i <= N; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }

    /**
     * TODO
     * 方法五：公式法
     *
     * 复杂度分析
     * 时间复杂度：O(1)。常数的时间复杂度，因为是基于 Binet 公式进行计算，没有使用循环或递归。
     * 空间复杂度：O(1)，存储黄金分割率所使用的空间。
     */
    public static int solution5(int N) {
        double goldenRatio = (1 + Math.sqrt(5)) / 2;
        return (int)Math.round(Math.pow(goldenRatio, N)/ Math.sqrt(5));
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(3) = " + Fibonacci.solution1(3));
        LeetCodeUtil.logln("solution1(5) = " + Fibonacci.solution1(5));
        LeetCodeUtil.logln("solution1(7) = " + Fibonacci.solution1(7));
        LeetCodeUtil.logln("solution1(9) = " + Fibonacci.solution1(9));
        LeetCodeUtil.logln("solution1(19) = " + Fibonacci.solution1(19));
        LeetCodeUtil.logln("solution1(27) = " + Fibonacci.solution1(27));
        LeetCodeUtil.logln("solution1(30) = " + Fibonacci.solution1(30));
        LeetCodeUtil.logln("solution2(3) = " + Fibonacci.solution2(3));
        LeetCodeUtil.logln("solution2(5) = " + Fibonacci.solution2(5));
        LeetCodeUtil.logln("solution2(7) = " + Fibonacci.solution2(7));
        LeetCodeUtil.logln("solution2(9) = " + Fibonacci.solution2(9));
        LeetCodeUtil.logln("solution2(19) = " + Fibonacci.solution2(19));
        LeetCodeUtil.logln("solution2(27) = " + Fibonacci.solution2(27));
        LeetCodeUtil.logln("solution2(30) = " + Fibonacci.solution2(30));
        LeetCodeUtil.logln("solution3(3) = " + Fibonacci.solution3(3));
        LeetCodeUtil.logln("solution3(5) = " + Fibonacci.solution3(5));
        LeetCodeUtil.logln("solution3(7) = " + Fibonacci.solution3(7));
        LeetCodeUtil.logln("solution3(9) = " + Fibonacci.solution3(9));
        LeetCodeUtil.logln("solution3(19) = " + Fibonacci.solution3(19));
        LeetCodeUtil.logln("solution3(27) = " + Fibonacci.solution3(27));
        LeetCodeUtil.logln("solution3(30) = " + Fibonacci.solution3(30));
        LeetCodeUtil.logln("solution4(3) = " + Fibonacci.solution4(3));
        LeetCodeUtil.logln("solution4(5) = " + Fibonacci.solution4(5));
        LeetCodeUtil.logln("solution4(7) = " + Fibonacci.solution4(7));
        LeetCodeUtil.logln("solution4(9) = " + Fibonacci.solution4(9));
        LeetCodeUtil.logln("solution4(19) = " + Fibonacci.solution4(19));
        LeetCodeUtil.logln("solution4(27) = " + Fibonacci.solution4(27));
        LeetCodeUtil.logln("solution4(30) = " + Fibonacci.solution4(30));
        LeetCodeUtil.logln("solution5(3) = " + Fibonacci.solution5(3));
        LeetCodeUtil.logln("solution5(5) = " + Fibonacci.solution5(5));
        LeetCodeUtil.logln("solution5(7) = " + Fibonacci.solution5(7));
        LeetCodeUtil.logln("solution5(9) = " + Fibonacci.solution5(9));
        LeetCodeUtil.logln("solution5(19) = " + Fibonacci.solution5(19));
        LeetCodeUtil.logln("solution5(27) = " + Fibonacci.solution5(27));
        LeetCodeUtil.logln("solution5(30) = " + Fibonacci.solution5(30));
    }
}
