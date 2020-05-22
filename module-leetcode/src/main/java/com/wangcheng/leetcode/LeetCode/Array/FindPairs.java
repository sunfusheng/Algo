package com.wangcheng.leetcode.LeetCode.Array;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *【题目】
 * 532.数组中的K-diff数对
 * 给定一个整数数组和一个整数 k, 你需要在数组里找到不同的 k-diff 数对。
 * 这里将 k-diff 数对定义为一个整数对 (i, j), 其中 i 和 j 都是数组
 * 中的数字，且两数之差的绝对值是 k.
 *【示例】
 * 示例 1:
 * 输入: [3, 1, 4, 1, 5], k = 2
 * 输出: 2
 * 解释: 数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
 * 尽管数组中有两个1，但我们只应返回不同的数对的数量。
 *
 * 示例 2:
 * 输入:[1, 2, 3, 4, 5], k = 1
 * 输出: 4
 * 解释: 数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5)。
 *
 * 示例 3:
 * 输入: [1, 3, 1, 5, 4], k = 0
 * 输出: 1
 * 解释: 数组中只有一个 0-diff 数对，(1, 1)。
 *【注意】
 * 数对 (i, j) 和数对 (j, i) 被算作同一数对。
 * 数组的长度不超过10,000。
 * 所有输入的整数的范围在 [-1e7, 1e7]。
 *
 * @author liwangcheng
 * @date 2020/5/22.
 */
public class FindPairs {

    /**
     * 方法一：排序+遍历
     * 查找数组中两数之差的绝对值是k, 首先想到也最简单的就是排序之后进行计算
     * 定义一个起始指针start 随着i的增加找到差值为k的时候 start++, i++
     * 中间会遇到两种特殊情况：
     * - 当遇到一串相同数字的时候 那么只需要i不断前进即可
     *   当遇到差值为k的时候记录下prev = nums[start]
     *   然后跳过所有等于prev的start
     * - 就是当差值大于k的时候，需要start进1
     *   此时需要判断增长后的start是否等于i
     *   只有相等的情况才需要i也进1，否则需要i--
     *
     * 参考：
     * https://leetcode-cn.com/problems/k-diff-pairs-in-an-array/solution/pai-xu-bian-li-zhi-xing-yong-shi-4-ms-zai-suo-you-/
     */
    public static int solution1(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        Arrays.sort(nums);
        int start = 0;
        int count = 0;
        int prev = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[start] > k || prev == nums[start]) {
                if (++start != i) {
                    i--;
                }
            } else if (nums[i] - nums[start] == k) {
                prev = nums[start++];
                count++;
            }
        }
        return count;
    }

    /**
     * 方法二：利用map
     * 参考：
     * https://leetcode-cn.com/problems/k-diff-pairs-in-an-array/solution/mapwen-ti-de-si-lu-liang-shu-de-si-ze-yun-suan-by-/
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static int solution2(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        // 遍历的逻辑分成两种情况k=0时，出现大于一次时 就是一种结果。
        // k!=0时，map中包含i+k的key时就是一种情况。
        for (int i : map.keySet()) {
            if (k == 0) {
                if (map.get(i) > 1) {
                    count++;
                }
            } else {
                if (map.containsKey(i + k)) {
                    count++;
                }
            }
        }
        return count;
    }
}
