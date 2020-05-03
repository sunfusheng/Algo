package com.sunfusheng.algo.Algo.Sort;

import com.sunfusheng.algo.common.util.AlgoUtil;

/**
 * 【堆排序】
 * 堆排序(Heap Sort)是指利用堆这种数据结构所设计的一种排序算法。
 * 堆是一个近似完全二叉树的结构，并同时满足堆积的性质：即子结点的键值或索引总是小于(或者大于)它的父节点。
 * <p>
 * 【堆】
 * 堆是一个树形结构，其实堆的底层是一棵完全二叉树。而完全二叉树是一层一层按照进入的顺序排成的。
 * 按照这个特性，我们可以用数组来按照完全二叉树实现堆。
 * <p>
 * 【大顶堆】
 * 根结点（亦称为堆顶）的关键字是堆里所有结点关键字中最大者，称为大顶堆。
 * 大顶堆要求根节点的关键字既大于或等于左子树的关键字值，又大于或等于右子树的关键字值。
 * <p>
 * 【小顶堆】
 * 根结点（亦称为堆顶）的关键字是堆里所有结点关键字中最小者，称为小顶堆。
 * 小堆堆要求根节点的关键字既小于或等于左子树的关键字值，又小于或等于右子树的关键字值。
 * <p>
 * 【算法描述】
 * 1、构建初始堆，将待排序列构成一个大顶堆(或者小顶堆)，升序大顶堆，降序小顶堆；
 * 2、将堆顶元素与堆尾元素交换，并断开(从待排序列中移除)堆尾元素。
 * 3、重新构建堆。
 * 4、重复2~3，直到待排序列中只剩下一个元素(堆顶元素)。
 *
 * @author sunfusheng
 * @since 2020/5/3
 */
public class HeapSort {

    /**
     * 初始化建堆的时间复杂度为O(n)
     * 排序重建堆的时间复杂度为O(nlogn)
     * 所以总的时间复杂度为O(n+nlogn) = O(nlogn)
     *
     * @param arr
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        buildHeap(arr);
        for (int i = arr.length - 1; i >= 0; i--) {
            swap(arr, i, 0);
            heapify(arr, i, 0);
        }
    }

    // 建堆
    private static void buildHeap(int[] arr) {
        int len = arr.length;
        for (int i = (len - 1) / 2; i >= 0; i--) {
            heapify(arr, len, i);
        }
    }

    // 堆化
    private static void heapify(int[] arr, int n, int i) {
        if (i > n) {
            return;
        }

        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max = i;

        if (left < n && arr[left] > arr[max]) {
            max = left;
        }
        if (right < n && arr[right] > arr[max]) {
            max = right;
        }
        if (max != i) {
            swap(arr, max, i);
            heapify(arr, n, max);
        }
    }

    // 交换数组的两个元素
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{7, 2, 4, 5, 3, 1, 6, 8};

        System.out.print("输入：");
        AlgoUtil.printlnArray(arr);
        heapSort(arr);
        System.out.print("输出：");
        AlgoUtil.printlnArray(arr);
    }
}
