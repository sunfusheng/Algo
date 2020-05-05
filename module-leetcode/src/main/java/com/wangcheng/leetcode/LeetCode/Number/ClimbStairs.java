package com.wangcheng.leetcode.LeetCode.Number;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 * 【题目】
 * 70.爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 【示例】
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * <p>
 * 示例 2：
 * 输入： 3
 * 输出： 3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 *
 * @author liwangcheng
 * @date 2020/3/26.
 */
public class ClimbStairs {

    /**
     * 方法一：暴力法
     * 算法
     * 在暴力法中，将会把所有可能爬的阶数进行组合，
     * 也就是 1 和 2 。而在每一步中都会继续调用
     * climbStairs 这个函数模拟爬 1 阶和 2 阶的情形，
     * 并返回两个函数的返回值之和。
     * <p>
     * 复杂度分析
     * 时间复杂度：O(2^n)。
     * 空间复杂度：O(n)，递归树的深度可以达到 n。
     */
    public static int solution1(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        return solution1(n - 1) + solution1(n - 2);
    }

    /**
     * 方法二：记忆化递归
     * 算法
     * 在上一种方法中，计算每一步的结果时出现了冗余。
     * 另一种思路是，可以把每一步的结果存储在 memo 数组之中，
     * 每当函数再次被调用，我们就直接从 memo 数组返回结果。
     * 在 memo 数组的帮助下，得到了一个修复的递归树，其大小减少到 n。
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n)，树形递归的大小可以达到 n。
     * 空间复杂度：O(n)，递归树的深度可以达到 n。
     */
    public static int solution2(int x) {
        int[] memo = new int[x + 1];
        return climbStairs(0, x, memo);
    }

    private static int climbStairs(int i, int n, int[] memo) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (memo[i] > 0) {
            return memo[i];
        }
        return memo[i] = climbStairs(i + 1, n, memo) + climbStairs(i + 2, n, memo);
    }

    /**
     * 方法三：动态规划
     * 这个问题可以被分解为一些包含最优子结构的子问题，
     * 即它的最优解可以从其子问题的最优解来有效地构建，
     * 可以使用动态规划来解决这一问题。
     * <p>
     * 第 i 阶可以由以下两种方法得到：
     * 在第 (i-1) 阶后向上爬一阶。
     * 在第 (i-2) 阶后向上爬 2 阶。
     * 所以到达第 i 阶的方法总数就是到第 (i−1) 阶和第 (i−2) 阶的方法数之和。
     * 令 dp[i] 表示能到达第 i 阶的方法总数：
     * dp[i] = dp[i-1] + dp[i-2]
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n)，单循环到 n。
     * 空间复杂度：O(n)，dp 数组用了 n 的空间。
     */
    public static int solution3(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 方法四：斐波那契数
     * 算法
     * 在上述方法中，使用 dp 数组，其中 dp[i] = dp[i-1] + dp[i-2]。
     * 可以很容易通过分析得出 dp[i] 其实就是第 i 个斐波那契数。
     * <p>
     * Fib(n)=Fib(n-1)+Fib(n-2)
     * <p>
     * 现在必须找出以 1 和 2 作为第一项和第二项的斐波那契数列中的第 n 个数，
     * 也就是说 Fib(1) = 1 且 Fib(2) = 2。
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n)，单循环到 n，需要计算第 n 个斐波那契数。
     * 空间复杂度：O(1)，使用常量级空间。
     */
    public static int solution4(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        int first = 1;
        int second = 2;
        int third = 3;
        for (int i = 3; i <= n; i++) {
            third = first + second;
            first = second;
            second = third;
        }
        return third;
    }

    /**
     * 方法五：Binets 方法
     * 算法
     * 这里有一种有趣的解法，它使用矩阵乘法来得到第 n 个斐波那契数。
     * <p>
     * 复杂度分析
     * 时间复杂度：O(log(n))，遍历 log(n) 位。
     * 空间复杂度：O(1)，使用常量级空间。
     * <p>
     * 【说明】
     * 感兴趣的可以了解一下
     * 链接：https://leetcode-cn.com/problems/climbing-stairs/solution/pa-lou-ti-by-leetcode/
     */
    public static int solution5(int n) {
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n);
        return res[0][0];
    }

    private static int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    private static int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }

    /**
     * 方法六：斐波那契公式
     * <p>
     * 复杂度分析
     * 时间复杂度：O(log(n))，pow 方法将会用去 log(n) 的时间。
     * 空间复杂度：O(1)，使用常量级空间。
     */
    public static int solution6(int n) {
        double sqrt5 = Math.sqrt(5);
        double fibn = Math.pow((1 + sqrt5) / 2, n + 1) - Math.pow((1 - sqrt5) / 2, n + 1);
        return (int) (fibn / sqrt5);
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(5) = " + solution1(5));
        LeetCodeUtil.logln("solution2(5) = " + solution2(5));
        LeetCodeUtil.logln("solution3(5) = " + solution3(5));
        LeetCodeUtil.logln("solution4(5) = " + solution4(5));
        LeetCodeUtil.logln("solution5(5) = " + solution5(5));
        LeetCodeUtil.logln("solution6(5) = " + solution6(5));
    }
}