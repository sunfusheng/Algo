package com.sunfusheng.algo.Algo.Sort;

import com.sunfusheng.algo.common.util.AlgoUtil;

/**
 * 【插入排序】
 * 插入排序(Insertion Sort)，一般也被称为直接插入排序。对于少量元素的排序，它是一个有效的算法。
 * 插入排序是一种最简单的排序方法，它的基本思想是将一个记录插入到已经排好序的有序表中，
 * 从而一个新的、记录数增1的有序表。在其实现过程使用双层循环，外层循环对除了第一个元素之外的所有元素，
 * 内层循环对当前元素前面有序表进行待插入位置查找，并进行移动。
 * <p>
 * 【算法描述】
 * 1、从第一个元素开始，该元素可以认为已经被排序。
 * 2、取出下一个元素，在已经排序的元素序列中从后向前扫描。
 * 3、如果该元素（已排序）大于新元素，将该元素移到下一位置。
 * 4、重复步骤3，直到找到已排序的元素小于或者等于新元素的位置。
 * 5、将新元素插入到该位置后。
 * 6、重复步骤2~5。
 *
 * @author sunfusheng
 * @since 2020/5/4
 */
public class InsertionSort {

    /**
     * 时间复杂度：O(n2)
     *
     * @param arr
     */
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int preIndex;
        int curValue;
        for (int i = 1, len = arr.length; i < len; i++) {
            preIndex = i - 1;
            curValue = arr[i];
            while (preIndex >= 0 && arr[preIndex] > curValue) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = curValue;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{7, 2, 4, 5, 3, 1, 6, 8};

        System.out.print("输入：");
        AlgoUtil.printlnArray(arr);
        insertionSort(arr);
        System.out.print("输出：");
        AlgoUtil.printlnArray(arr);
    }
}
