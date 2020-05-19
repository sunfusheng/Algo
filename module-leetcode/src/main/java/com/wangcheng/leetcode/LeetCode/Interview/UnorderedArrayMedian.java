package com.wangcheng.leetcode.LeetCode.Interview;

import com.sunfusheng.algo.common.util.AlgoUtil;

import java.util.PriorityQueue;

/**
 * 【题目】
 * #.01. 无序数组的中位数
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
     *
     * @param arr
     * @return
     */
    public static double median(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int len = arr.length;
        sort(arr, 0, len - 1);
        if (len % 2 == 1) {
            return arr[len / 2];
        }
        return (arr[(len - 1) / 2] + arr[len / 2]) / 2.0;
    }

    private static void sort(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        int mid = partition(arr, left, right);
        sort(arr, left, mid - 1);
        sort(arr, mid + 1, right);
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        while (left < right) {
            while (left < right && arr[right] > pivot) right--;
            arr[left] = arr[right];
            while (left < right && arr[left] < pivot) left++;
            arr[right] = arr[left];
        }
        arr[left] = pivot;
        return left;
    }

    /**
     * 方法二：最小堆
     *
     * @param arr
     * @return
     */
    public static double median2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int heapSize = arr.length / 2 + 1;
        PriorityQueue<Integer> heap = new PriorityQueue<>(heapSize);
        for (int i = 0; i < heapSize; i++) {
            heap.add(arr[i]);
        }
        for (int i = heapSize; i < arr.length; i++) {
            if (heap.peek() < arr[i]) {
                heap.poll();
                heap.add(arr[i]);
            }
        }
        if (arr.length % 2 == 1) {
            return heap.peek();
        }
        return (heap.poll() + heap.peek()) / 2.0;
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
