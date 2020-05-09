package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.AlgoUtil;

import java.util.PriorityQueue;

/**
 * 【题目】
 * 215.数组中的第K个最大元素
 * <p>
 * 在未排序的数组中找到第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * <p>
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * <p>
 * 说明:
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 * @author sunfusheng
 * @since 2020/5/3
 */
public class FindKthLargest {

    /**
     * 方法一：堆
     * 思路是创建一个大顶堆，将所有数组中的元素加入堆中，并保持堆的大小小于等于 k。
     * 这样，堆中就保留了前 k 个最大的元素。这样，堆顶的元素就是正确答案。
     * <p>
     * 时间复杂度 : O(Nlogk)。
     * 空间复杂度 : O(k)，用于存储堆元素。
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest1(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(nums.length, (o1, o2) -> o1 - o2);
        for (int num : nums) {
            heap.offer(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        if (heap.size() == k) {
            Integer res;
            return (res = heap.poll()) == null ? -1 : res;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int[] nums2 = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.print("输入1：");
        AlgoUtil.printlnArray(nums);
        System.out.print("输入2：");
        AlgoUtil.printlnArray(nums2);

        System.out.println("方法一输出1：" + findKthLargest1(nums, 2));
        System.out.println("方法一输出2：" + findKthLargest1(nums2, 4));
    }
}
