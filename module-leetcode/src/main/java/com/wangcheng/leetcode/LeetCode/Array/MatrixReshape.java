package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *【题目】
 * 566.重塑矩阵
 * 在MATLAB中，有一个非常有用的函数 reshape，它可以将一
 * 个矩阵重塑为另一个大小不同的新矩阵，但保留其原始数据。
 * <p>
 * 给出一个由二维数组表示的矩阵，以及两个正整数r和c，分别
 * 表示想要的重构的矩阵的行数和列数。
 * <p>
 * 重构后的矩阵需要将原始矩阵的所有元素以相同的行遍历顺序填充。
 * <p>
 * 如果具有给定参数的reshape操作是可行且合理的，则输出新
 * 的重塑矩阵；否则，输出原始矩阵。
 * <p>
 *【示例】
 * 示例 1:
 * 输入:
 * nums =
 * [[1,2], [3,4]]
 * r = 1, c = 4
 * 输出:
 * [[1,2,3,4]]
 * 解释:
 * 行遍历nums的结果是 [1,2,3,4]。新的矩阵是 1 * 4 矩阵,
 * 用之前的元素值一行一行填充新矩阵。
 *
 * 示例 2:
 * 输入:
 * nums =
 * [[1,2], [3,4]]
 * r = 2, c = 4
 * 输出:
 * [[1,2], [3,4]]
 * 解释:
 * 没有办法将 2 * 2 矩阵转化为 2 * 4 矩阵。 所以输出原矩阵。
 *【注意】
 * 给定矩阵的宽和高范围在 [1, 100]。
 * 给定的 r 和 c 都是正数。
 *
 * @author liwangcheng
 * @date 2020/6/1.
 */
public class MatrixReshape {

    /**
     * 方法一 使用队列
     * 算法
     * 最简单的方法是通过以行方式读取元素来提取给定矩阵的
     * 所有元素。在此实现中，使用队列来放置提取的元素。然后，
     * 可以取出以串行顺序形成的队列元素，并再次按行逐行排
     * 列所得到的所需矩阵中的元素。
     *
     * 如果原始矩阵中的元素数量不等于所得矩阵中的元素数量，
     * 则不可能形成所得矩阵。
     *
     * 复杂度分析
     * 时间复杂度：O(m∗n)。遍历 m * n 元素两次。
     *  这里，m 和 n 分别表示给定矩阵的行数和列数。
     * 空间复杂度：O(m∗n)。形成的队列大小为 m*n。
     */
    public static int[][] solution1(int[][] nums, int r, int c) {
        if (null == nums || nums.length == 0 || r * c != nums.length * nums[0].length) {
            return nums;
        }
        int[][] res = new int[r][c];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                queue.add(nums[i][j]);
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                res[i][j] = queue.remove();
            }
        }
        return res;
    }

    /**
     * 方法二 不用额外空间
     * 算法
     * 不必像在暴力方法中那样不必要地使用队列，而是
     * 可以在逐行顺序迭代给定矩阵的同时，直接将数字
     * 放在结果矩阵中。在将数字放入结果数组时，固定
     * 一个特定的行，并继续增加列数，直到到达c指示
     * 的所需列的末尾。此时，通过递增来更新行索引，
     * 并将列索引重置为从0开始。因此，可以节省队列
     * 消耗的空间，以便存储只需要复制到新数组中的数据。
     *
     * 复杂度分析
     * 时间复杂度：O(m∗n)。只遍历整个矩阵 m*n。
     *  这里，m 和 n 指的是给定矩阵中的行数和列数。
     * 空间复杂度：O(m∗n)。使用大小为 m*n 的结果矩阵。
     */
    public static int[][] solution2(int[][] nums, int r, int c) {
        if (null == nums || nums.length == 0 || r * c != nums.length * nums[0].length) {
            return nums;
        }
        int[][] res = new int[r][c];
        int rows = 0;
        int cols = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                res[rows][cols++] = nums[i][j];
                if (cols == c) {
                    cols = 0;
                    rows++;
                }
            }
        }
        return res;
    }

    /**
     * 方法三 除法和取模
     * 算法
     * 在上一种方法中，需要跟踪何时到达结果矩阵的列的末尾，
     * 并且需要通过每次检查当前索引来更新当前行和列号以放
     * 置提取的元素。可以利用数学来帮助解决，而不是在每一
     * 步都进行限制性检查。
     *
     * 复杂度分析
     * 时间复杂度：O(m∗n)。只遍历整个矩阵 m*n。这里，
     *  m 和 n 指的是给定矩阵中的行数和列数。
     * 空间复杂度：O(m∗n)。使用大小为 m*n 的矩阵存储结果。
     */
    public static int[][] solution3(int[][] nums, int r, int c) {
        if (null == nums || nums.length == 0 || r * c != nums.length * nums[0].length) {
            return nums;
        }
        int[][] res = new int[r][c];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                res[count/c][count%c] = nums[i][j];
                count++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{
                new int[]{1,2},
                new int[]{3,4}
        };
        LeetCodeUtil.logln(Arrays.deepToString(nums));
        LeetCodeUtil.logln("solution1() = " + Arrays.deepToString(MatrixReshape.solution1(nums, 1, 4)));
        LeetCodeUtil.logln("solution1() = " + Arrays.deepToString(MatrixReshape.solution2(nums, 1, 4)));
        LeetCodeUtil.logln("solution1() = " + Arrays.deepToString(MatrixReshape.solution3(nums, 1, 4)));
    }
}
