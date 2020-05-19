package com.wangcheng.leetcode.LeetCode.Interview;

import com.sunfusheng.algo.common.util.AlgoUtil;

/**
 * 【题目】
 * 01.07. 旋转矩阵
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 * <p>
 * 不占用额外内存空间能否做到？
 * <p>
 * 示例 1:
 * 给定 matrix =
 * [
 * - [1,2,3],
 * - [4,5,6],
 * - [7,8,9]
 * ],
 * 原地旋转输入矩阵，使其变为:
 * [
 * - [7,4,1],
 * - [8,5,2],
 * - [9,6,3]
 * ]
 * <p>
 * 示例 2:
 * 给定 matrix =
 * [
 * - [ 5, 1, 9,11],
 * - [ 2, 4, 8,10],
 * - [13, 3, 6, 7],
 * - [15,14,12,16]
 * ],
 * 原地旋转输入矩阵，使其变为:
 * [
 * - [15,13, 2, 5],
 * - [14, 3, 4, 1],
 * - [12, 6, 8, 9],
 * - [16, 7,10,11]
 * ]
 *
 * @author sunfusheng
 * @since 2020/5/11
 */
public class RotateMatrix {

    /**
     * 方法一：原地旋转
     * <p>
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(1)
     *
     * @param matrix
     */
    public static void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }

        int N = matrix.length;
        // 外层循环遍历到中间位置，奇数边长包含中间位置
        for (int i = 0; i < (N + 1) / 2; i++) {
            // 内层循环遍历到中间位置，奇数边长不包含中间位置
            for (int j = 0; j < N / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[N - j - 1][i];
                matrix[N - j - 1][i] = matrix[N - i - 1][N - j - 1];
                matrix[N - i - 1][N - j - 1] = matrix[j][N - i - 1];
                matrix[j][N - i - 1] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println("输入1：");
        AlgoUtil.printArray2(matrix);
        rotate(matrix);
        System.out.println("输出1：");
        AlgoUtil.printArray2(matrix);

        int[][] matrix2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        System.out.println("输入2：");
        AlgoUtil.printArray2(matrix2);
        rotate(matrix2);
        System.out.println("输出2：");
        AlgoUtil.printArray2(matrix2);
    }
}
