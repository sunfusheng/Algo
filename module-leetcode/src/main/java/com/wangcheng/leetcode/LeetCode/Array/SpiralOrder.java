package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.AlgoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 【题目】
 * 54.螺旋矩阵
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * <p>
 * 示例 1:
 * 输入:
 * [
 * - [ 1, 2, 3 ],
 * - [ 4, 5, 6 ],
 * - [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * <p>
 * 示例 2:
 * 输入:
 * [
 * - [1, 2, 3, 4],
 * - [5, 6, 7, 8],
 * - [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * @author sunfusheng
 * @since 2020/5/8
 */
public class SpiralOrder {

    /**
     * 方法一：按层遍历
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param matrix
     * @return
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return null;
        }

        List<Integer> res = new ArrayList<>();
        int r1 = 0;
        int r2 = matrix[0].length - 1;
        int c1 = 0;
        int c2 = matrix.length - 1;
        while (r1 <= r2 && c1 <= c2) {
            for (int i = r1; i <= r2; i++)
                res.add(matrix[c1][i]);
            for (int i = c1 + 1; i <= c2; i++)
                res.add(matrix[i][r2]);
            if (r1 < r2 && c1 < c2) {
                for (int i = r2 - 1; i >= r1; i--)
                    res.add(matrix[c2][i]);
                for (int i = c2 - 1; i > c1; i--)
                    res.add(matrix[i][r1]);
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] matrix2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        System.out.print("方法一输出1：");
        AlgoUtil.printlnList(spiralOrder(matrix));
        System.out.print("方法一输出2：");
        AlgoUtil.printlnList(spiralOrder(matrix2));
    }
}
