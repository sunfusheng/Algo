package com.sunfusheng.algo.Algo.Sort;

import com.sunfusheng.algo.common.util.AlgoUtil;

import java.util.Arrays;

/**
 * 【归并排序】
 * 归并排序(Merge Sort)是建立在归并操作上的一种有效的排序算法，
 * 该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。
 * 若将两个有序表合并成一个有序表，称为二路归并。
 * <p>
 * 【算法描述】
 * 1、把长度为n的输入序列分成两个长度为n/2的子序列。
 * 2、对这两个子序列分别采用归并排序。
 * 3、将两个排序好的子序列合并成一个最终的排序序列。
 *
 * @author sunfusheng
 * @since 2020/5/4
 */
public class MergeSort {

    /**
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(n)
     */
    public static int[] mergeSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        int[] dst = Arrays.copyOf(nums, nums.length);
        mergeSortRecur(nums, dst, 0, nums.length);
        return dst;
    }

    private static void mergeSortRecur(int[] src, int[] dst, int start, int end) {
        if (start + 1 >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSortRecur(dst, src, start, mid);
        mergeSortRecur(dst, src, mid, end);

        int i = start;
        int j = mid;
        int k = start;
        while (i < mid || j < end) {
            if (j == end || (i < mid && src[i] < src[j])) {
                dst[k++] = src[i++];
            } else {
                dst[k++] = src[j++];
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{7, 2, 4, 5, 3, 1, 6, 8};

        System.out.print("输入：");
        AlgoUtil.printlnArray(arr);
        arr = mergeSort(arr);
        System.out.print("输出：");
        AlgoUtil.printlnArray(arr);
    }
}
