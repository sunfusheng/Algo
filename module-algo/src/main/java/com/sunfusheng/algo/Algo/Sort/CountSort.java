package com.sunfusheng.algo.Algo.Sort;

import com.sunfusheng.algo.common.util.AlgoUtil;

/**
 * 【计数排序】
 * 先统计数组中每个整数在数组中出现的次数，
 * 然后按照从小到大的顺序将每个整数按照它出现的次数填到数组中。
 * <p>
 * 时间复杂度：O(n+k)
 * 空间复杂度：O(k)
 *
 * @author sunfusheng
 * @since 2022/05/26
 */
public class CountSort {

    public static int[] sort(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(num, min);
            max = Math.max(num, max);
        }

        int[] counts = new int[max - min + 1];
        for (int num : nums) {
            counts[num - min]++;
        }

        int i = 0;
        for (int num = min; num <= max; num++) {
            while (counts[num - min] > 0) {
                counts[num - min]--;
                nums[i++] = num;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 4, 2, 3, 2, 1};
        System.out.print("输入：");
        AlgoUtil.printArray(nums);
        System.out.print("\n输出：");
        AlgoUtil.printArray(sort(nums));
    }
}
