package com.sunfusheng.algo.Algo.StackQueue;

import com.sunfusheng.algo.common.util.AlgoUtil;

import java.util.LinkedList;

/**
 * 【题目】
 * 生成窗口最大值数组
 * <p>
 * 有一个整型数组arr和一个大小为w的窗口从数组的最左边滑到最右边，
 * 窗口每次向右边滑一个位置。
 * <p>
 * 如果数组长度为n，窗口大小为w，则一共产生n-w+1个窗口的最大值。请实现一个函数。
 * 输入：整型数组arr，窗口大小为w。
 * 输出：一个长度为n-w+1的数组res，res[i]表示每一种窗口状态下的最大值。
 * <p>
 * 例如：数组为{4, 3, 5, 4, 3, 3, 6, 7}，窗口大小为3时，
 * 结果应该返回{5, 5, 5, 4, 6, 7}。
 *
 * @author sunfusheng
 * @since 2020-02-22
 */
public class MaxWindow {

    // 生成窗口最大值数组
    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || arr.length == 0 || w <= 0) {
            return null;
        }

        LinkedList<Integer> qmax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!qmax.isEmpty() && arr[i] >= arr[qmax.peekLast()]) {
                qmax.pollLast();
            }
            qmax.addLast(i);

            if (qmax.peekFirst() == i - w) {
                qmax.pollFirst();
            }
            if (i >= w - 1) {
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        int[] res = getMaxWindow(arr, 3);
        AlgoUtil.printlnArray(res);
    }
}
