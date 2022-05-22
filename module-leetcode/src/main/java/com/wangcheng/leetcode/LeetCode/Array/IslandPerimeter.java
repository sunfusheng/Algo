package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 * 【题目】
 * 463.岛屿的周长
 * <p>
 * 给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
 * 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，
 * 但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * <p>
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。
 * 格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 * <p>
 * 示例 1：
 * 输入：grid = {
 *      {0, 1, 0, 0},
 *      {1, 1, 1, 0},
 *      {0, 1, 0, 0},
 *      {1, 1, 0, 0}
 *  }
 * 输出：16
 * 解释：它的周长是上面图片中的 16 个黄色的边
 * <p>
 * 示例 2：
 * 输入：grid = [[1]]
 * 输出：4
 * <p>
 * 示例 3：
 * 输入：grid = [[1,0]]
 * 输出：4
 *
 * @author liwangcheng
 * @date 2020/5/14.
 */
public class IslandPerimeter {

    /**
     * 方法一：双重循环
     * 思路：
     * 小精灵落到格子上，立刻在自己四周围起围栏来，随后它环顾四周，
     * 如果发现和别的小精灵相邻，就拆掉自己这边的围栏。
     */
    public static int solution1(int[][] grid) {
        int land = 1;
        int sea = 0;
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == land) {
                    ans += 4;
                    // look up
                    if (i != 0) {
                        if (grid[i - 1][j] == land) {
                            ans--;
                        }
                    }
                    // look down
                    if (i != grid.length - 1) {
                        if (grid[i + 1][j] == land) {
                            ans--;
                        }
                    }
                    // look left
                    if (j != 0) {
                        if (grid[i][j - 1] == land) {
                            ans--;
                        }
                    }
                    // look right
                    if (j != grid[0].length - 1) {
                        if (grid[i][j + 1] == land) {
                            ans--;
                        }
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 方法二：DFS 遍历
     * 思路：
     * 如果用 DFS 遍历来求的话，有一种很简单的思路：岛屿的周长
     * 就是岛屿方格和非岛屿方格相邻的边的数量。注意，这里的非岛
     * 屿方格，既包括水域方格，也包括网格的边界。
     * <p>
     * 将这个“相邻关系”对应到 DFS 遍历中，就是：每当在 DFS 遍
     * 历中，从一个岛屿方格走向一个非岛屿方格，就将周长加 1。
     */
    public static int solution2(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    // 题目限制只有一个岛屿，计算一个即可
                    return dfs(grid, i, j);
                }
            }
        }
        return 0;
    }

    private static int dfs(int[][] grid, int r, int c) {
        // 从一个岛屿方格走向网格边界，周长加 1
        if (!(0 <= r && r < grid.length && 0 <= c && c < grid[0].length)) {
            return 1;
        }
        // 从一个岛屿方格走向水域方格，周长加 1
        if (grid[r][c] == 0) {
            return 1;
        }
        if (grid[r][c] != 1) {
            return 0;
        }
        grid[r][c] = 2;
        return dfs(grid, r - 1, c)
                + dfs(grid, r + 1, c)
                + dfs(grid, r, c - 1)
                + dfs(grid, r, c + 1);
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}
        };
        LeetCodeUtil.logln("solution1() = " + IslandPerimeter.solution1(grid));
        LeetCodeUtil.logln("solution2() = " + IslandPerimeter.solution2(grid));
    }
}
