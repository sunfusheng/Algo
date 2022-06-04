package com.wangcheng.leetcode.LeetCode.Array;

import android.annotation.SuppressLint;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 347.前 K 个高频元素
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。
 * 你可以按任意顺序返回答案。
 * <p>
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * <p>
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 * @author sunfusheng
 * @since 2022/06/04
 */
public class TopKFrequent {

    /**
     * 时间复杂度：O(n + k)
     * 空间复杂度：O(nlogk)
     */
    @SuppressLint("NewApi")
    public static int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        Map<Integer, Integer> numToCount = new HashMap<>();
        for (int num : nums) {
            numToCount.put(num, numToCount.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
                (o1, o2) -> numToCount.get(o1) - numToCount.get(o2)
        );
        for (int key : numToCount.keySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(key);
            } else if (numToCount.get(key) > numToCount.get(minHeap.peek())) {
                minHeap.poll();
                minHeap.offer(key);
            }
        }
        int[] res = new int[minHeap.size()];
        int i = 0;
        while (!minHeap.isEmpty()) {
            res[i] = minHeap.poll();
            i++;
        }
        return res;
    }
}
