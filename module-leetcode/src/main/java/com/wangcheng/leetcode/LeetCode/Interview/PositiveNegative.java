package com.wangcheng.leetcode.LeetCode.Interview;

import com.sunfusheng.algo.common.util.AlgoUtil;

/**
 * 【题目】
 * #.03. 无序数组排序，负数在前正数在后且有序
 * <p>
 * 示例：
 * 输入：{3, -4, 2, -5, 1, -6}
 * 输出：{-4, -5, -6, 3, 2, 1}
 * <p>
 * 要求：
 * 时间复杂度：O(N)
 * 空间复杂度：O(1)
 *
 * @author sunfusheng
 * @since 2020/5/19
 */
public class PositiveNegative {

    /**
     * 负数在前，正数在后，无序
     *
     * @param arr
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            while (left < right && arr[left] < 0) left++;
            while (left < right && arr[right] > 0) right--;
            swap(arr, left, right);
        }
    }

    /**
     * 负数在前，正数在后，且有序
     *
     * @param arr
     */
    public static void sort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int left = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                int cur = i;
                while (cur > left) {
                    swap(arr, cur, --cur);
                }
                left++;
            }
        }
    }

    /**
     * 奇数在前，偶数在后，且有序
     *
     * @param arr
     */
    public static void sort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int left = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 1) {
                int cur = i;
                while (cur > left) {
                    swap(arr, cur, --cur);
                }
                left++;
            }
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, -4, 2, -5, 1, -6};
        System.out.println("【负数在前，正数在后，无序】");
        System.out.print("输入：");
        AlgoUtil.printlnArray(arr);
        sort(arr);
        System.out.print("输出：");
        AlgoUtil.printlnArray(arr);

        arr = new int[]{3, -4, 2, -5, 1, -6};
        System.out.println("\n【奇数在前，偶数在后，且有序】");
        System.out.print("输入：");
        AlgoUtil.printlnArray(arr);
        sort2(arr);
        System.out.print("输出：");
        AlgoUtil.printlnArray(arr);

        arr = new int[]{1, 2, 3, 4, 5, 6};
        System.out.println("\n【奇数在前，偶数在后，且有序】");
        System.out.print("输入：");
        AlgoUtil.printlnArray(arr);
        sort3(arr);
        System.out.print("输出：");
        AlgoUtil.printlnArray(arr);
    }
}
