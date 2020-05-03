package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *【题目】
 * 303.区域和检索 - 数组不可变
 * <p>
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，
 * 包含 i,  j 两点。
 * <p>
 *【示例】
 * 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 *【说明】
 * 你可以假设数组不可变。
 * 会多次调用 sumRange 方法。
 *
 * @author liwangcheng
 * @date 2020/5/3.
 */
public class NumArray {

    private int[] data;
    private Map<Pair, Integer> map = new HashMap<>();
    private int[] sum;

    private static class Pair {
        int key;
        int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Pair pair = (Pair) o;
            return key == pair.key &&
                    value == pair.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

    public NumArray(int[] nums) {
        data = nums;
        sum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
            int tempSum = 0;
            for (int j = i; j < nums.length; j++) {
                tempSum += nums[j];
                map.put(new Pair(i, j), tempSum);
            }
        }
    }

    /**
     * 方法一：暴力法[超过时间限制]
     * 每次调用 sumrange 时，都使用for循环将索引 i 到 j 之间的每个元素相加。
     *
     * 复杂度分析
     * 时间复杂度：每次查询的时间 O(n)，每个 sumrange 查询需要 O(n) 时间。
     * 空间复杂度：O(1)，请注意，data 是对 nums 的引用，不是它的副本。
     */
    public int solution1(int i, int j) {
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += data[k];
        }
        return sum;
    }

    /**
     * 方法二：缓存
     *
     * 复杂度分析
     * 时间复杂度：每次查询的时间 O(1)，O(n^2)时间用来预计算。
     *  在构造函数中完成的预计算需要 O(n^2)时间。每个 sumrange
     *  查询的时间复杂性是 O(1) 因为哈希表的查找操作是常量时间。
     * 空间复杂度：O(n^2)，所需的额外空间为 O(n^2)，
     *  因为 i 和 j 都有 n 个候选空间。
     */
    public int solution2(int i, int j) {
        return map.get(new Pair(i, j));
    }

    /**
     * 方法三：缓存
     * 将 sum[k] 定义为 nums[0⋯k−1] 的累积和（包括这两个值）
     * 现在，可以计算 sumrange 如下：
     * sumrange（i，j）=sum[j+1]−sum[i]
     */
    public int solution3(int i, int j) {
        return sum[j + 1] - sum[i];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(nums);
        LeetCodeUtil.logln("solution1(0,2) = " + numArray.solution1(0, 2));
        LeetCodeUtil.logln("solution1(2,5) = " + numArray.solution1(2, 5));
        LeetCodeUtil.logln("solution1(0,5) = " + numArray.solution1(0, 5));
        LeetCodeUtil.logln("solution2(0,2) = " + numArray.solution2(0, 2));
        LeetCodeUtil.logln("solution2(2,5) = " + numArray.solution2(2, 5));
        LeetCodeUtil.logln("solution2(0,5) = " + numArray.solution2(0, 5));
        LeetCodeUtil.logln("solution3(0,2) = " + numArray.solution3(0, 2));
        LeetCodeUtil.logln("solution3(2,5) = " + numArray.solution3(2, 5));
        LeetCodeUtil.logln("solution3(0,5) = " + numArray.solution3(0, 5));
    }
}
