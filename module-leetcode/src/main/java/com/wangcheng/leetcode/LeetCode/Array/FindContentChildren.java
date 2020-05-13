package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.Arrays;

/**
 *【题目】
 * 455.分发饼干
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。
 * 但是，每个孩子最多只能给一块饼干。对每个孩子 i ，
 * 都有一个胃口值 gi ，这是能让孩子们满足胃口的饼干的
 * 最小尺寸；并且每块饼干 j ，都有一个尺寸 sj 。
 * 如果 sj >= gi ，可以将这个饼干 j 分配给孩子 i ，
 * 这个孩子会得到满足。你的目标是尽可能满足越多数量的
 * 孩子，并输出这个最大数值。
 * <p>
 * 注意：
 * <p>
 * 你可以假设胃口值为正。
 * 一个小朋友最多只能拥有一块饼干。
 * <p>
 *【示例】
 * 示例 1:
 * 输入: [1,2,3], [1,1]
 * 输出: 1
 * 解释:
 * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
 * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
 * 所以你应该输出1。
 *
 * 示例 2:
 * 输入: [1,2], [1,2,3]
 * 输出: 2
 * 解释:
 * 你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
 * 你拥有的饼干数量和尺寸都足以让所有孩子满足。
 * 所以你应该输出2.
 *
 * @author liwangcheng
 * @date 2020/5/13.
 */
public class FindContentChildren {

    /**
     * 方法一：排序比较
     * 思路
     * 将孩子的胃口和饼干的尺寸都从小到大排序好。分别从第一个孩子和
     * 第一块饼干开始比较，若这个孩子的胃口小于这块饼干的尺寸，则得
     * 到满足的孩子数+1，接着比较下一个孩子与下一块饼干。若这个孩子
     * 的胃口大于这块饼干，则拿下一块饼干的尺寸与这个孩子的胃口进行
     * 比较，直到找出比这个孩子的胃口大的饼干或者没有饼干了。
     */
    public static int solution1(int[] g, int[] s) {
        if (LeetCodeUtil.isEmpty(g) || LeetCodeUtil.isEmpty(s)) {
            return 0;
        }
        Arrays.sort(g);
        Arrays.sort(s);
        int num = 0;
        int j = 0;
        for (int i = 0; i < g.length; i++) {
            for (; j < s.length; j++) {
                if (g[i] <= s[j]) {
                    j++;
                    num++;
                    break;
                }
            }
        }
        return num;
    }

    /**
     * 方法二：贪心解法
     * 1.给一个孩子的饼干应当尽量小并且又能满足该孩子，
     *   这样大饼干才能拿来给满足度比较大的孩子。
     * 2.因为满足度最小的孩子最容易得到满足，所以先满足
     *   满足度最小的孩子。
     */
    public static int solution2(int[] g, int[] s) {
        if (LeetCodeUtil.isEmpty(g) || LeetCodeUtil.isEmpty(s)) {
            return 0;
        }
        Arrays.sort(g);
        Arrays.sort(s);
        int gi = 0;
        int sj = 0;
        while (gi < g.length && sj < s.length) {
            if (g[gi] <= s[sj]) {
                gi++;
            }
            sj++;
        }
        return gi;
    }

    public static void main(String[] args) {
        int[] g = new int[]{1,2,3};
        int[] s = new int[]{1,1};
        LeetCodeUtil.logln("solution1([1,2,3], [1,1]) = " + FindContentChildren.solution1(g, s));
        LeetCodeUtil.logln("solution2([1,2,3], [1,1]) = " + FindContentChildren.solution2(g, s));
        g = new int[]{1,2};
        s = new int[]{1,2,3};
        LeetCodeUtil.logln("solution1([1,2], [1,2,3]) = " + FindContentChildren.solution1(g, s));
        LeetCodeUtil.logln("solution2([1,2], [1,2,3]) = " + FindContentChildren.solution2(g, s));
    }
}
