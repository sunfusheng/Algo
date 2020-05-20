package com.sunfusheng.algo.Algo.Sort;

import com.sunfusheng.algo.common.util.AlgoUtil;

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
     * 方法一
     *
     * @param arr
     * @return
     */
    public static int[] mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }

        return mergeSortRecur(arr, 0, arr.length - 1);
    }

    private static int[] mergeSortRecur(int[] arr, int left, int right) {
        if (left == right) {
            return new int[]{arr[left]};
        }

        int mid = left + (right - left) / 2;
        int[] leftArr = mergeSortRecur(arr, left, mid);
        int[] rightArr = mergeSortRecur(arr, mid + 1, right);

        int[] res = new int[leftArr.length + rightArr.length];
        int i = 0, j = 0, k = 0;

        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] < rightArr[j]) {
                res[k++] = leftArr[i++];
            } else {
                res[k++] = rightArr[j++];
            }
        }
        while (i < leftArr.length) {
            res[k++] = leftArr[i++];
        }
        while (j < rightArr.length) {
            res[k++] = rightArr[j++];
        }
        return res;
    }

    /**
     * 方法二
     * <p>
     * 时间复杂度：O(nlogn)
     *
     * @param arr
     */
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        mergeSortRecur2(arr, 0, arr.length - 1);
    }

    private static void mergeSortRecur2(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSortRecur2(arr, left, mid);
        mergeSortRecur2(arr, mid + 1, right);
        merge2(arr, left, mid + 1, right);
    }

    private static void merge2(int[] arr, int left, int mid, int right) {
        int leftSize = mid - left;
        int rightSize = right - mid + 1;
        int[] leftArr = new int[leftSize];
        int[] rightArr = new int[rightSize];

        for (int i = left; i < mid; i++) {
            leftArr[i - left] = arr[i];
        }
        for (int i = mid; i <= right; i++) {
            rightArr[i - mid] = arr[i];
        }

        int i = 0, j = 0, k = left;
        while (i < leftSize && j < rightSize) {
            if (leftArr[i] < rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        while (i < leftSize) {
            arr[k++] = leftArr[i++];
        }
        while (j < rightSize) {
            arr[k++] = rightArr[j++];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{7, 2, 4, 5, 3, 1, 6, 8};

        System.out.print("输入：");
        AlgoUtil.printlnArray(arr);
        arr = mergeSort(arr);
//        mergeSort2(arr);
        System.out.print("输出：");
        AlgoUtil.printlnArray(arr);
    }
}
