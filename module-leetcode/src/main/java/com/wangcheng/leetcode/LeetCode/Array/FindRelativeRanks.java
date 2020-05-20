package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *【题目】
 * 506.相对名次
 * 给出 N 名运动员的成绩，找出他们的相对名次并授予前三名对应的奖牌。
 * 前三名运动员将会被分别授予 “金牌”，“银牌” 和“ 铜牌”
 * （"Gold Medal", "Silver Medal", "Bronze Medal"）。
 * <p>
 * (注：分数越高的选手，排名越靠前。)
 * <p>
 * 示例 1:
 *
 * 输入: [5, 4, 3, 2, 1]
 * 输出: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
 * 解释: 前三名运动员的成绩为前三高的，因此将会分别被授予 “金牌”，“银牌”
 * 和“铜牌” ("Gold Medal", "Silver Medal" and "Bronze Medal").
 * 余下的两名运动员，我们只需要通过他们的成绩计算将其相对名次即可。
 * <p>
 * 提示:
 * <p>
 * N 是一个正整数并且不会超过 10000。
 * 所有运动员的成绩都不相同。
 * <p>
 *
 * @author liwangcheng
 * @date 2020/5/20.
 */
public class FindRelativeRanks {

    public static String[] solution(int[] nums) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0, j = nums.length; i < nums.length; i++) {
            map.put(nums[i], j--);
        }
        String[] res = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            Integer j = map.get(copy[i]);
            if (j == 1) {
                res[i] = "Gold Medal";
            } else if (j == 2) {
                res[i] = "Silver Medal";
            } else if (j == 3) {
                res[i] = "Bronze Medal";
            } else {
                res[i] = j.toString();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution([1]) = " + Arrays.toString(FindRelativeRanks.solution(new int[]{1})));
        LeetCodeUtil.logln("solution([2, 1]) = " + Arrays.toString(FindRelativeRanks.solution(new int[]{2, 1})));
        LeetCodeUtil.logln("solution([5, 4, 3, 2, 1]) = " + Arrays.toString(FindRelativeRanks.solution(new int[]{5, 4, 3, 2, 1})));
        LeetCodeUtil.logln("solution([1,5,10,6,2,8]) = " + Arrays.toString(FindRelativeRanks.solution(new int[]{1, 5, 10, 6, 2, 8})));
    }
}
