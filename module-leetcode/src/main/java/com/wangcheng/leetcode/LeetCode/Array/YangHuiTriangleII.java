package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TODO
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

    /**
     * 方法一：动态规划
     * 总的来说就是利用杨辉三角形后一行与前一行的关系。
     * 更新过程为：
     * 从倒数第二个元素开始往前更新，它等于原来这个位置的数 + 前一个位置的数
     * 行[i] = 行[i] + 行[i-1]
     */
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

    /**
     * 参考：
     * https://leetcode-cn.com/problems/pascals-triangle-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--28/
     */
    public static List<Integer> solution2(int rowIndex) {
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

    /**
     * 只需要一层一层的求。但是不需要把每一层的结果都保存起来，
     * 只需要保存上一层的结果，就可以求出当前层的结果了。
     */
    public static List<Integer> solution2_0(int rowIndex) {
        List<Integer> pre = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            cur = new ArrayList<>();
            cur.add(1);
            for (int j = 1; j < i; j++) {
                cur.add(pre.get(j - 1) + pre.get(j));
            }
            cur.add(1);
            pre = cur;
        }
        return cur;
    }

    /**
     * 可以优化一下，可以把 pre 的 List 省去。
     * 这样的话，cur每次不去新建 List，而是把cur当作pre。
     * 又因为更新当前j的时候，就把之前j的信息覆盖掉了。
     * 而更新 j + 1 的时候又需要之前j的信息，所以在更新前，
     * 需要一个变量把之前j的信息保存起来。
     */
    public static List<Integer> solution2_1(int rowIndex) {
        int pre = 1;
        List<Integer> cur = new ArrayList<>();
        cur.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = 1; j < i; j++) {
                int temp = cur.get(j);
                cur.set(j, pre + cur.get(j));
                pre = temp;
            }
            cur.add(1);
        }
        return cur;
    }

    /**
     * 还有一种想法，那就是倒着进行，这样就不会存在覆盖的情况了。
     * 因为更新完j的信息后，虽然把j之前的信息覆盖掉了。
     * 但是下一次更新的是j - 1，需要的是j - 1和j - 2 的信息，
     * j信息覆盖就不会造成影响了。
     */
    public static List<Integer> solution2_2(int rowIndex) {
        List<Integer> cur = new ArrayList<>();
        cur.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i - 1; j > 0; j--) {
                cur.set(j, cur.get(j - 1) + cur.get(j));
            }
            cur.add(1);
        }
        return cur;
    }

    /**
     * TODO
     * 公式法
     * 杨辉三角其实可以看做由组合数构成
     */
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

    /**
     * TODO
     * 公式法优化
     * 上边的算法对于每个组合数我们都重新求了一遍，但事实上前后的组合数其实是有联系的
     * 只需要用pre变量保存上一次的组合数结果
     */
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
        LeetCodeUtil.logln("1 -5-\n" + YangHuiTriangleII.solution1(5));
        LeetCodeUtil.logln("2 -5-\n" + YangHuiTriangleII.solution2(5));
        LeetCodeUtil.logln("2_0 -5-\n" + YangHuiTriangleII.solution2_0(5));
        LeetCodeUtil.logln("2_1 -5-\n" + YangHuiTriangleII.solution2_1(5));
        LeetCodeUtil.logln("2_2 -5-\n" + YangHuiTriangleII.solution2_2(5));
        LeetCodeUtil.logln("3 -5-\n" + YangHuiTriangleII.solution3(5));
        LeetCodeUtil.logln("4 -5-\n" + YangHuiTriangleII.solution4(5));
    }
}
