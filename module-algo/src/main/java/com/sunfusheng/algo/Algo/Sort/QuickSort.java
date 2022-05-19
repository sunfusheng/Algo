package com.sunfusheng.algo.Algo.Sort;

import com.sunfusheng.algo.common.util.AlgoUtil;

import java.util.Random;

/**
 * 【快速排序】
 * 快速排序的基本思想：通过一趟排序将待排记录分隔成独立的两部分，
 * 其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序。
 * <p>
 * 【算法描述】
 * 快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下：
 * 1、从数列中随机挑出一个元素，称为 “基准”（pivot）。
 * 2、重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
 * 在这个分区退出之后，该基准就处于数列的中间位置。这个操作称为分区（partition）操作。
 * 3、递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 *
 * @author sunfusheng
 * @since 2020/5/3
 */
public class QuickSort {

    /**
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(logn)
     * <p>
     * 如果每次选取的中间值都位于排序数组的头部或尾部，那么快排的时间复杂度是 O(n2)
     */
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSortRecur(arr, 0, arr.length - 1);
    }

    private static void quickSortRecur(int[] arr, int start, int end) {
        if (start < end) {
            int pivot = partition(arr, start, end);
            quickSortRecur(arr, start, pivot - 1);
            quickSortRecur(arr, pivot + 1, end);
        }
    }

    private static int partition(int[] arr, int start, int end) {
        int random = new Random().nextInt(end - start + 1) + start;
        swap(arr, random, end);
        int small = start - 1;
        for (int i = start; i < end; i++) {
            if (arr[i] < arr[end]) {
                small++;
                swap(arr, small, i);
            }
        }
        small++;
        swap(arr, small, end);
        return small;
    }

    private static void swap(int[] arr, int i1, int i2) {
        if (i1 != i2) {
            int temp = arr[i1];
            arr[i1] = arr[i2];
            arr[i2] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {7, 2, 4, 5, 3, 1, 6, 8};

        System.out.print("输入：");
        AlgoUtil.printlnArray(arr);
        quickSort(arr);
        System.out.print("输出：");
        AlgoUtil.printlnArray(arr);
    }
}
