package com.wangcheng.leetcode.LeetCode.Array;

import com.sunfusheng.algo.common.util.AlgoUtil;

/**
 * 【题目】
 * 189.旋转数组
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * <p>
 * 【示例】
 * 示例 1:
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * <p>
 * 示例 2:
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * 【说明】
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的原地算法。
 *
 * @author liwangcheng
 * @date 2020/4/10.
 */
public class RotateArray {

    /**
     * 方法 1：暴力
     * 最简单的方法是旋转 k 次，每次将数组旋转 1 个元素。
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n*k)。每个元素都被移动 1 步（O(n)）k次（O(k)）。
     * 空间复杂度：O(1)。没有额外空间被使用。
     */
    public static void solution1(int[] nums, int k) {
        if (check(nums, k)) {
            return;
        }
        int temp;
        for (int i = 0; i < k; i++) {
            temp = nums[nums.length - 1];
            for (int j = nums.length - 2; j >= 0; j--) {
                nums[j + 1] = nums[j];
            }
            nums[0] = temp;
        }
    }

    /**
     * 方法 2：使用额外的数组
     * 算法
     * 可以用一个额外的数组来将每个元素放到正确的位置上，
     * 也就是原本数组里下标为 i 的把它放到 (i+k)%数组长度 的位置。
     * 然后把新的数组拷贝到原数组中。
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n)。将数字放到新的数组中需要一遍遍历，另一边来把新数组的元素拷贝回原数组。
     * 空间复杂度：O(n)。另一个数组需要原数组长度的空间。
     */
    public static void solution2(int[] nums, int k) {
        if (check(nums, k)) {
            return;
        }
        k %= nums.length;
        int[] temp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int pos = (i + k) % nums.length;
            temp[pos] = nums[i];
        }
        System.arraycopy(temp, 0, nums, 0, nums.length);
    }

    /**
     * TODO
     * 方法 3：使用环状替换
     * 算法
     * 如果直接把每一个数字放到它最后的位置，但这样的后果是遗失原来的元素。
     * 因此，需要把被替换的数字保存在变量 temp 里面。然后，将被替换数字（temp）
     * 放到它正确的位置，并继续这个过程 n 次， n 是数组的长度。这是因为
     * 需要将数组里所有的元素都移动。但是，这种方法可能会有个问题，如果 n%k==0，
     * 其中 k=k%n （因为如果 k 大于 n ，移动 k 次实际上相当于移动 k%n 次）。
     * 这种情况下，会发现在没有遍历所有数字的情况下回到出发数字。此时，
     * 应该从下一个数字开始再重复相同的过程。
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n) 。只遍历了每个元素一次。
     * 空间复杂度：O(1) 。使用了常数个额外空间。
     */
    public static void solution3(int[] nums, int k) {
        if (check(nums, k)) {
            return;
        }
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }

    /**
     * 方法 4：使用反转
     * 算法
     * 这个方法基于这个事实：当旋转数组 k 次， k%n 个尾部元素会被移动到头部，
     * 剩下的元素会被向后移动。
     * 在这个方法中，首先将所有元素反转。然后反转前 k 个元素，再反转后面 n-k 个元素，
     * 就能得到想要的结果。
     * <p>
     * 假设 n=7 且 k=3。
     * <p>
     * 原始数组 ---------------- : 1 2 3 4 5 6 7
     * 反转所有数字后 ----------- : 7 6 5 4 3 2 1
     * 反转前 k 个数字后 -------- : 5 6 7 4 3 2 1
     * 反转后 n-k 个数字后 ------ : 5 6 7 1 2 3 4 --> 结果
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n) 。 n 个元素被反转了总共 3 次。
     * 空间复杂度：O(1) 。 没有使用额外的空间。
     */
    public static void solution4(int[] nums, int k) {
        if (check(nums, k)) {
            return;
        }
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    private static boolean check(int[] nums, int k) {
        return null == nums || k < 0 || nums.length <= k;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        System.out.print("输入：");
        AlgoUtil.printlnArray(nums);
        solution4(nums, 3);
        System.out.print("输出：");
        AlgoUtil.printlnArray(nums);
    }
}
