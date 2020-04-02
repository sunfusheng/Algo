package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *【题目】
 * 119.杨辉三角 II
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 * <p>
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * <p>
 *【示例】
 * 输入: 3
 * 输出: [1,3,3,1]
 *
 *【进阶】
 * 可以优化你的算法到 O(k) 空间复杂度吗？
 *
 * @author liwangcheng
 * @date 2020/4/2.
 */
public class YangHuiTriangleII {

    public static List<Integer> solution1(int rowIndex) {
        Integer[] kRows = new Integer[rowIndex + 1];
        for (int i = 0; i <= rowIndex; i++) {
            // 行末尾为1
            kRows[i] = 1;
            for (int j = i; j > 1; j--) {
                // 每一行的更新过程
                kRows[j - 1] += kRows[j - 2];
            }
        }
        return Arrays.asList(kRows);
    }

    public static List<Integer> solution2(int rowIndex) {
        int pre = 1;
        List<Integer> cur = new ArrayList<>();
        cur.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i - 1; j > 0; j--) {
                cur.set(j, cur.get(j - 1) + cur.get(j));
            }
            // 补上每层的最后一个 1
            cur.add(1);
        }
        return cur;
    }

    public static List<Integer> solution3(int rowIndex) {
        List<Integer> ans = new ArrayList<>();
        int N = rowIndex;
        for (int k = 0; k <= N; k++) {
            ans.add(combination(N, k));
        }
        return ans;
    }

    private static int combination(int n, int k) {
        long res = 1;
        for (int i = 1; i <= k; i++) {
            res = res * (n - k + i) / i;
        }
        return (int) res;
    }

    public static List<Integer> solution4(int rowIndex) {
        List<Integer> ans = new ArrayList<>();
        int N = rowIndex;
        long pre = 1;
        ans.add(1);
        for (int k = 1; k <= N; k++) {
            long cur = pre * (N - k + 1) / k;
            ans.add((int) cur);
            pre = cur;
        }
        return ans;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("-5-\n" + YangHuiTriangleII.solution1(5));
        LeetCodeUtil.logln("-5-\n" + YangHuiTriangleII.solution2(5));
        LeetCodeUtil.logln("-5-\n" + YangHuiTriangleII.solution3(5));
        LeetCodeUtil.logln("-5-\n" + YangHuiTriangleII.solution4(5));
    }
}
