package com.wangcheng.leetcode.LeetCode.String;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 *【题目】
 * 28. 实现 strStr()
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * <p>
 * 当 needle 是空字符串时返回 0
 * <p>
 *【示例】
 * 示例 1:
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 *
 * 示例 2:
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 *
 * @author liwangcheng
 * @date 2020/3/20.
 */
public class ImplStrStr {

    /**
     * 方法一：子串逐一比较 - 线性时间复杂度
     * 最直接的方法 - 沿着字符换逐步移动滑动窗口，将窗口内的子串与 needle 字符串比较。
     *
     * 复杂度分析
     * 时间复杂度：O((N - L)L)，其中 N 为 haystack 字符串的长度，L 为 needle 字符串的长度。内循环中比较字符串的复杂度为 L，总共需要比较 (N - L) 次。
     * 空间复杂度：O(1)。
     */
    public static int solution1(String haystack, String needle) {
        int n = haystack.length();
        int L = needle.length();
        if (check(n, L)) {
            return -1;
        }
        for (int i = 0; i < n - L + 1; i++) {
            if (haystack.substring(i, i + L).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean check(int n, int l) {
        if (n == 0 || l == 0 || l > n) {
            return true;
        }
        return false;
    }

    /**
     * 方法二：双指针 - 线性时间复杂度
     * 首先，只有子串的第一个字符跟 needle 字符串第一个字符相同的时候才需要比较。
     * 其次，可以一个字符一个字符比较，一旦不匹配了就立刻终止。
     *
     * 复杂度分析
     * 时间复杂度：最坏时间复杂度为 O((N−L)L)，最优时间复杂度为 O(N)。
     * 空间复杂度：O(1)。
     */
    public static int solution2(String haystack, String needle) {
        int n = haystack.length();
        int L = needle.length();
        if (check(n, L)) {
            return -1;
        }
        // pn 指针在 haystack 上移动
        int pn = 0;
        while (pn < n - L + 1) {
            // 在haystack中找到与needle第一个字符匹配的字符的位置
            while (pn < n - L + 1 && haystack.charAt(pn) != needle.charAt(0)) {
                ++pn;
            }
            // 计算最大匹配的字符串
            int currLen = 0;
            // pL指针在 needle 上移动
            int pL = 0;
            while (pL < L && pn < n  && haystack.charAt(pn) == needle.charAt(pL)) {
                pL++;
                pn++;
                currLen++;
            }
            // 完全匹配 needle
            if (currLen == L) {
                return pn - L;
            }
            pn = pn - currLen + 1;
        }
        return -1;
    }

    /**
     * 方法三： Rabin Karp - 常数复杂度
     * 思路
     * 先生成窗口内子串的哈希码，然后再跟 needle 字符串的哈希码做比较。
     *
     * 有一个问题需要解决，如何在常数时间生成子串的哈希码？
     *
     * 滚动哈希：常数时间生成哈希码
     *
     * 复杂度分析
     * 时间复杂度：O(N)，计算 needle 字符串的哈希值需要 O(L) 时间，之后需要执行 (N - L) 次循环，每次循环的计算复杂度为常数。
     * 空间复杂度：O(1)。
     */
    public static int solution3(String haystack, String needle) {
        int n = haystack.length();
        int L = needle.length();
        if (check(n, L)) {
            return -1;
        }
        // base value for the rolling hash function
        int a = 26;
        // modulus value for the rolling hash function to avoid overflow
        long modulus = (long)Math.pow(2, 31);

        // compute the hash of strings haystack[:L], needle[:L]
        long h = 0, ref_h = 0;
        for (int i = 0; i < L; ++i) {
            h = (h * a + charToInt(i, haystack)) % modulus;
            ref_h = (ref_h * a + charToInt(i, needle)) % modulus;
        }
        if (h == ref_h) {
            return 0;
        }

        // const value to be used often : a**L % modulus
        long aL = 1;
        for (int i = 1; i <= L; ++i) {
            aL = (aL * a) % modulus;
        }

        for (int start = 1; start < n - L + 1; ++start) {
            // compute rolling hash in O(1) time
            h = (h * a - charToInt(start - 1, haystack) * aL
                    + charToInt(start + L - 1, haystack)) % modulus;
            if (h == ref_h) {
                return start;
            }
        }
        return -1;
    }

    /**
     * function to convert character to integer
     */
    private static int charToInt(int idx, String s) {
        return (int)s.charAt(idx) - (int)'a';
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution1(hello, ll) = " + ImplStrStr.solution1("hello", "ll"));
        LeetCodeUtil.logln("solution1(aaaaa, bba) = " + ImplStrStr.solution1("aaaaa", "bba"));
        LeetCodeUtil.logln("solution1(imissyou, a) = " + ImplStrStr.solution1("imissyou", "a"));
        LeetCodeUtil.logln("solution2(hello, ll) = " + ImplStrStr.solution2("hello", "ll"));
        LeetCodeUtil.logln("solution2(aaaaa, bba) = " + ImplStrStr.solution2("aaaaa", "bba"));
        LeetCodeUtil.logln("solution2(imissyou, a) = " + ImplStrStr.solution1("imissyou", "a"));
        LeetCodeUtil.logln("solution3(hello, ll) = " + ImplStrStr.solution3("hello", "ll"));
        LeetCodeUtil.logln("solution3(aaaaa, bba) = " + ImplStrStr.solution3("aaaaa", "bba"));
        LeetCodeUtil.logln("solution3(imissyou, a) = " + ImplStrStr.solution1("imissyou", "a"));
    }
}
