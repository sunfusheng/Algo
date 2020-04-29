package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.AlgoUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 【题目】
 * 56.合并区间
 * <p>
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 示例 2:
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * @author sunfusheng
 * @since 2020/4/29
 */
public class MergeArrayInterval {

    /**
     * 解题思路：使用贪心算法，获得每一步的最优解，结果肯定是最优解。
     *
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length < 2) {
            return intervals;
        }

        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] pre = res.get(res.size() - 1);
            int[] cur = intervals[i];
            if (pre[1] >= cur[0]) {
                pre[1] = Math.max(pre[1], cur[1]);
            } else {
                res.add(cur);
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{2, 6}, {8, 10}, {1, 3}, {15, 18}};
        int[][] intervals2 = new int[][]{{1, 4}, {4, 5}};
        System.out.println("输入：");
        AlgoUtil.printArray2(intervals);
        System.out.println("输出：");
        AlgoUtil.printArray2(merge(intervals));
    }
}
