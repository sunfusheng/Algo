package com.sunfusheng.algo.Algo.Sort;

import com.sunfusheng.algo.common.util.AlgoUtil;

/**
 * 【冒泡排序】
 * 冒泡排序是一种简单的排序算法。它重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。
 * 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。
 * 这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
 * <p>
 * 【算法描述】
 * 1、比较相邻的两个元素，如果第一个比第二个大，就交换它们两个。
 * 2、对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该是最大的数。
 * 3、针对所有的元素重复以上的步骤，除了最后一个；
 * 4、重复步骤1~3，直到排序完成。
 *
 * @author sunfusheng
 * @since 2020/5/3
 */
public class BubbleSort {

    /**
     * 时间复杂度：O(n2)
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0, len = arr.length; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
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
        bubbleSort(arr);
        System.out.print("输出：");
        AlgoUtil.printlnArray(arr);
    }
}
