package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 * 【题目】
 * 26. 删除排序数组中的重复项
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 【示例】
 * 给定数组 nums = [1,1,2],
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 * @author liwangcheng
 * @date 2020/3/20.
 */
public class RemoveDuplicates {

    public static int solution1(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return 1;
        }
        int res = 0;
        for (int i = 0, j = 1; j < arr.length; ) {
            int pre = arr[i];
            int next = arr[j];
            if (next == pre) {
                j++;
            } else {
                arr[++i] = arr[j];
                res = i;
            }
        }
        LeetCodeUtil.logln(arr);
        return res + 1;
    }

    /**
     * 方法：双指针法
     *
     * 数组完成排序后，可以放置两个指针 i 和 j，其中 i 是慢指针，而 j 是快指针。
     * 只要 nums[i] = nums[j]，就增加 j 以跳过重复项。
     * 当遇到 nums[j] ≠ nums[i] 时，跳过重复项的运行已经结束，因此必须把它
     * （nums[j]）的值复制到 nums[i+1]。然后递增 i，接着将再次重复相同的过程，
     * 直到 j 到达数组的末尾为止。
     *
     * 复杂度分析
     * 时间复杂度：O(n)，假设数组的长度是 n，那么 i 和 j 分别最多遍历 n 步。
     * 空间复杂度：O(1)。
     */
    public static int officialSolution(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 1; j < arr.length; j++) {
            if (arr[j] != arr[i]) {
                arr[++i] = arr[j];
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        LeetCodeUtil.logln("solution([1,1,2]) = " + RemoveDuplicates.solution1(new int[]{1, 1, 2}));
        LeetCodeUtil.logln("solution([0,0,1,1,1,2,2,3,3,4]) = " + RemoveDuplicates.solution1(new int[]{0,0,1,1,1,2,2,3,3,4}));
        LeetCodeUtil.logln("officialSolution([1,1,2]) = " + RemoveDuplicates.officialSolution(new int[]{1, 1, 2}));
        LeetCodeUtil.logln("officialSolution([0,0,1,1,1,2,2,3,3,4]) = " + RemoveDuplicates.officialSolution(new int[]{0,0,1,1,1,2,2,3,3,4}));
    }

}
