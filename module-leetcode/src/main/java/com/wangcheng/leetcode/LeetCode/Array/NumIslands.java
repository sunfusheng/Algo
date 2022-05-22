package com.wangcheng.leetcode.LeetCode.Array;

/**
 * 【题目】
 * 200.岛屿数量
 * <p>
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * 示例 1：
 * 输入：grid = {
 *      {'1', '1', '1', '1', '0'},
 *      {'1', '1', '0', '1', '0'},
 *      {'1', '1', '0', '0', '0'},
 *      {'0', '0', '0', '0', '0'}
 * };
 * 输出：1
 * <p>
 * 示例 2：
 * 输入：grid2 = {
 *      {'1', '1', '0', '0', '0'},
 *      {'1', '1', '0', '0', '0'},
 *      {'0', '0', '1', '0', '0'},
 *      {'0', '0', '0', '1', '1'}
 * };
 * 输出：3
 *
 * @author sunfusheng
 * @since 2020/5/8
 */
public class NumIslands {

    /**
     * 方法一：深度优先遍历
     * <p>
     * 时间复杂度：O(M*N)，其中 M 和 N 分别为行数和列数。
     * 空间复杂度：O(M*N)，在最坏情况下，整个网格均为陆地，深度优先搜索的深度达到 M*N。
     */
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int res = 0;
        int nr = grid.length;
        int nc = grid[0].length;
        for (int r = 0; r < nr; r++) {
            for (int c = 0; c < nc; c++) {
                if (grid[r][c] == '1') {
                    res++;
                    dfs(grid, r, c);
                }
            }
        }
        return res;
    }

    private static void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;
        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }
        grid[r][c] = '0';
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println("方法一输出1：" + numIslands(grid));
        System.out.println("方法一输出2：" + numIslands(grid2));
    }
}
