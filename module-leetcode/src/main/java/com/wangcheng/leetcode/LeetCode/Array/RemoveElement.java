package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.LeetCodeUtil;

/**
 * 【题目】
 * 27. 移除元素
 * 给你一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并原地修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * <p>
 *【示例】
 * 给定 nums = [3,2,2,3], val = 3,
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 *
 * @author liwangcheng
 * @date 2020/3/20.
 */
public class RemoveElement {

    public static int solution1(int[] arr, int val) {
        if (arr.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] != val) {
                arr[i++] = arr[j];
            }
        }
        LeetCodeUtil.logln(arr);
        return i;
    }

    /**
     * 方法二：双指针 —— 当要删除的元素很少时
     *
     * 思路
     * 现在考虑数组包含很少的要删除的元素的情况。
     * 例如，num=[1，2，3，5，4]，Val=4。
     * 之前的算法会对前四个元素做不必要的复制操作。
     * 另一个例子是 num=[4，1，2，3，5]，Val=4。
     * 似乎没有必要将 [1，2，3，5] 这几个元素左移一步，
     * 因为问题描述中提到元素的顺序可以更改。
     *
     * 算法
     * 当遇到 nums[i] = val 时，可以将当前元素与
     * 最后一个元素进行交换，并释放最后一个元素。
     * 这实际上使数组的大小减少了 1。
     *
     * 请注意，被交换的最后一个元素可能是想要移除的值。
     * 但是不要担心，在下一次迭代中，仍然会检查这个元素。
     */
    public static int solution2(int[] arr, int val) {
        int i = 0;
        int n = arr.length;
        while (i < n) {
            if (arr[i] == val) {
                arr[i] = arr[n - 1];
                n--;
            } else {
                i++;
            }
        }
        LeetCodeUtil.logln(arr);
        return n;
    }
}
