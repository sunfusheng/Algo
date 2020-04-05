package com.sunfusheng.algo.Algo.StackQueue;

import com.sunfusheng.algo.common.util.AlgoUtil;

import java.util.Stack;

/**
 * 【题目】
 * 求最大子矩阵的大小
 * <p>
 * 给定一个整型矩阵map，其中的值只有0和1两种，求其中全是1的所有矩形区域中，
 * 最大的矩形区域为1的数量。
 * <p>
 * 例如：
 * {
 * -- {1, 1, 1, 0}
 * }
 * 其中最大的矩形区域有3个1，所以返回3
 * <p>
 * 再如：
 * {
 * -- {1, 0, 1, 1}
 * -- {1, 1, 1, 1}
 * -- {1, 1, 1, 0}
 * }
 * 其中最大的矩形区域有6个1，所以返回6
 *
 * @author sunfusheng
 * @since 2020/4/5
 */
public class MaxRecSize {

    /**
     * 解法步骤如下：
     * 1、把矩阵每一行切割，统计以当前行为底的情况下，每个位置往上1的数量。
     * 切割后如下：
     * {
     * -- {1, 0, 1, 1}
     * -- {2, 1, 2, 2}
     * -- {3, 2, 3, 0}
     * }
     * 2、利用单调栈的特性求出每行每个高度最大可以扩展的矩形大小
     *
     * @param map 输入的矩阵
     * @return
     */
    public static int getMaxRecSize(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int maxRecSize = 0;
        int[] height = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
            }
            maxRecSize = Math.max(getMaxRecSizeFromHeight(height), maxRecSize);
        }
        return maxRecSize;
    }

    /**
     * 利用单调栈的特性求出每行每个高度最大可以扩展的矩形大小
     *
     * @param height 输入矩阵切割后每行的高度
     * @return
     */
    private static int getMaxRecSizeFromHeight(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxRecSize = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int curMaxRecSize = (i - k - 1) * height[j];
                maxRecSize = Math.max(curMaxRecSize, maxRecSize);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int curMaxRecSize = (height.length - k - 1) * height[j];
            maxRecSize = Math.max(curMaxRecSize, maxRecSize);
        }
        return maxRecSize;
    }

    public static void main(String[] args) {
        int[][] map = {
                {1, 0, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 0},
        };
        System.out.println("输入矩阵如下：");
        AlgoUtil.printArray2(map);
        System.out.println("输出最大子矩阵的大小：" + getMaxRecSize(map));
    }
}
