package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.AlgoUtil;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * 【题目】
 * 215.数组中的第K个最大元素
 * <p>
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * <p>
 * 示例2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
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
     */
    public static int findKthLargest1(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(k);
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

    /**
     * 方法二：快排思想
     * <p>
     * 时间复杂度 : O(N)。
     * 空间复杂度 : O(1)
     */
    public static int findKthLargest2(int[] nums, int k) {
        int target = nums.length - k;
        int start = 0;
        int end = nums.length - 1;
        int index = partition(nums, start, end);
        while (index != target) {
            if (index > target) {
                end = index - 1;
            } else {
                start = index + 1;
            }
            index = partition(nums, start, end);
        }
        return nums[index];
    }

    private static int partition(int[] nums, int start, int end) {
        int random = new Random().nextInt(end - start + 1) + start;
        swap(nums, random, end);
        int small = start - 1;
        for (int i = start; i < end; i++) {
            if (nums[i] < nums[end]) {
                small++;
                swap(nums, small, i);
            }
        }
        small++;
        swap(nums, small, end);
        return small;
    }

    private static void swap(int[] nums, int i1, int i2) {
        if (i1 != i2) {
            int temp = nums[i1];
            nums[i1] = nums[i2];
            nums[i2] = temp;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int[] nums2 = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};

        System.out.print("方法一输入1：");
        AlgoUtil.printlnArray(nums);
        System.out.println("方法一输出1：" + findKthLargest1(nums, 2));
        System.out.print("方法一输入2：");
        AlgoUtil.printlnArray(nums2);
        System.out.println("方法一输出2：" + findKthLargest1(nums2, 4));

        System.out.print("方法二输入1：");
        AlgoUtil.printlnArray(nums);
        System.out.println("方法二输出1：" + findKthLargest2(nums, 2));
        System.out.print("方法二输入2：");
        AlgoUtil.printlnArray(nums2);
        System.out.println("方法二输出2：" + findKthLargest2(nums2, 4));
    }
}
