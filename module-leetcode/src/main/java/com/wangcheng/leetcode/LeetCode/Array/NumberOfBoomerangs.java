package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.HashMap;
import java.util.Map;

/**
 *【题目】
 * 447.回旋镖的数量
 * 给定平面上 n 对不同的点，“回旋镖” 是由点表示的元组 (i, j, k) ，
 * 其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
 * <p>
 * 找到所有回旋镖的数量。
 * 你可以假设 n 最大为 500，所有点的坐标在闭区间 [-10000, 10000] 中。
 * <p>
 *【示例】
 * 输入:
 * [[0,0],[1,0],[2,0]]
 * 输出:
 * 2
 * 解释:
 * 两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 *
 * @author liwangcheng
 * @date 2020/5/11.
 */
public class NumberOfBoomerangs {

    /**
     * 思路: 排列组合的使用
     * 将当前点作为第一个点，计算与非当前点的距离，保存在hashMap中，
     * 若计算距离距离在hashMap中已有值，则表明之前有相同的距离，res，
     * 因为可以换位，则乘以2，并更新到当前hash中（可以与当前距离相同
     * 的任意一个点交换位置）.当前点作为第一个点结束之后，清空hashMap，
     * 继续以下一个点作为第一个点。
     *
     * 参考：
     * https://leetcode-cn.com/problems/number-of-boomerangs/solution/447-hui-xuan-biao-de-shu-liang-by-en-zhao/
     */
    public static int solution(int[][] points) {
        if (null == points || points.length == 0) {
            return 0;
        }
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            map.clear();
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                int d = distance(points, i, j);
                if (map.containsKey(d)) {
                    res += map.get(d) * 2;
                    map.put(d, map.get(d) + 1);
                } else {
                    map.put(d, 1);
                }
            }
        }
        return res;
    }

    private static int distance(int[][] points, int i, int j) {
        return (points[i][0] - points[j][0])*(points[i][0] - points[j][0])
                + (points[i][1] - points[j][1])*(points[i][1] - points[j][1]);
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution([[0,0],[1,0],[2,0]]) = " + NumberOfBoomerangs.solution(new int[][]{new int[]{0,0}, new int[]{1,0}, new int[]{2,0}}));
    }
}
