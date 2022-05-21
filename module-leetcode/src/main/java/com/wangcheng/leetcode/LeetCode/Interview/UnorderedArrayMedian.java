package com.wangcheng.leetcode.LeetCode.Interview;

import com.sunfusheng.algo.common.util.AlgoUtil;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * 【题目】
 * 面试题 #.01. 无序数组的中位数
 * <p>
 * 中位数，就是数组排序后处于数组最中间的那个元素。如果数组长度是奇数，最中间就是位置为（n+1）／2的那个元素。
 * 如果是偶数呢，标准的定义是位置为 n/2 和位置为 n/2+1 的两个元素的和除以2的结果
 * <p>
 * 示例 1:
 * nums1 = [2, 3, 1]
 * 中位数是 2.0
 * <p>
 * 示例 2:
 * nums2 = [3, 2, 4, 1]
 * 则中位数是 2.5
 *
 * @author sunfusheng
 * @since 2020/5/13
 */
public class UnorderedArrayMedian {

    /**
     * 方法一：先快排，再拿数
     */
    public static double median(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int len = nums.length;
        sort(nums, 0, len - 1);
        if (len % 2 == 1) {
            return nums[len / 2];
        }
        return (nums[(len - 1) / 2] + nums[len / 2]) / 2.0;
    }

    private static void sort(int[] nums, int start, int end) {
        if (start < end) {
            int mid = partition(nums, start, end);
            sort(nums, start, mid - 1);
            sort(nums, mid + 1, end);
        }
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

    /**
     * 方法二：最小堆
     */
    public static double median2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int heapSize = nums.length / 2 + 1;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > heapSize) {
                minHeap.poll();
            }
        }
        if (nums.length % 2 == 1) {
            Integer res = minHeap.peek();
            return res == null ? -1 : res;
        }
        Integer res1 = minHeap.poll();
        Integer res2 = minHeap.peek();
        if (res1 != null && res2 != null) {
            return (res1 + res2) / 2.0;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 1};
        int[] arr2 = new int[]{3, 2, 4, 1};

        System.out.print("方法一输入1：");
        AlgoUtil.printlnArray(arr);
        System.out.println("方法一输出1：" + median(arr));
        System.out.print("方法一输入2：");
        AlgoUtil.printlnArray(arr2);
        System.out.println("方法一输出2：" + median(arr2));

        arr = new int[]{2, 3, 1};
        arr2 = new int[]{3, 2, 4, 1};

        System.out.print("方法二输入1：");
        AlgoUtil.printlnArray(arr);
        System.out.println("方法二输出1：" + median2(arr));
        System.out.print("方法二输入2：");
        AlgoUtil.printlnArray(arr2);
        System.out.println("方法二输出2：" + median2(arr2));
    }
}
