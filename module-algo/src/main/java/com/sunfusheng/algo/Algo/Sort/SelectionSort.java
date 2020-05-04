package com.sunfusheng.algo.Algo.Sort;

import com.sunfusheng.algo.common.util.AlgoUtil;

/**
 * 【选择排序】
 * 选择排序（Selection sort）是一种简单直观的排序算法。
 * 它的工作原理是：第一次从待排序的数据元素中选出最小（或最大）的一个元素，存放在序列的起始位置，
 * 然后再从剩余的未排序元素中寻找到最小（大）元素，然后放到已排序的序列的末尾。
 * 以此类推，直到全部待排序的数据元素的个数为零。
 * <p>
 * 【算法描述】
 * 1、初始状态：有序区为空，无序区为R[1..n]。
 * 2、第i趟排序开始时，有序区为R[1..i-1]，无序区为R(i..n）。
 * 3、n-1趟结束后，数组有序化了。
 *
 * @author sunfusheng
 * @since 2020/5/4
 */
public class SelectionSort {

    /**
     * 时间复杂度：O(n2)
     *
     * @param arr
     */
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int minIndex;
        for (int i = 0, len = arr.length; i < len - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, minIndex, i);
        }
    }

    // 交换数组两个元素
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{7, 2, 4, 5, 3, 1, 6, 8};

        System.out.print("输入：");
        AlgoUtil.printlnArray(arr);
        selectionSort(arr);
        System.out.print("输出：");
        AlgoUtil.printlnArray(arr);
    }
}
